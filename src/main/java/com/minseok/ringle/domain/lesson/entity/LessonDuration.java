package com.minseok.ringle.domain.lesson.entity;

public enum LessonDuration {
    HALF(30),
    FULL_TIME(60);

    private final int time;

    LessonDuration(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }
}