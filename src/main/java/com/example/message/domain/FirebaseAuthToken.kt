package com.example.message.domain

import javax.persistence.*

@Entity(name = "FirebaseAuthUser")
class FirebaseAuthToken(

        @Id
        @Column(name = "FIREBASE_AUTH_TOKEN_ID")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = -1,

        @Column(length = 1000)
        var firebaseAuthToken: String
)