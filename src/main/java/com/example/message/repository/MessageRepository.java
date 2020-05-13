package com.example.message.repository;

import com.example.message.domain.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MessageRepository extends CrudRepository<Message, Long> {
    @Override
    List<Message> findAll();
}
