package com.example.message.mapper;

import com.example.message.domain.Conversation;
import com.example.message.domain.dto.ConversationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConversationMapper {

    @Autowired
    public ConversationMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Conversation mapToConversation(ConversationDto conversationDto) {
        return new Conversation(conversationDto.getId(), userMapper.mapToUserList(conversationDto.getMembers()));
    }

    public ConversationDto mapToConversationDto(Conversation conversation) {
        return new ConversationDto(conversation.getId(), userMapper.mapToUserDtoList(conversation.getMembers()));
    }

    public List<Conversation> mapToConversationList(List<ConversationDto> conversationDtoList) {
        return  conversationDtoList.stream().map(this::mapToConversation).collect(Collectors.toList());
    }

    public List<ConversationDto> mapToConversationDtoList(List<Conversation> conversationList) {
        return conversationList.stream().map(this::mapToConversationDto).collect(Collectors.toList());
    }

    private final UserMapper userMapper;
}
