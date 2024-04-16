package com.devptit.event_app.dto.response;

import com.devptit.event_app.entity.EventCalendar;
import com.devptit.event_app.entity.EventType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;
import java.util.Set;

@Data
public class EventResponse {
    String id;
    String name;
    LocalDate startDate;
    LocalDate endDate;
    String description;
    EventTypeResponse type;
    Set<EventCalendarResponse> calendars;
}
