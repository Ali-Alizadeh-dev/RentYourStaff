package com.example.rentyourtool.repository;

import com.example.rentyourtool.entity.MessageChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// MessageRepository
@Repository
public interface MessageRepository extends JpaRepository<MessageChat, Long> {
}
