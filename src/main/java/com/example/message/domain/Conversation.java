package com.example.message.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class Conversation {

    public Conversation(List<User> members) {
        this.members = members;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CONVERSATION_ID")
    private long id;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "JOIN_CONVERSATION_MEMBERS",
            joinColumns = {@JoinColumn(name = "CONVERSATION_ID", referencedColumnName = "CONVERSATION_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")}
    )
    private List<User> members;
}
