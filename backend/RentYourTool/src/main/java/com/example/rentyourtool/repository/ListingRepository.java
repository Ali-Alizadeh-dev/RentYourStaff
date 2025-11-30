package com.example.rentyourtool.repository;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {
    List<Listing> findByStatusAndOrtContainingIgnoreCase(ListingStatus status, String ort);
    List<Listing> findByVermieterAndStatus(User vermieter, ListingStatus status);
}