package com.minseok.ringle.domain.user.entity;

import com.minseok.ringle.domain.base.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;


@Getter
@MappedSuperclass
public abstract class User extends BaseTime{

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "profile_url", nullable = true)
    private String profileUrl;
}