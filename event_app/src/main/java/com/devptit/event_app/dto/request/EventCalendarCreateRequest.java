package com.devptit.event_app.dto.request;

import com.devptit.event_app.dto.response.EventResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class EventCalendarCreateRequest {
    String detail;
    LocalTime startTime;
    LocalTime endTime;
    String description;
}
