package com.example.message.repository;

import com.example.message.domain.Conversation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConversationRepository extends CrudRepository<Conversation, Long> {
    @Override
    List<Conversation> findAll();
}
