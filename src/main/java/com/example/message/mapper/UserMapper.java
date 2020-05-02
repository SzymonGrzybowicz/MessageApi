package com.example.message.mapper;

import com.example.message.domain.User;
import com.example.message.domain.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToUser(UserDto userDto) {
        return new User(userDto.getName(), userDto.getPassword(), userDto.getMail());
    }

    public UserDto mapToUserDto(User user) {
        return new UserDto(user.getName(), null, user.getMail());
    }
}
