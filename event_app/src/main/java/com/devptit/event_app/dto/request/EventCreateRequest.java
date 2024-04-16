package com.devptit.event_app.dto.request;

import com.devptit.event_app.entity.EventCalendar;
import com.devptit.event_app.entity.EventType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class EventCreateRequest {
    String name;
    LocalDate startDate;
    LocalDate endDate;
    String description;
}
