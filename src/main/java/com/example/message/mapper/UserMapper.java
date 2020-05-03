package com.example.message.mapper;

import com.example.message.domain.User;
import com.example.message.domain.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements IMapper<User, UserDto> {

    @Override
    public User mapToDomain(UserDto userDto) {
        return new User(userDto.getId(), userDto.getName(), userDto.getPassword(), userDto.getMail());
    }

    @Override
    public UserDto mapToDto(User user) {
        return new UserDto(user.getId(), user.getName(), null, user.getMail());
    }
}
