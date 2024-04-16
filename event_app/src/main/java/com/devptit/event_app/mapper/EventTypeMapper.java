package com.devptit.event_app.mapper;

import com.devptit.event_app.dto.request.EventTypeCreateRequest;
import com.devptit.event_app.dto.response.EventTypeResponse;
import com.devptit.event_app.entity.EventType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventTypeMapper {
    EventTypeResponse toEventTypeResponse(EventType eventType);
    EventType toEventType(EventTypeCreateRequest request);
}
