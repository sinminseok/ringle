package com.minseok.ringle.domain.student.service;

import com.minseok.ringle.domain.lesson.entity.Lesson;
import com.minseok.ringle.domain.lesson.mapper.LessonMapper;
import com.minseok.ringle.domain.lesson.repository.LessonRepository;
import com.minseok.ringle.domain.student.dto.LessonResponse;
import com.minseok.ringle.domain.tutor.entity.Tutor;
import com.minseok.ringle.domain.tutor.repository.TutorRepository;
import com.minseok.ringle.utils.OptionalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final LessonRepository lessonRepository;
    private final TutorRepository tutorRepository;
    private final LessonMapper lessonMapper;

    public List<LessonResponse> findApplyLessons(final UUID studentId) {
        return lessonRepository.findByStudentId(studentId).stream()
                .map(this::mapToLessonResponse)
                .sorted(Comparator.comparing(LessonResponse::getStartAt).reversed())
                .collect(Collectors.toList());
    }

    private LessonResponse mapToLessonResponse(final Lesson lesson) {
        Tutor tutor = OptionalUtil.getOrElseThrow(tutorRepository.findById(lesson.getTutorId()), "존재하지 않는 Tutor Id 입니다.");
        return lessonMapper.toLessonResponse(lesson, tutor);
    }
}
