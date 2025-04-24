package com.minseok.ringle.domain.tutor.repository;

import com.minseok.ringle.domain.tutor.entity.AvailabilityTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AvailabilityTimeRepository extends JpaRepository<AvailabilityTime, UUID>, CustomAvailabilityTimeeRepository {
}
