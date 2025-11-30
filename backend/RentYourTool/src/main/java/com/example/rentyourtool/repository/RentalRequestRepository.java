package com.example.rentyourtool.repository;

import com.example.rentyourtool.entity.RentalRequest;
import com.example.rentyourtool.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// RentalRequestRepository
@Repository
public interface RentalRequestRepository extends JpaRepository<RentalRequest, Long> {

}
