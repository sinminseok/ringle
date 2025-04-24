package com.minseok.ringle.domain.lesson.mapper;

import com.minseok.ringle.domain.lesson.dto.LessonRequest;
import com.minseok.ringle.domain.lesson.entity.Lesson;
import com.minseok.ringle.domain.student.dto.LessonResponse;
import com.minseok.ringle.domain.tutor.entity.Tutor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LessonMapper {


    @Mapping(target = "lessonId", source = "lesson.id")
    @Mapping(target = "startAt", source = "lesson.startAt")
    @Mapping(target = "endAt", source = "lesson.endAt")
    @Mapping(target = "tutorProfileUrl", source = "tutor.profileUrl")
    @Mapping(target = "tutorName", source = "tutor.name")
    @Mapping(target = "job", source = "tutor.job")
    @Mapping(target = "schoolName", source = "tutor.schoolName")
    LessonResponse toLessonResponse(final Lesson lesson, final Tutor tutor);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "endAt", expression = "java(calculateLessonEndAt(lessonRequest))")
    @Mapping(target = "isFinish", expression = "java(false)")
    Lesson toLesson(final LessonRequest lessonRequest);

    @Named("calculateLessonEndAt")
    default LocalDateTime calculateLessonEndAt(LessonRequest lessonRequest) {
        return lessonRequest.getStartAt().plusMinutes(lessonRequest.getLessonDuration().getTime());
    }
}
