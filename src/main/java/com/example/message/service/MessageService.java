package com.example.message.service;

import com.example.message.domain.Conversation;
import com.example.message.domain.Message;
import com.example.message.domain.User;
import com.example.message.notifier.MessageNotifier;
import com.example.message.repository.ConversationRepository;
import com.example.message.repository.MessageRepository;
import com.example.message.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    public MessageService(MessageRepository messageRepository, MessageNotifier messageNotifier, ConversationRepository conversationRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.messageNotifier = messageNotifier;
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
    }

    public void onNewMessage(Message message) {
        message.setTimestamp(System.currentTimeMillis());
        messageRepository.save(message);
        createConversationsInOtherUsers(message);
        messageNotifier.notify(message);
    }

    public List<Message> getMessages(long conversationId) {
        return messageRepository.findAll().stream().filter(m -> m.getConversation().getId() == conversationId).collect(Collectors.toList());
    }

    private void createConversationsInOtherUsers(Message message) {
        for (User user: message.getConversation().getWith()) {
            List<Conversation> conversations = user.getConversations().stream().filter(c -> c.getWith().contains(message.getCreator())).collect(Collectors.toList());
            if (conversations.isEmpty()) {
                List<User> users = new ArrayList<>(message.getConversation().getWith());
                users.remove(user);
                users.add(message.getCreator());
                Conversation newConversation = new Conversation(users, true);
                conversationRepository.save(newConversation);
                Message newMessage = new Message(message.getContent(), message.getTimestamp(), message.getCreator(), newConversation);
                messageRepository.save(newMessage);
                user.getConversations().add(newConversation);
                userRepository.save(user);
            } else {
                Conversation conversation = conversations.get(0);
                conversation.setUnread(true);
                conversationRepository.save(conversation);
                Message newMessage = new Message(message.getContent(), message.getTimestamp(), message.getCreator(), conversation);
                messageRepository.save(newMessage);
            }
        }
    }

    private List<Message> sortByTimestamp(List<Message> messages){
        List<Message> sorted = new ArrayList<>(messages);
        sorted.sort(new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                if (o1.getTimestamp() > o2.getTimestamp()) {
                    return 1;
                } else if (o1.getTimestamp() == o2.getTimestamp()) {
                    return 0;
                }
                return -1;
            }
        });
        return sorted;
    }

    private final MessageRepository messageRepository;
    private final MessageNotifier messageNotifier;
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;
}
