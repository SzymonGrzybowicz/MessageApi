package com.example.message.controller;

import com.example.message.domain.Conversation;
import com.example.message.domain.dto.ConversationDto;
import com.example.message.exception.IncorrectDataException;
import com.example.message.mapper.ConversationMapper;
import com.example.message.service.ConversationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/conversation")
public class ConversationController {

    public ConversationController(ConversationService conversationService, ConversationMapper conversationMapper) {
        this.conversationService = conversationService;
        this.conversationMapper = conversationMapper;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public List<ConversationDto> getConversationsForUser(@PathVariable long userId, HttpServletResponse response) {
        try {
            return conversationMapper.mapToDtoList(conversationService.getConversationForUser(userId));
        } catch (IncorrectDataException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return new ArrayList<>();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createConversationForUser(@PathVariable long userId, @RequestBody ConversationDto conversationDto) {
        conversationService.createConversationForUser(userId, conversationDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}/{conversationId}")
    public void deleteConversationForUser(@PathVariable long userId, @PathVariable long conversationId, HttpServletResponse response){
        if (!conversationService.deleteConversationForUser(userId, conversationId)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/markRead/{conversationId}")
    public void markConversationAsRead(@PathVariable long conversationId){
        conversationService.markAsRead(conversationId);
    }

    private final ConversationService conversationService;
    private final ConversationMapper conversationMapper;
}
