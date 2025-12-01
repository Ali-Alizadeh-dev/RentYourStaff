package com.example.rentyourtool.entity;

import com.nimbusds.oauth2.sdk.Message;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chats")
@AllArgsConstructor
@NoArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Beziehung: Jeder Chat gehört zu genau einer Mietanfrage
    @OneToOne(fetch = FetchType. LAZY)
    @JoinColumn(name = "rental_request_id", nullable = false)
    private RentalRequest rentalRequest;

    // Beziehung: Chat zwischen Vermieter und Mieter
    @ManyToOne(fetch = FetchType. LAZY)
    @JoinColumn(name = "vermieter_id", nullable = false)
    private User teilnehmerVermieter;

    @ManyToOne(fetch = FetchType. LAZY)
    @JoinColumn(name = "mieter_id", nullable = false)
    private User teilnehmerMieter;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "last_message_at")
    private LocalDateTime lastMessageAt;

    // Beziehung: Ein Chat hat viele Nachrichten
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, fetch = FetchType. LAZY)
    @OrderBy("timestamp ASC") // Nachrichten chronologisch sortiert
    private List<MessageChat> messages = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        lastMessageAt = LocalDateTime. now();
    }


}