-- 테이블이 없으면 생성
CREATE TABLE IF NOT EXISTS tutor (
    tutor_id BINARY(16) PRIMARY KEY,
    name VARCHAR(255),
    profile_url VARCHAR(255),
    created_at TIMESTAMP,
    modified_at TIMESTAMP,
    school_name VARCHAR(255),
    job VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS student (
    student_id BINARY(16) PRIMARY KEY,
    name VARCHAR(255),
    profile_url VARCHAR(255),
    created_at TIMESTAMP,
    modified_at TIMESTAMP
);

-- UUID 값 삽입 (중복이면 무시)
INSERT IGNORE INTO tutor (tutor_id, name, profile_url, created_at, modified_at, school_name, job)
VALUES (UUID_TO_BIN('f47ac10b-58cc-4372-a567-0e02b2c3d479'),
        'Minseok', 'https://example.com/profile.jpg', NOW(), NOW(), 'Seoul University', 'teacher');

INSERT IGNORE INTO student (student_id, name, profile_url, created_at, modified_at)
VALUES (UUID_TO_BIN('f47ac10b-58cc-4372-a567-0e02b2c3d478'),
        'Sol', 'https://example.com/profile.jpg', NOW(), NOW());