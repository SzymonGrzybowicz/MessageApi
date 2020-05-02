package com.example.message.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "users")
@Getter
@NoArgsConstructor
public class User {

    public User(String name, String password, String mail) {
        this.name = name;
        this.password = password;
        this.mail = mail;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String mail;
}
