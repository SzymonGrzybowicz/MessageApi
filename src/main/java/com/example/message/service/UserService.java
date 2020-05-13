package com.example.message.service;

import com.example.message.domain.FirebaseAuthToken;
import com.example.message.domain.User;
import com.example.message.domain.dto.UserDto;
import com.example.message.mapper.UserMapper;
import com.example.message.repository.FirebaseAuthTokenRepository;
import com.example.message.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    public UserService(UserRepository repository, UserMapper mapper, FirebaseAuthTokenRepository firebaseRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.firebaseRepository = firebaseRepository;
    }

    public boolean createUser(UserDto userDto) {
        if (repository.findByMail(userDto.getMail()) != null) {
            return false;
        }
        User user = mapper.mapToDomain(userDto);
        repository.save(user);
        return true;
    }

    public UserDto loginUser(UserDto userDto) {
        User user = repository.findByMail(userDto.getMail());
        if (user != null && user.getPassword().equals(userDto.getPassword())) {
            return mapper.mapToDto(user);
        }
        return null;
    }

    public UserDto getUserByMail(String mail) {
        User user = repository.findByMail(mail);
        if (user != null) {
            return mapper.mapToDto(user);
        }
        return null;
    }


    public void addFirebaseAuthToken(long userId, String authToken) {
        Optional<User> optionalUser = repository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            boolean contains = user.getFirebaseAuthTokens().stream().anyMatch(a -> a.getFirebaseAuthToken().equals(authToken));
            if (contains) {
                return;
            }
            List<FirebaseAuthToken> tokens = firebaseRepository.findAll().stream().filter(a -> a.getFirebaseAuthToken().equals(authToken)).collect(Collectors.toList());
            for (FirebaseAuthToken token: tokens) {
                firebaseRepository.delete(token);
            }
            FirebaseAuthToken token = firebaseRepository.save(new FirebaseAuthToken(authToken));
            user.getFirebaseAuthTokens().add(token);
            repository.save(user);
        }
    }

    public List<UserDto> getAllUsers() {
        return mapper.mapToDtoList(repository.findAll());
    }

    private final UserRepository repository;
    private final UserMapper mapper;
    private final FirebaseAuthTokenRepository firebaseRepository;
}
