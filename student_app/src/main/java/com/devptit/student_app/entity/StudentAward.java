package com.devptit.student_app.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentAward {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String awardId;
    String eventID;
    LocalDate conferDate;
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    StudentTranscript studentTranscript;
}
