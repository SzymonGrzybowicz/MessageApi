package com.example.message.repository

import com.example.message.domain.Conversation
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ConversationRepository : CrudRepository<Conversation, Long> {
    override fun findAll(): List<Conversation>
}