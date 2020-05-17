package com.example.message.repository

import com.example.message.domain.FirebaseAuthToken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FirebaseAuthTokenRepository : CrudRepository<FirebaseAuthToken, Long> {
    override fun findAll(): List<FirebaseAuthToken>
}