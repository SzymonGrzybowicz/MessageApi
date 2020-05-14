package com.example.message.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class ConversationDto {

    @JsonProperty(value = "conversation_id")
    private final long id;

    @JsonProperty(value = "with")
    private final List<UserDto> with;

}
