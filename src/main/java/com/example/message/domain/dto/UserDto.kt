package com.example.message.domain.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class UserDto(

        @JsonProperty(value = "id")
        val id: Long,

        @JsonProperty(value = "name")
        val name: String,

        @JsonProperty(value = "password")
        val password: String?,

        @JsonProperty(value = "mail")
        val mail: String
)