package com.minseok.ringle.domain.tutor.repository;

import com.minseok.ringle.domain.tutor.entity.AvailabilityTime;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface CustomAvailabilityTimeeRepository {

    List<AvailabilityTime> findAvailableTimes(DayOfWeek dayOfWeek);
}
