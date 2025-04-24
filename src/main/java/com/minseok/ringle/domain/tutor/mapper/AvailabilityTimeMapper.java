package com.minseok.ringle.domain.tutor.mapper;

import com.minseok.ringle.domain.lesson.dto.AvailabilityTimeResponse;
import com.minseok.ringle.domain.tutor.entity.AvailabilityTime;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AvailabilityTimeMapper {


    default AvailabilityTime toAvailabilityTime(final LocalTime startAt, final LocalTime endAt, final DayOfWeek dayOfWeek){
        return AvailabilityTime.builder()
                .startAt(startAt)
                .dayOfWeek(dayOfWeek)
                .endAt(endAt)
                .build();
    }

    default AvailabilityTimeResponse toAvailabilityTimeResponse(LocalDateTime time, boolean canLesson) {
        return AvailabilityTimeResponse.builder()
                .time(time)
                .canLesson(canLesson)
                .build();
    }
}
