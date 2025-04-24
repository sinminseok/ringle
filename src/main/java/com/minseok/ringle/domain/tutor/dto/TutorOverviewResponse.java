package com.minseok.ringle.domain.tutor.dto;

import com.minseok.ringle.domain.lesson.dto.AvailabilityTimeResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Setter
@AllArgsConstructor
public class TutorOverviewResponse {

    private UUID tutorId;

    private String name;

    private String profileUrl;

    private String schoolName;

    private String job;

    private List<AvailabilityTimeResponse> times;
}
