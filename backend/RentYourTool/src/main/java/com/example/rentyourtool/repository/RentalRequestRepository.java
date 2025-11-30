package com.example.rentyourtool.repository;

// RentalRequestRepository
@Repository
public interface RentalRequestRepository extends JpaRepository<RentalRequest, Long> {
    List<RentalRequest> findByMieterOrderByCreatedAtDesc(User mieter);
    List<RentalRequest> findByInserat_VermieterOrderByCreatedAtDesc(User vermieter);
}
