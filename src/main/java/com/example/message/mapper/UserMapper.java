package com.example.message.mapper;

import com.example.message.domain.User;
import com.example.message.domain.dto.UserDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User mapToUser(UserDto userDto) {
        return new User(userDto.getId(), userDto.getName(), userDto.getPassword(), userDto.getMail());
    }

    public UserDto mapToUserDto(User user) {
        return new UserDto(user.getId(), user.getName(), null, user.getMail());
    }

    public List<User> mapToUserList(List<UserDto> userDtoList) {
        return userDtoList.stream().map(this::mapToUser).collect(Collectors.toList());
    }

    public List<UserDto> mapToUserDtoList(List<User> userList) {
        return userList.stream().map(this::mapToUserDto).collect(Collectors.toList());
    }
}
