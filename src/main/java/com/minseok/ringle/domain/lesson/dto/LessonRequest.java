package com.minseok.ringle.domain.lesson.dto;

import com.minseok.ringle.domain.lesson.entity.LessonDuration;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class LessonRequest {
    private UUID tutorId;

    private UUID studentId;

    private LessonDuration lessonDuration;

    private LocalDateTime startAt;
}
