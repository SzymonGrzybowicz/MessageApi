package com.example.message.service;

import com.example.message.domain.Conversation;
import com.example.message.domain.User;
import com.example.message.repository.ConversationRepository;
import com.example.message.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConversationService {

    public ConversationService(UserRepository userRepository, ConversationRepository conversationRepository) {
        this.userRepository = userRepository;
        this.conversationRepository = conversationRepository;
    }

    public boolean deleteConversationForUser(long userId, long conversationId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (!(optionalUser.isPresent() && conversationRepository.findById(conversationId).isPresent())) {
            return false;
        }

        User user = optionalUser.get();
        Optional<Conversation> optionalConversation = user.getConversations().stream().filter(c -> c.getId() == conversationId).findAny();
        if (!optionalConversation.isPresent()) {
            return false;
        }

        user.getConversations().remove(optionalConversation.get());
        userRepository.save(user);
        return true;
    }

    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;
}
