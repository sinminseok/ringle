package com.minseok.ringle.domain.lesson.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Builder
@Setter
@AllArgsConstructor
public class AvailabilityTimeResponse {

    private LocalDateTime time;

    private boolean canLesson;
}
