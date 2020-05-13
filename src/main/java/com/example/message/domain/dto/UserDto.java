package com.example.message.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class UserDto {

    @JsonProperty(value = "id")
    private final long id;

    @JsonProperty(value = "name")
    private final String name;

    @JsonProperty(value = "password")
    private final String password;

    @JsonProperty(value = "mail")
    private final String mail;
}
