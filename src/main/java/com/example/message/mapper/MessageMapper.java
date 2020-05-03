package com.example.message.mapper;

import com.example.message.domain.Message;
import com.example.message.domain.dto.MessageDto;
import com.example.message.repository.ConversationRepository;
import com.example.message.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper implements IMapper<Message, MessageDto>{

    @Autowired
    public MessageMapper(UserRepository userRepository, ConversationRepository conversationRepository) {
        this.userRepository = userRepository;
        this.conversationRepository = conversationRepository;
    }

    @Override
    public Message mapToDomain(MessageDto messageDto) {
        return new Message(
                messageDto.getId(),
                messageDto.getContent(),
                userRepository.findById(messageDto.getCreatorID()).orElse(null),
                conversationRepository.findById(messageDto.getConversationId()).orElse(null)
        );
    }

    @Override
    public MessageDto mapToDto(Message message) {
        return new MessageDto(
                message.getId(), message.getContent(),
                message.getCreator().getId(),
                message.getConversation().getId()
        );
    }

    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;
}
