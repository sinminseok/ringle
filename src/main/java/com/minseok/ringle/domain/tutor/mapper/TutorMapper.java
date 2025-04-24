package com.minseok.ringle.domain.tutor.mapper;

import com.minseok.ringle.domain.lesson.dto.AvailabilityTimeResponse;
import com.minseok.ringle.domain.tutor.dto.TutorOverviewResponse;
import com.minseok.ringle.domain.tutor.entity.Tutor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TutorMapper {


    default TutorOverviewResponse toTutorOverviewResponse(final Tutor tutor, final List<AvailabilityTimeResponse> timeResponses) {
        return TutorOverviewResponse.builder()
                .tutorId(tutor.getId())
                .name(tutor.getName())
                .profileUrl(tutor.getProfileUrl())
                .schoolName(tutor.getSchoolName())
                .job(tutor.getJob())
                .times(timeResponses)
                .build();
    }

}
