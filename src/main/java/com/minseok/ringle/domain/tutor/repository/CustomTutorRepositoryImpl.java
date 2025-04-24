package com.minseok.ringle.domain.tutor.repository;

import com.minseok.ringle.domain.lesson.entity.LessonDuration;
import com.minseok.ringle.domain.tutor.entity.QAvailabilityTime;
import com.minseok.ringle.domain.tutor.entity.QTutor;
import com.minseok.ringle.domain.tutor.entity.Tutor;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomTutorRepositoryImpl implements CustomTutorRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Tutor> findOnDayOfWeek(final LocalDateTime current, final LessonDuration lessonDuration) {
        QAvailabilityTime availabilityTime = QAvailabilityTime.availabilityTime;

        return queryFactory
                .selectFrom(QTutor.tutor)
                .distinct()
                .join(QTutor.tutor.availabilityTimes, availabilityTime).fetchJoin()
                .where(availabilityTime.dayOfWeek.eq(current.getDayOfWeek()))
                .fetch();
    }
}
