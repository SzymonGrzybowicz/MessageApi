package com.example.message.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class MessageDto {

    @JsonProperty(value = "id")
    private final long id;

    @JsonProperty(value = "content")
    private final String content;

    @JsonProperty(value = "timestamp")
    private final long timestamp;

    @JsonProperty(value = "creator_id")
    private final long creatorID;

    @JsonProperty(value = "conversation_id")
    private final long conversationId;
}
