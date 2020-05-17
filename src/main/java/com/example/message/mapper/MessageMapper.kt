package com.example.message.mapper

import com.example.message.domain.Message
import com.example.message.domain.dto.MessageDto
import com.example.message.exception.IncorrectDataException
import com.example.message.repository.ConversationRepository
import com.example.message.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class MessageMapper(
        private val userRepository: UserRepository,
        private val conversationRepository: ConversationRepository
) : IMapper<Message, MessageDto> {

    @Throws(IncorrectDataException::class)
    override fun mapToDomain(dto: MessageDto): Message {
        return Message(
                dto.id,
                dto.content,
                dto.timestamp,
                userRepository.findById(dto.creatorID ?: -1).orElse(null),
                conversationRepository.findById(dto.conversationId ?: -1).orElse(null)
        )
    }

    override fun mapToDto(domain: Message): MessageDto {
        return MessageDto(
                domain.id,
                domain.content,
                domain.conversation?.id,
                domain.creator?.id,
                domain.timestamp
        )
    }
}