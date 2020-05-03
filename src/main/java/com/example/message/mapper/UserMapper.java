package com.example.message.mapper;

import com.example.message.domain.User;
import com.example.message.domain.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements IMapper<User, UserDto> {

    @Autowired
    public UserMapper(ConversationMapper conversationMapper) {
        this.conversationMapper = conversationMapper;
    }

    @Override
    public User mapToDomain(UserDto userDto) {
        return new User(userDto.getId(), userDto.getName(), userDto.getPassword(), userDto.getMail());
    }

    @Override
    public UserDto mapToDto(User user) {
        return new UserDto(user.getId(), user.getName(), null, user.getMail(), conversationMapper.mapToDtoList(user.getConversations()));
    }

    private final ConversationMapper conversationMapper;
}
