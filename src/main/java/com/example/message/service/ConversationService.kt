package com.example.message.service

import com.example.message.domain.Conversation
import com.example.message.domain.User
import com.example.message.domain.dto.ConversationDto
import com.example.message.exception.IncorrectDataException
import com.example.message.firebase.FCMInitializer
import com.example.message.mapper.ConversationMapper
import com.example.message.repository.ConversationRepository
import com.example.message.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class ConversationService(
        private val userRepository: UserRepository,
        private val conversationRepository: ConversationRepository,
        private val conversationMapper: ConversationMapper)
{

    fun deleteConversationForUser(userId: Long, conversationId: Long): Boolean {
        userRepository.findByIdOrNull(userId)?.let { user ->
            conversationRepository.findByIdOrNull(conversationId)?.let { conversation ->
                return if (user.conversations.contains(conversation)) {
                    user.conversations.remove(conversation)
                    userRepository.save(user)
                    true
                } else {
                    false
                }
            }
            return false
        }
        return false
    }

    @Throws(IncorrectDataException::class)
    fun getConversationForUser(userId: Long): List<Conversation> {
        userRepository.findByIdOrNull(userId)?.let {
            return it.conversations
        }

        throw IncorrectDataException()
    }

    fun createConversationForUser(userId: Long, conversationDto: ConversationDto): Boolean {
        logger.info("userId($userId), conversationDto($conversationDto); createConversationForUser()")
        userRepository.findByIdOrNull(userId)?.let { currentUser ->
            val conversation = conversationMapper.mapToDomain(conversationDto)
            val withUsers: MutableList<User> = ArrayList()
            conversation.with.forEach { withUser ->
                val user  = userRepository.findByIdOrNull(withUser.id) ?: return false
                withUsers.add(user)
            }
            if (currentUser.conversations.any{ c-> c.with == withUsers}) {
                logger.error("conversation wasn't created because of duplicate; createConversationForUser()")
                return false
            }
            conversation.with = withUsers
            conversationRepository.save(conversation)
            currentUser.conversations.add(conversation)
            userRepository.save(currentUser)
            return true
        }

        logger.error("conversation wasn't created because there is no user with id($userId); createConversationForUser()")
        return false
    }

    fun markAsRead(conversationId: Long) {
        logger.info("conversationId($conversationId) markAsRead()")
        conversationRepository.findByIdOrNull(conversationId)?.let {
            it.unread = false
            conversationRepository.save(it)
            return
        }

        logger.error("there is no conversation with id($conversationId); markAsRead()")
    }

    private var logger = LoggerFactory.getLogger(this::class.java)
}