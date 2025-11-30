package com.example.rentyourtool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
    @Entity
    @Table(name = "rental_requests")
    public class RentalRequest {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "inserat_id", nullable = false)
        private Listing inserat;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "mieter_id", nullable = false)
        private User mieter;

        @Column(name = "start_datum", nullable = false)
        private LocalDate startDatum;

        @Column(name = "end_datum", nullable = false)
        private LocalDate endDatum;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private RequestStatus status;

        @Column(name = "created_at", nullable = false)
        private LocalDateTime createdAt;

        @Column(name = "updated_at")
        private LocalDateTime updatedAt;

        @OneToOne(mappedBy = "rentalRequest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Chat chat;

        @PrePersist
        protected void onCreate() {
            createdAt = LocalDateTime.now();
            updatedAt = LocalDateTime.now();
        }

        @PreUpdate
        protected void onUpdate() {
            updatedAt = LocalDateTime.now();
        }


    public enum RequestStatus {
        OFFEN,
        ZUGESAGT,
        ABGELEHNT
    }

    }


