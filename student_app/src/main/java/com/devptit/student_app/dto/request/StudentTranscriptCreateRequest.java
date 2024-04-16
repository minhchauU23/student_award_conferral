package com.devptit.student_app.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentTranscriptCreateRequest {
    String firstName;
    String lastName;
    LocalDate dob;
    String academicYear;
    float gpa;
    float drl;
    String major;
}
