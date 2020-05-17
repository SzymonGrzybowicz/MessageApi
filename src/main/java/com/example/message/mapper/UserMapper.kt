package com.example.message.mapper

import com.example.message.domain.User
import com.example.message.domain.dto.UserDto
import org.springframework.stereotype.Component

@Component
class UserMapper : IMapper<User, UserDto> {

    override fun mapToDomain(dto: UserDto): User {
        return User(
                dto.id,
                dto.name,
                dto.password,
                dto.mail,
                mutableListOf(),
                mutableListOf()
        )
    }

    override fun mapToDto(domain: User): UserDto {
        return UserDto(
                domain.id,
                domain.name,
                null,
                domain.mail
        )
    }
}