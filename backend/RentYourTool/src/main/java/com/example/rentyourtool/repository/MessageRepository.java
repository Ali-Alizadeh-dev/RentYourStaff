package com.example.rentyourtool.repository;

// MessageRepository
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChatOrderByTimestampAsc(Chat chat);
}