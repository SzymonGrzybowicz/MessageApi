package com.example.message.repository;

import com.example.message.domain.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface MessageRepository extends CrudRepository<Message, Long> {
}
