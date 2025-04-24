package com.minseok.ringle.domain.lesson.dto;

import com.minseok.ringle.domain.lesson.entity.LessonDuration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class AvailabilityTimeSearchRequest {

    private LocalDate localDate;

    private LessonDuration lessonDuration;
}
