package com.example.message.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "USERS")
@Getter
@NoArgsConstructor
public class User {

    public User(long id, String name, String password, String mail, List<FirebaseAuthToken> firebaseAuthTokens) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.mail = mail;
        this.firebaseAuthTokens = firebaseAuthTokens;
    }

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String mail;

    @OneToMany(fetch = FetchType.LAZY ,cascade = {CascadeType.ALL})
    @JoinTable(
            name="USER_AUTH_ID",
            joinColumns = @JoinColumn( name="FIREBASE_AUTH_TOKEN_ID"),
            inverseJoinColumns = @JoinColumn( name="USER_ID")
    )
    private List<FirebaseAuthToken> firebaseAuthTokens;

    @Column
    @OneToMany(fetch = FetchType.LAZY ,cascade = {CascadeType.ALL})
    @JoinTable(
            name = "JOIN_USER_CONVERSATIONS",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CONVERSATION_ID", referencedColumnName = "CONVERSATION_ID")}
    )
    private List<Conversation> conversations;
}
