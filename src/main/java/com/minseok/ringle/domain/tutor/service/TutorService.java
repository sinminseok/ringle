package com.minseok.ringle.domain.tutor.service;

import com.minseok.ringle.domain.lesson.dto.AvailabilityTimeRequest;
import com.minseok.ringle.domain.lesson.dto.AvailabilityTimeResponse;
import com.minseok.ringle.domain.lesson.entity.LessonDuration;
import com.minseok.ringle.domain.lesson.repository.LessonRepository;
import com.minseok.ringle.domain.tutor.dto.TutorAvailabilitySearchRequest;
import com.minseok.ringle.domain.tutor.dto.TutorOverviewResponse;
import com.minseok.ringle.domain.tutor.entity.AvailabilityTime;
import com.minseok.ringle.domain.tutor.entity.Tutor;
import com.minseok.ringle.domain.tutor.mapper.AvailabilityTimeMapper;
import com.minseok.ringle.domain.tutor.mapper.TutorMapper;
import com.minseok.ringle.domain.tutor.repository.TutorRepository;
import com.minseok.ringle.utils.OptionalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TutorService {

    private final LessonRepository lessonRepository;
    private final TutorRepository tutorRepository;
    private final TutorMapper tutorMapper;
    private final AvailabilityTimeMapper availabilityTimeMapper;

    public void saveAvailabilityTime(final UUID tutorId, final AvailabilityTimeRequest timeRequest) {
        Tutor tutor = getTutor(tutorId);
        validateOverlappingAvailability(tutor, timeRequest);
        generateAvailabilityTimes(tutor, timeRequest).forEach(tutor::addAvailabilityTime);
    }

    public void deleteAvailabilityTime(final UUID tutorId, final AvailabilityTimeRequest timeRequest) {
        Tutor tutor = getTutor(tutorId);
        findMatchingAvailabilityTimes(tutor, timeRequest).forEach(tutor::removeAvailabilityTime);
    }

    public List<TutorOverviewResponse> findAvailabilityTutor(final TutorAvailabilitySearchRequest request) {
        LocalDateTime time = request.getLocalDateTime();
        LessonDuration duration = request.getLessonDuration();

        return tutorRepository.findOnDayOfWeek(time, duration).stream()
                .map(tutor -> tutorMapper.toTutorOverviewResponse(
                        tutor, generateTimeResponses(tutor, time, duration, time.toLocalDate())))
                .collect(Collectors.toList());
    }

    private Tutor getTutor(UUID tutorId) {
        return OptionalUtil.getOrElseThrow(tutorRepository.findById(tutorId), "존재하지 않는 튜터 ID 입니다.");
    }

    private void validateOverlappingAvailability(Tutor tutor, AvailabilityTimeRequest timeRequest) {
        if (tutor.hasOverlappingAvailabilityTime(timeRequest.getStartAt(), timeRequest.getEndAt())) {
            throw new IllegalArgumentException("중복되는 시간을 설정할 수 없습니다.");
        }
    }

    private List<AvailabilityTime> generateAvailabilityTimes(Tutor tutor, AvailabilityTimeRequest request) {
        List<AvailabilityTime> times = new ArrayList<>();
        for (LocalTime time = request.getStartAt(); !time.isAfter(request.getEndAt().minusMinutes(30)); time = time.plusMinutes(30)) {
            AvailabilityTime availabilityTime = availabilityTimeMapper.toAvailabilityTime(time, time.plusMinutes(30), request.getDayOfWeek());
            availabilityTime.setTutor(tutor);
            times.add(availabilityTime);
        }
        return times;
    }

    private List<AvailabilityTimeResponse> generateTimeResponses(Tutor tutor, LocalDateTime baseTime, LessonDuration duration, LocalDate date) {
        return List.of(
                createAvailabilityTimeResponse(baseTime.minusMinutes(duration.getTime()), tutor, duration, date),
                createAvailabilityTimeResponse(baseTime, tutor, duration, date),
                createAvailabilityTimeResponse(baseTime.plusMinutes(duration.getTime()), tutor, duration, date)
        );
    }

    private AvailabilityTimeResponse createAvailabilityTimeResponse(LocalDateTime time, Tutor tutor, LessonDuration duration, LocalDate date) {
        LocalTime start = time.toLocalTime();
        LocalTime end = start.plusMinutes(duration.getTime());
        boolean canApply = tutor.hasOverlappingAvailabilityTime(start, end) &&
                !lessonRepository.isAlreadyReservation(tutor.getId(), start, end, date);
        return availabilityTimeMapper.toAvailabilityTimeResponse(time, canApply);
    }

    private List<AvailabilityTime> findMatchingAvailabilityTimes(Tutor tutor, AvailabilityTimeRequest request) {
        List<AvailabilityTime> matchedTimes = new ArrayList<>();
        for (LocalTime time = request.getStartAt(); !time.isAfter(request.getEndAt().minusMinutes(30)); time = time.plusMinutes(30)) {
            final LocalTime slotStart = time;
            final LocalTime slotEnd = time.plusMinutes(30);
            tutor.getAvailabilityTimes().stream()
                    .filter(t -> t.getDayOfWeek().equals(request.getDayOfWeek()))
                    .filter(t -> t.getStartAt().equals(slotStart) && t.getEndAt().equals(slotEnd))
                    .findFirst()
                    .ifPresent(matchedTimes::add);
        }
        return matchedTimes;
    }
}
