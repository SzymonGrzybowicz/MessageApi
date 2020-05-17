package com.example.message.domain

import javax.persistence.*

@Entity(name = "USERS")
class User(

        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "USER_ID")
        @Id
        val id: Long = -1,

        @Column
        val name: String,

        @Column
        val password: String?,

        @Column(unique = true)
        val mail: String,

        @JoinTable(
                name = "USER_AUTH_ID",
                joinColumns = [JoinColumn(name = "FIREBASE_AUTH_TOKEN_ID")],
                inverseJoinColumns = [JoinColumn(name = "USER_ID")]
        )
        @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        val firebaseAuthTokens: MutableList<FirebaseAuthToken>,

        @Column
        @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinTable(
                name = "JOIN_USER_CONVERSATIONS",
                joinColumns = [JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")],
                inverseJoinColumns = [JoinColumn(name = "CONVERSATION_ID", referencedColumnName = "CONVERSATION_ID")]
        )
        val conversations: MutableList<Conversation> = mutableListOf()
)

