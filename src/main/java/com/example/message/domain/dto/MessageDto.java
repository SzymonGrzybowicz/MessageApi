package com.example.message.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    public MessageDto(String content, long timestamp, long creatorID, long conversationId) {
        this.content = content;
        this.timestamp = timestamp;
        this.creatorID = creatorID;
        this.conversationId = conversationId;
    }

    @JsonProperty(value = "id")
    private long id;

    @JsonProperty(value = "content")
    private String content;

    @JsonProperty(value = "timestamp")
    private long timestamp;

    @JsonProperty(value = "creator_id")
    private long creatorID;

    @JsonProperty(value = "conversation_id")
    private long conversationId;
}
