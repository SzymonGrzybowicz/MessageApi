package com.example.message.service;

import com.example.message.domain.Message;
import com.example.message.notifier.MessageNotifier;
import com.example.message.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    public MessageService(MessageRepository messageRepository, MessageNotifier messageNotifier) {
        this.messageRepository = messageRepository;
        this.messageNotifier = messageNotifier;
    }

    public void onNewMessage(Message message) {
        message.setTimestamp(System.currentTimeMillis());
        messageRepository.save(message);
        messageNotifier.notify(message);
    }

    public List<Message> getMessages(long conversationId) {
        return messageRepository.findAll().stream().filter(m -> m.getConversation().getId() == conversationId).collect(Collectors.toList());
    }

    private final MessageRepository messageRepository;
    private final MessageNotifier messageNotifier;
}
