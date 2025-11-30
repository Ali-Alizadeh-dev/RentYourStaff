package com.example.rentyourtool.repository;

import com.example.rentyourtool.entity.Chat;
import com.example.rentyourtool.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// ChatRepository
@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

}