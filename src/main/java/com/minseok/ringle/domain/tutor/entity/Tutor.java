package com.minseok.ringle.domain.tutor.entity;

import com.minseok.ringle.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tutor extends User {

    @Id
    @Column(name = "tutor_id", nullable = false, updatable = false)
    @UuidGenerator
    private UUID id;

    @Column(name = "school_name", nullable = true)
    private String schoolName;

    @Column(name = "job", nullable = true)
    private String job;

    @Builder.Default
    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilityTime> availabilityTimes = new ArrayList<>();


    public void addAvailabilityTime(final AvailabilityTime availabilityTime) {
        availabilityTimes.add(availabilityTime);
    }

    public void removeAvailabilityTime(final AvailabilityTime availabilityTime) {
        availabilityTimes.remove(availabilityTime);
    }

    public boolean hasOverlappingAvailabilityTime(final LocalTime startAt, final LocalTime endAt) {
        return availabilityTimes.stream()
                .anyMatch(availabilityTime -> availabilityTime.isOverlappingWith(startAt, endAt));
    }
}
