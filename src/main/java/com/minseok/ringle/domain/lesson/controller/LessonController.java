package com.minseok.ringle.domain.lesson.controller;

import com.minseok.ringle.domain.lesson.dto.AvailabilityTimeResponse;
import com.minseok.ringle.domain.lesson.dto.AvailabilityTimeSearchRequest;
import com.minseok.ringle.domain.lesson.dto.LessonRequest;
import com.minseok.ringle.domain.lesson.service.LessonService;
import com.minseok.ringle.utils.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/lessons")
public class LessonController {

    private final LessonService lessonService;

    /**
     * 수업 가능한 시간대 조회 API
     */
    @GetMapping("/available-times")
    public ResponseEntity<?> findAvailabilityTimes(@RequestBody final AvailabilityTimeSearchRequest request) {
        List<AvailabilityTimeResponse> availabilityTimeResponses = lessonService.findAvailabilityTimes(request);
        SuccessResponse response = new SuccessResponse(true, "수업 가능 시간 조회 성공", availabilityTimeResponses);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 수업 신청 API
     */
    @PostMapping
    public ResponseEntity<?> applyLesson(@RequestBody final LessonRequest request) {
        lessonService.applyLesson(request);
        SuccessResponse response = new SuccessResponse(true, "수업 신청 성공", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
