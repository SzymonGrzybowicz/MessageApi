package com.example.message.service

import com.example.message.domain.Conversation
import com.example.message.domain.Message
import com.example.message.domain.User
import com.example.message.notifier.MessageNotifier
import com.example.message.repository.ConversationRepository
import com.example.message.repository.MessageRepository
import com.example.message.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class MessageService(
        private val messageRepository: MessageRepository,
        private val messageNotifier: MessageNotifier,
        private val conversationRepository: ConversationRepository,
        private val userRepository: UserRepository
) {

    fun onNewMessage(message: Message) {
        message.timestamp = System.currentTimeMillis()
        messageRepository.save(message)
        createConversationsInOtherUsers(message)
        messageNotifier.notify(message)
    }

    fun getMessagesForConversation(conversationId: Long): List<Message> {
        return messageRepository.findAll().filter { m -> m.conversation?.id == conversationId } //todo
    }

    private fun createConversationsInOtherUsers(message: Message) {
        message.conversation?.with?.forEach { user ->
            val conversationsWithCurrentUser = user.conversations.filter { c-> c.with.contains(message.creator) }
            if (conversationsWithCurrentUser.isEmpty()) {
                val users = ArrayList(message.conversation.with)
                users.remove(user)
                users.add(message.creator)
                val newConversation = Conversation(-1, users, true)
                conversationRepository.save(newConversation)
                val newMessage = Message(-1, message.content, message.timestamp, message.creator, newConversation)
                messageRepository.save(newMessage)
                user.conversations.add(newConversation)
                userRepository.save(user)
            } else {
                val conversation = conversationsWithCurrentUser[0]
                conversation.unread = true
                conversationRepository.save(conversation)
                val newMessage = Message(-1, message.content, message.timestamp, message.creator, conversation)
                messageRepository.save(newMessage)
            }
        }
    }

    private fun sortByTimestamp(messages: List<Message>): List<Message> {
        return messages.sortedBy { message -> message.timestamp }
    }

}