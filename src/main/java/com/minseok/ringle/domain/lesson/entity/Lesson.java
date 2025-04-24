package com.minseok.ringle.domain.lesson.entity;

import com.minseok.ringle.domain.base.BaseTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Lesson extends BaseTime {

    @Id
    @Column(name = "lesson_id", nullable = false, updatable = false)
    @UuidGenerator
    private UUID id;

    @Column(name = "student_id", nullable = false, updatable = false)
    private UUID studentId;

    @Column(name = "tutor_id", nullable = false, updatable = false)
    private UUID tutorId;

    @Column(name = "is_finish", nullable = false, updatable = false)
    private boolean isFinish;

    @Enumerated(EnumType.STRING)
    @Column(name = "lesson_duration", nullable = false)
    private LessonDuration lessonDuration;

    @Column(name = "start_at", nullable = false)
    private LocalDateTime startAt;

    @Column(name = "end_at", nullable = false)
    private LocalDateTime endAt;
}
