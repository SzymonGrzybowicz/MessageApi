package com.example.message.mapper;

import com.example.message.domain.Conversation;
import com.example.message.domain.dto.ConversationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConversationMapper implements IMapper<Conversation, ConversationDto> {

    @Autowired
    public ConversationMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Conversation mapToDomain(ConversationDto conversationDto) {
        return new Conversation(conversationDto.getId(), userMapper.mapToDomainList(conversationDto.getMembers()));
    }

    @Override
    public ConversationDto mapToDto(Conversation conversation) {
        return new ConversationDto(conversation.getId(), userMapper.mapToDtoList(conversation.getMembers()));
    }

    private final UserMapper userMapper;
}
