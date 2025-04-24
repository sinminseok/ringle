package com.minseok.ringle.domain.student.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@Setter
@AllArgsConstructor
public class LessonResponse {

    private UUID lessonId;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    private String tutorProfileUrl;

    private String tutorName;

    private String job;

    private String schoolName;
}
