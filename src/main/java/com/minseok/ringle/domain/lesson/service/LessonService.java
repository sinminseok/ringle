package com.minseok.ringle.domain.lesson.service;

import com.minseok.ringle.domain.lesson.dto.AvailabilityTimeResponse;
import com.minseok.ringle.domain.lesson.dto.AvailabilityTimeSearchRequest;
import com.minseok.ringle.domain.lesson.dto.LessonRequest;
import com.minseok.ringle.domain.lesson.entity.Lesson;
import com.minseok.ringle.domain.lesson.mapper.LessonMapper;
import com.minseok.ringle.domain.lesson.repository.LessonRepository;
import com.minseok.ringle.domain.tutor.entity.AvailabilityTime;
import com.minseok.ringle.domain.tutor.repository.AvailabilityTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final AvailabilityTimeRepository availabilityTimeRepository;
    private final LessonMapper lessonMapper;


    public List<AvailabilityTimeResponse> findAvailabilityTimes(final AvailabilityTimeSearchRequest request) {
        LocalDateTime selectDay = request.getLocalDate().atStartOfDay();
        List<AvailabilityTime> availableTimes = availabilityTimeRepository.findAvailableTimes(request.getLocalDate().getDayOfWeek());
        return IntStream.range(0, 48)
                .mapToObj(i -> selectDay.plusMinutes(i * 30))
                .map(current -> AvailabilityTimeResponse.builder()
                        .time(current)
                        .canLesson(availableTimes.stream().anyMatch(time -> isWithinAvailableRange(current.toLocalTime(), time.getStartAt(), time.getEndAt()) && !isAlreadyReservation(time.getStartAt(), time.getEndAt(), request.getLocalDate())))
                        .build())
                .collect(Collectors.toList());
    }

    public void applyLesson(final LessonRequest lessonRequest) {
        validateReservation(lessonRequest);
        lessonRepository.save(lessonMapper.toLesson(lessonRequest));
    }

    private boolean isWithinAvailableRange(LocalTime target, LocalTime start, LocalTime end) {
        return !target.isBefore(start) && target.isBefore(end);
    }

    private void validateReservation(LessonRequest lessonRequest){
        LocalDateTime startAt = lessonRequest.getStartAt();
        LocalDateTime endAt = lessonRequest.getStartAt().plusMinutes(lessonRequest.getLessonDuration().getTime());
        if(lessonRepository.isAlreadyReservation(lessonRequest.getTutorId(),startAt.toLocalTime(), endAt.toLocalTime(), lessonRequest.getStartAt().toLocalDate())){
            throw new IllegalArgumentException("이미 신청된 시간입니다.");
        }
    }

    private boolean isAlreadyReservation(LocalTime startAt, LocalTime endAt, LocalDate date) {
        LocalDateTime startDateTime = LocalDateTime.of(date, startAt);
        LocalDateTime endDateTime = LocalDateTime.of(date, endAt);
        return lessonRepository.isAlreadyReservationByDateTime(startDateTime, endDateTime);
    }
}