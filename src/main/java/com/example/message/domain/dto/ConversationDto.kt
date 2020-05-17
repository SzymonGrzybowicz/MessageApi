package com.example.message.domain.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ConversationDto(

        @JsonProperty(value = "conversation_id")
        val id: Long,

        @JsonProperty(value = "with")
        val with: List<UserDto>,

        @JsonProperty(value = "unread")
        val unread: Boolean
)