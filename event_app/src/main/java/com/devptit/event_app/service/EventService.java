package com.devptit.event_app.service;

import com.devptit.event_app.dto.request.EventCalendarCreateRequest;
import com.devptit.event_app.dto.request.EventCreateRequest;
import com.devptit.event_app.dto.response.EventCalendarResponse;
import com.devptit.event_app.dto.response.EventResponse;
import com.devptit.event_app.entity.Event;
import com.devptit.event_app.entity.EventCalendar;
import com.devptit.event_app.entity.EventType;
import com.devptit.event_app.exception.AppException;
import com.devptit.event_app.exception.ErrorCode;
import com.devptit.event_app.mapper.EventCalendarMapper;
import com.devptit.event_app.mapper.EventMapper;
import com.devptit.event_app.repository.EventCalendarRepository;
import com.devptit.event_app.repository.EventRepository;
import com.devptit.event_app.repository.EventTypeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EventService {
    EventRepository eventRepository;
    EventTypeRepository eventTypeRepository;
    EventMapper mapper;
    EventCalendarMapper eventCalendarMapper;


    public EventResponse findById(String eventID) {
        Event event = eventRepository.findById(eventID).orElseThrow(()-> new AppException(ErrorCode.KEY_INVALID));
        return mapper.toEventResponse(event);
    }

    public EventResponse create(Integer eventTypeID, EventCreateRequest request){
        Event event = mapper.toEvent(request);
        EventType eventType = eventTypeRepository.findById(eventTypeID).orElseThrow(()-> new AppException(ErrorCode.KEY_INVALID));
        event.setType(eventType);
        return mapper.toEventResponse(eventRepository.save(event));
    }

    public EventResponse addEventCalendar(String eventID, EventCalendarCreateRequest request) {
        Event event = eventRepository.findById(eventID).orElseThrow(()-> new AppException(ErrorCode.KEY_INVALID));
        event.getCalendars().add(eventCalendarMapper.toEventCalendar(request));
        event = eventRepository.save(event);
        return mapper.toEventResponse(event);
    }
}
