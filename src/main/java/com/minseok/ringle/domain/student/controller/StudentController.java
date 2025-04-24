package com.minseok.ringle.domain.student.controller;


import com.minseok.ringle.domain.student.dto.LessonResponse;
import com.minseok.ringle.domain.student.service.StudentService;
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
@RequestMapping("/v1/api/students")
public class StudentController {

    private final StudentService studentService;

    /**
     * 신청한 수업 조회 API
     */
    @GetMapping("/{studentId}/lessons")
    public ResponseEntity<?> findApplyLessons(@PathVariable UUID studentId) {
        List<LessonResponse> lessons = studentService.findApplyLessons(studentId);
        SuccessResponse response = new SuccessResponse(true, "신청한 수업 조회 성공", lessons);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
