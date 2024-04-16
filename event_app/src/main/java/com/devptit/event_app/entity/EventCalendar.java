package com.devptit.event_app.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String detail;
    LocalTime startTime;
    LocalTime endTime;
    String description;
    @ManyToOne
    Event event;
}
