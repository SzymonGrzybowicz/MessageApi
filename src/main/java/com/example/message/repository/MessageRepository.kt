package com.example.message.repository

import com.example.message.domain.Message
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component

@Component
interface MessageRepository : CrudRepository<Message, Long> {
    override fun findAll(): List<Message>
}