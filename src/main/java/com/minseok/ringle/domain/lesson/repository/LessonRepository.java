package com.minseok.ringle.domain.lesson.repository;

import com.minseok.ringle.domain.lesson.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, UUID>, CustomLessonRepository {
}


