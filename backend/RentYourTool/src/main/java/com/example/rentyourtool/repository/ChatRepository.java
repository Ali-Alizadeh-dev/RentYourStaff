package com.example.rentyourtool.repository;

// ChatRepository
@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByTeilnehmerVermieterOrTeilnehmerMieterOrderByLastMessageAtDesc(User vermieter, User mieter);
}