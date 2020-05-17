package com.example.message.mapper

import com.example.message.domain.Conversation
import com.example.message.domain.dto.ConversationDto
import org.springframework.stereotype.Component

@Component
class ConversationMapper(private val userMapper: UserMapper) :
        IMapper<Conversation, ConversationDto> {

    override fun mapToDomain(dto: ConversationDto): Conversation {
        return Conversation(dto.id, userMapper.mapToDomainList(dto.with), dto.unread)
    }

    override fun mapToDto(domain: Conversation): ConversationDto {
        return ConversationDto(domain.id, userMapper.mapToDtoList(domain.with), domain.unread)
    }
}