package com.devptit.event_app.service;

import com.devptit.event_app.dto.request.EventCreateRequest;
import com.devptit.event_app.dto.request.EventTypeCreateRequest;
import com.devptit.event_app.dto.response.EventResponse;
import com.devptit.event_app.dto.response.EventTypeResponse;
import com.devptit.event_app.entity.EventType;
import com.devptit.event_app.mapper.EventMapper;
import com.devptit.event_app.mapper.EventTypeMapper;
import com.devptit.event_app.repository.EventTypeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class EventTypeService {
    EventTypeRepository eventTypeRepository;
    EventTypeMapper mapper;
    EventMapper eventMapper;
    public EventTypeResponse create(EventTypeCreateRequest request){
        EventType eventType = mapper.toEventType(request);
        return mapper.toEventTypeResponse(eventTypeRepository.save(eventType));
    }

}
