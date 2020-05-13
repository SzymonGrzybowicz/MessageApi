package com.example.message.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "FirebaseAuthUser")
@Getter
@NoArgsConstructor
public class FirebaseAuthToken {

    public FirebaseAuthToken(String firebaseAuthToken) {
        this.firebaseAuthToken = firebaseAuthToken;
    }

    @Id
    @Column(name = "FIREBASE_AUTH_TOKEN_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Setter
    @Column(length = 1000)
    private String firebaseAuthToken;
}
