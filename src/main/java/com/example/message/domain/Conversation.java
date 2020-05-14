package com.example.message.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "CONVERSATIONS")
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class Conversation {

    public Conversation(List<User> with, boolean unread) {
        this.with = with;
        this.unread = unread;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CONVERSATION_ID")
    private long id;

    @ManyToMany(fetch = FetchType.LAZY ,cascade = {CascadeType.ALL})
    @JoinTable(
            name = "JOIN_CONVERSATION_MEMBERS",
            joinColumns = {@JoinColumn(name = "CONVERSATION_ID", referencedColumnName = "CONVERSATION_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")}
    )
    @Setter
    private List<User> with;

    @Setter
    @Column
    private boolean unread;
}
