package com.devptit.student_app.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentTranscript {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String firstName;
    String lastName;
    LocalDate dob;
    String academicYear;
    float gpa;
    float drl;
    String major;
    @OneToMany(mappedBy = "studentTranscript", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<StudentAward> studentAwards;

}
