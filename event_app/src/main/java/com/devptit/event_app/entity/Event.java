package com.devptit.event_app.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    LocalDate startDate;
    LocalDate endDate;
    String description;
    @ManyToOne
    EventType type;
    @OneToMany(mappedBy = "event")
    Set<EventCalendar> calendars;
}
