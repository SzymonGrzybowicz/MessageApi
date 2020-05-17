package com.example.message.controller

import com.example.message.domain.dto.ConversationDto
import com.example.message.exception.IncorrectDataException
import com.example.message.mapper.ConversationMapper
import com.example.message.service.ConversationService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.HttpServletResponse

@RestController
@CrossOrigin("*")
@RequestMapping("/conversation")
class ConversationController(
        private val conversationService: ConversationService,
        private val conversationMapper: ConversationMapper
) {

    @GetMapping(value = ["/{userId}"])
    fun getConversationsForUser(@PathVariable userId: Long, response: HttpServletResponse): List<ConversationDto> {
        return try {
            conversationMapper.mapToDtoList(conversationService.getConversationForUser(userId))
        } catch (e: IncorrectDataException) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
            ArrayList()
        }
    }

    @PostMapping(value = ["/{userId}"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createConversationForUser(@PathVariable userId: Long, @RequestBody conversationDto: ConversationDto) {
        conversationService.createConversationForUser(userId, conversationDto)
    }

    @DeleteMapping(value = ["/{userId}/{conversationId}"])
    fun deleteConversationForUser(@PathVariable userId: Long, @PathVariable conversationId: Long, response: HttpServletResponse) {
        if (!conversationService.deleteConversationForUser(userId, conversationId)) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
        }
    }

    @PatchMapping(value = ["/markRead/{conversationId}"])
    fun markConversationAsRead(@PathVariable conversationId: Long) {
        conversationService.markAsRead(conversationId)
    }
}