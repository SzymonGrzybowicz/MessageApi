package com.example.message.domain

import javax.persistence.*

@Entity(name = "MESSAGES")
class Message(

        @Column(name = "MESSAGE_ID")
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = -1,

        @Column(length = 10000)
        val content: String,

        @Column
        var timestamp: Long,

        @JoinColumn(name = "USER_ID", nullable = false)
        @ManyToOne
        val creator: User?,

        @JoinColumn(name = "CONVERSATION_ID", nullable = false)
        @ManyToOne
        val conversation: Conversation?
)