package com.devptit.confer_award_student_app.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class LogTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    LocalDateTime time;
    String state;
    String description;
    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    Task task;
}
