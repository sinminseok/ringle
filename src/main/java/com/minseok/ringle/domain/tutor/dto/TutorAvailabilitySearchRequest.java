package com.minseok.ringle.domain.tutor.dto;

import com.minseok.ringle.domain.lesson.entity.LessonDuration;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TutorAvailabilitySearchRequest {

    private LocalDateTime localDateTime;

    private LessonDuration lessonDuration;
}
