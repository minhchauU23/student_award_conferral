package com.devptit.student_app.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class StudentTranscriptResponse {
    String id;
    String firstName;
    String lastName;
    LocalDate dob;
    String academicYear;
    float gpa;
    float drl;
    String major;
    Set<StudentAwardResponse> studentAwards;
}
