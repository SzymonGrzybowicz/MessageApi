package com.example.message.repository;

import com.example.message.domain.FirebaseAuthToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FirebaseAuthTokenRepository extends CrudRepository<FirebaseAuthToken, Long> {
    @Override
    List<FirebaseAuthToken> findAll();
}
