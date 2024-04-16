package com.devptit.event_app.service;

import com.devptit.event_app.dto.request.EventCalendarCreateRequest;
import com.devptit.event_app.dto.response.EventCalendarResponse;
import com.devptit.event_app.dto.response.EventResponse;
import com.devptit.event_app.entity.Event;
import com.devptit.event_app.entity.EventCalendar;
import com.devptit.event_app.mapper.EventCalendarMapper;
import com.devptit.event_app.repository.EventCalendarRepository;
import com.devptit.event_app.repository.EventRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EventCalendarService {
    EventCalendarRepository eventCalendarRepository;
    EventRepository eventRepository;
    EventCalendarMapper eventCalendarMapper;

    public List<EventCalendarResponse>  findByEventID(String eventID){
        return eventCalendarMapper.toEventCalendarResponses(eventCalendarRepository.findByEventId(eventID));
    }

//    public EventCalendarResponse create(EventCal){
//
//    }
public EventCalendarResponse addEventCalendar(String eventID, EventCalendarCreateRequest request) {
    Event event = eventRepository.findById(eventID).orElseThrow();
    EventCalendar eventCalendar = eventCalendarMapper.toEventCalendar(request);
    eventCalendar.setEvent(event);
    return eventCalendarMapper.toEventCalendarResponse(eventCalendarRepository.save(eventCalendar));
}
}
