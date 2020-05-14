package com.example.message.service;

import com.example.message.domain.Conversation;
import com.example.message.domain.User;
import com.example.message.domain.dto.ConversationDto;
import com.example.message.exception.IncorrectDataException;
import com.example.message.mapper.ConversationMapper;
import com.example.message.repository.ConversationRepository;
import com.example.message.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConversationService {

    public ConversationService(UserRepository userRepository, ConversationRepository conversationRepository, ConversationMapper conversationMapper) {
        this.userRepository = userRepository;
        this.conversationRepository = conversationRepository;
        this.conversationMapper = conversationMapper;
    }

    public boolean deleteConversationForUser(long userId, long conversationId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (!(optionalUser.isPresent() && conversationRepository.findById(conversationId).isPresent())) {
            return false;
        }

        User user = optionalUser.get();
//        Optional<Conversation> optionalConversation = user.getConversations().stream().filter(c -> c.getId() == conversationId).findAny();
//        if (!optionalConversation.isPresent()) {
//            return false;
//        }
//
//        user.getConversations().remove(optionalConversation.get());
        userRepository.save(user);
        return true;
    }

    public List<Conversation> getConversationForUser(long userId) throws IncorrectDataException {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (!optionalUser.isPresent()) {
            throw new IncorrectDataException();
        }


        return optionalUser.get().getConversations();
    }

    public boolean createConversationForUser(long userId, ConversationDto conversationDto) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            return false;
        }
        User user = optionalUser.get();
        Conversation conversation = conversationMapper.mapToDomain(conversationDto);
        List<User> withUsers = new ArrayList<>();
        for (User userFromDto: conversation.getWith()) {
            optionalUser = userRepository.findById(userFromDto.getId());
            if (optionalUser.isPresent()){
                withUsers.add(optionalUser.get());
            } else {
                return false;
            }
        }
        conversation.setWith(withUsers);
        conversationRepository.save(conversation);
        user.getConversations().add(conversation);
        userRepository.save(user);
        return true;
    }

    private final ConversationMapper conversationMapper;
    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;
}
