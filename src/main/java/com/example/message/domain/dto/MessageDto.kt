package com.example.message.domain.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class MessageDto(

        @JsonProperty(value = "id")
        val id: Long,

        @JsonProperty(value = "content")
        val content: String,

        @JsonProperty(value = "conversation_id")
        val conversationId: Long?,

        @JsonProperty(value = "creator_id")
        val creatorID: Long?,

        @JsonProperty(value = "timestamp")
        val timestamp: Long
)