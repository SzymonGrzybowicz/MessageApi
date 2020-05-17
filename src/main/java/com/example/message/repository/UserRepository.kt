package com.example.message.repository

import com.example.message.domain.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : CrudRepository<User, Long> {
    override fun findAll(): List<User>
    fun findByMail(mail: String): User?
}