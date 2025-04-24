package com.minseok.ringle.domain.tutor.entity;

import com.minseok.ringle.domain.base.BaseTime;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AvailabilityTime extends BaseTime {

    @Id
    @Column(name = "availability_time_id", nullable = false, updatable = false)
    @UuidGenerator
    private UUID id;

    @Column(name = "day_of_week", nullable = false, updatable = true)
    private DayOfWeek dayOfWeek;

    @Column(name = "start_at", nullable = false)
    private LocalTime startAt;

    @Column(name = "end_at", nullable = false)
    private LocalTime endAt;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    public boolean isOverlappingWith(final LocalTime startAt, final LocalTime endAt) {
        return startAt.isBefore(this.endAt) && endAt.isAfter(this.startAt);
    }
}

