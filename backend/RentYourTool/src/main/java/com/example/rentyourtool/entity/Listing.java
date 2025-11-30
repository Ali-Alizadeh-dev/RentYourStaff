package com.example.rentyourtool.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;


@Entity
@Table(name = "listings")
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Beziehung: Jedes Listing gehört einem Vermieter (User)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vermieter_id", nullable = false)
    private User vermieter;

    @Column(nullable = false)
    private String werkzeugart;

    @Column(name = "preis_pro_tag", nullable = false, precision = 10, scale = 2)
    private BigDecimal preisProTag;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ListingStatus status;

    @Column(nullable = false)
    private String ort;

    @ElementCollection
    @CollectionTable(name = "listing_bilder", joinColumns = @JoinColumn(name = "listing_id"))
    @Column(name = "bild_url")
    private List<String> bilder = new ArrayList<>();

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Beziehung: Ein Listing kann viele Mietanfragen haben
    @OneToMany(mappedBy = "inserat", cascade = CascadeType.ALL, fetch = FetchType. LAZY)
    private List<RentalRequest> rentalRequests = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime. now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime. now();
    }

    public enum ListingStatus {
        VERFUEGBAR,
        VERMIETET
    }
}



