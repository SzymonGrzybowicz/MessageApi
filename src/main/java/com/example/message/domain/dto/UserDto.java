package com.example.message.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class UserDto {

    @JsonProperty(value = "name")
    private final String name;

    @JsonProperty(value = "password")
    private final String password;

    @JsonProperty(value = "mail")
    private final String mail;
}
