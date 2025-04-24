package com.minseok.ringle.domain.tutor.repository;

import com.minseok.ringle.domain.lesson.entity.QLesson;
import com.minseok.ringle.domain.tutor.entity.AvailabilityTime;
import com.minseok.ringle.domain.tutor.entity.QAvailabilityTime;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.TimePath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;



@Repository
@RequiredArgsConstructor
public class CustomAvailabilityTimeeRepositoryImpl implements CustomAvailabilityTimeeRepository {

    private static final String TIME_FORMAT = "TIME({0})";
    private final JPAQueryFactory queryFactory;

    @Override
    public List<AvailabilityTime> findAvailableTimes(DayOfWeek dayOfWeek) {
        QAvailabilityTime availabilityTime = QAvailabilityTime.availabilityTime;
        QLesson lesson = QLesson.lesson;
        return queryFactory
                .selectFrom(availabilityTime)
                .where(
                        availabilityTime.dayOfWeek.eq(dayOfWeek)
                                .and(getNoOverlappingLessonsExpression(lesson, availabilityTime))
                )
                .fetch();
    }

    private BooleanExpression getNoOverlappingLessonsExpression(QLesson lesson, QAvailabilityTime availabilityTime) {
        return JPAExpressions
                .selectOne()
                .from(lesson)
                .where(
                        lesson.tutorId.eq(availabilityTime.tutor.id)
                                .and(isBeforeEndTime(lesson.startAt, availabilityTime.endAt))
                                .and(isAfterStartTime(lesson.startAt, availabilityTime.endAt)))
                .notExists();
    }

    private BooleanExpression isBeforeEndTime(DateTimePath startAt, TimePath endAt) {
        return Expressions.stringTemplate(TIME_FORMAT, startAt)
                .lt(Expressions.stringTemplate(TIME_FORMAT, endAt));
    }

    private BooleanExpression isAfterStartTime(DateTimePath startAt, TimePath endAt) {
        return Expressions.stringTemplate(TIME_FORMAT, startAt)
                .gt(Expressions.stringTemplate(TIME_FORMAT, endAt));
    }
}
