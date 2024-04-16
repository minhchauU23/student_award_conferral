package com.devptit.event_app.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventCalendarResponse {
    String id;
    String detail;
    LocalTime startTime;
    LocalTime endTime;
    String description;
}
