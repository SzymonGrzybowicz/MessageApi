package com.example.message.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "MESSAGES")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Column(name = "MESSAGE_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 10000)
    private String content;

    @Column
    @Setter
    private long timestamp;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User creator;

    @ManyToOne
    @JoinColumn(name = "CONVERSATION_ID", nullable = false)
    private Conversation conversation;
}
