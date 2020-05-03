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
    @Column(name = "conversation_id")
    private long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "join_conversation_members",
            joinColumns = {@JoinColumn(name = "conversation_id", referencedColumnName = "conversation_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")}
    )
    private List<User> members;
}
