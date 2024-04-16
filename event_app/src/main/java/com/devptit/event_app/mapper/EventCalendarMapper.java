package com.devptit.event_app.mapper;

import com.devptit.event_app.dto.request.EventCalendarCreateRequest;
import com.devptit.event_app.dto.response.EventCalendarResponse;
import com.devptit.event_app.entity.EventCalendar;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface EventCalendarMapper {
    EventCalendarResponse toEventCalendarResponse(EventCalendar eventCalendar);
    List<EventCalendarResponse> toEventCalendarResponses(List<EventCalendar> eventCalendars);

    EventCalendar toEventCalendar(EventCalendarCreateRequest request);
}

