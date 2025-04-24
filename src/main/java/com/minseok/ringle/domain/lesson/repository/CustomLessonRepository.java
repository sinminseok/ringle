package com.minseok.ringle.domain.lesson.repository;

import com.minseok.ringle.domain.lesson.entity.Lesson;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface CustomLessonRepository {

    List<Lesson> findByStudentId(final UUID studentId);

    boolean isAlreadyReservation(final UUID tutorId, final LocalTime startAt, final LocalTime endAt, final LocalDate localDate);

    boolean isAlreadyReservationByDateTime(final LocalDateTime startAt, final LocalDateTime endAt);
}
