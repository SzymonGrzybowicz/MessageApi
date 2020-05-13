package com.example.message.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "USERS")
@Getter
@NoArgsConstructor
public class User {

    public User(long id, String name, String password, String mail, String firebaseAuthToken) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.mail = mail;
        this.firebaseAuthToken = firebaseAuthToken;
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

    @Setter
    @Column(length = 1000)
    private String firebaseAuthToken;
}
