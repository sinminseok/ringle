package com.minseok.ringle.domain.tutor.repository;

import com.minseok.ringle.domain.lesson.entity.LessonDuration;
import com.minseok.ringle.domain.tutor.entity.Tutor;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomTutorRepository {

    List<Tutor> findOnDayOfWeek(final LocalDateTime current, final LessonDuration lessonDuration);
}
