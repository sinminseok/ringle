package com.minseok.ringle.domain.student.entity;

import com.minseok.ringle.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student extends User {

    @Id
    @Column(name = "student_id", nullable = false, updatable = false)
    @UuidGenerator
    private UUID id;
}
