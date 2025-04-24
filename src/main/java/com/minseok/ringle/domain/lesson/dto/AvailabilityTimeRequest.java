package com.minseok.ringle.domain.lesson.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@Builder
@AllArgsConstructor
public class AvailabilityTimeRequest {

    private DayOfWeek dayOfWeek;

    private LocalTime startAt;

    private LocalTime endAt;
}
