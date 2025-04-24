package com.minseok.ringle.domain.tutor.controller;


import com.minseok.ringle.domain.lesson.dto.AvailabilityTimeRequest;
import com.minseok.ringle.domain.tutor.dto.TutorAvailabilitySearchRequest;
import com.minseok.ringle.domain.tutor.dto.TutorOverviewResponse;
import com.minseok.ringle.domain.tutor.service.TutorService;
import com.minseok.ringle.utils.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/tutors")
public class TutorController {

    private final TutorService tutorService;

    /**
     * 수업 가능한 시간 생성 API
     */
    @PostMapping("/{tutorId}/availability-times")
    public ResponseEntity<?> saveAvailabilityTime(@PathVariable UUID tutorId,
                                                  @RequestBody AvailabilityTimeRequest request){
        tutorService.saveAvailabilityTime(tutorId, request);
        SuccessResponse response = new SuccessResponse(true, "수업 가능시간 생성 완료", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 수업 가능한 시간 삭제 API
     */
    @DeleteMapping("/{tutorId}/availability-times")
    public ResponseEntity<?> deleteAvailabilityTime(@PathVariable UUID tutorId,
                                                  @RequestBody AvailabilityTimeRequest request){
        tutorService.deleteAvailabilityTime(tutorId, request);
        SuccessResponse response = new SuccessResponse(true, "수업 가능시간 삭제 완료", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 수업 가능한 튜터 조회 API
     */
    @GetMapping("/availability")
    public ResponseEntity<?> findAvailabilityTutor(@RequestBody TutorAvailabilitySearchRequest request) {
        List<TutorOverviewResponse> availabilityTutor = tutorService.findAvailabilityTutor(request);
        SuccessResponse response = new SuccessResponse(true, "수업 가능 튜터 조회 성공", availabilityTutor);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
