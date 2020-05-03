package com.example.message.service;

import com.example.message.domain.User;
import com.example.message.domain.dto.UserDto;
import com.example.message.mapper.UserMapper;
import com.example.message.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public boolean createUser(UserDto userDto) {
        if (repository.findByMail(userDto.getMail()) != null) {
            return false;
        }
        User user = mapper.mapToDomain(userDto);
        repository.save(user);
        return true;
    }

    public UserDto getUserByMail(String mail) {
        User user = repository.findByMail(mail);
        if (user != null) {
            return mapper.mapToDto(user);
        }
        return null;
    }

    private final UserRepository repository;
    private final UserMapper mapper;
}
