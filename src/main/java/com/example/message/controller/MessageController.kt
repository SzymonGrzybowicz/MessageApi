package com.example.message.controller

import com.example.message.domain.dto.MessageDto
import com.example.message.mapper.MessageMapper
import com.example.message.service.MessageService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
@RequestMapping("/message")
class MessageController(
        private val messageService: MessageService,
        private val messageMapper: MessageMapper
) {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun onNewMessage(@RequestBody messageDto: MessageDto) {
        messageService.onNewMessage(messageMapper.mapToDomain(messageDto))
    }

    @GetMapping(value = ["/{conversationId}"])
    fun getMessagesForConversation(@PathVariable conversationId: Long): List<MessageDto> {
        return messageMapper.mapToDtoList(messageService.getMessagesForConversation(conversationId))
    }
}