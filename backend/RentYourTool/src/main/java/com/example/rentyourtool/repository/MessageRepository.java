package com.example.rentyourtool.repository;

import com.nimbusds.oauth2.sdk.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// MessageRepository
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}