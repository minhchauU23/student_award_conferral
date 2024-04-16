package com.devptit.event_app.mapper;

import com.devptit.event_app.dto.request.EventCreateRequest;
import com.devptit.event_app.dto.response.EventResponse;
import com.devptit.event_app.entity.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventResponse toEventResponse(Event event);
    Event toEvent(EventCreateRequest request);
}
