package com.example.message.controller;

import com.example.message.service.ConversationService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class ConversationController {

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}/{conversationId}")
    public void deleteConversationForUser(@PathVariable long userId, @PathVariable long conversationId, HttpServletResponse response){
        if (!conversationService.deleteConversationForUser(userId, conversationId)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private final ConversationService conversationService;
}
