package com.minseok.ringle.domain.lesson.repository;

import com.minseok.ringle.domain.lesson.entity.Lesson;
import com.minseok.ringle.domain.lesson.entity.QLesson;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CustomLessonRepositoryImpl implements CustomLessonRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Lesson> findByStudentId(UUID studentId) {
        QLesson lesson = QLesson.lesson;

        return queryFactory
                .selectFrom(lesson)
                .where(lesson.studentId.eq(studentId), lesson.isFinish.eq(false))
                .fetch();
    }

    @Override
    public boolean isAlreadyReservation(UUID tutorId, LocalTime startAt, LocalTime endAt, LocalDate localDate) {
        QLesson lesson = QLesson.lesson;

        LocalDateTime newLessonStart = LocalDateTime.of(localDate, startAt);
        LocalDateTime newLessonEnd = LocalDateTime.of(localDate, endAt);

        return queryFactory
                .selectOne()
                .from(lesson)
                .where(
                        lesson.tutorId.eq(tutorId),
                        lesson.startAt.lt(newLessonEnd),
                        lesson.endAt.gt(newLessonStart)
                )
                .fetchFirst() != null;
    }

    @Override
    public boolean isAlreadyReservationByDateTime(LocalDateTime startAt, LocalDateTime endAt) {
        QLesson lesson = QLesson.lesson;
        return queryFactory
                .selectOne()
                .from(lesson)
                .where(lesson.startAt.lt(endAt), lesson.endAt.gt(startAt))
                .fetchFirst() != null;
    }
}
