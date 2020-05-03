package com.example.message.controller;

import com.example.message.domain.dto.MessageDto;
import com.example.message.mapper.MessageMapper;
import com.example.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/message")
public class MessageController {

    @Autowired
    public MessageController(MessageService messageService, MessageMapper messageMapper) {
        this.messageService = messageService;
        this.messageMapper = messageMapper;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void getConversationsForUser(@RequestBody MessageDto messageDto) {
        messageService.onNewMessage(messageMapper.mapToDomain(messageDto));
    }

    private final MessageService messageService;
    private final MessageMapper messageMapper;
}
