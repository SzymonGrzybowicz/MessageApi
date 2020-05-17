package com.example.message.domain

import javax.persistence.*

@Entity(name = "CONVERSATIONS")
class Conversation(

        @Id
        @Column(name = "CONVERSATION_ID")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = -1,

        @JoinTable(
                name = "JOIN_CONVERSATION_MEMBERS",
                joinColumns = [JoinColumn(name = "CONVERSATION_ID", referencedColumnName = "CONVERSATION_ID")],
                inverseJoinColumns = [JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")])

        @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        var with: List<User>,

        @Column
        var unread: Boolean
)