package com.devptit.event_app.controller;

import com.devptit.event_app.dto.request.EventCalendarCreateRequest;
import com.devptit.event_app.dto.request.EventCreateRequest;
import com.devptit.event_app.dto.request.EventTypeCreateRequest;
import com.devptit.event_app.dto.response.APIResponse;
import com.devptit.event_app.dto.response.EventCalendarResponse;
import com.devptit.event_app.dto.response.EventResponse;
import com.devptit.event_app.dto.response.EventTypeResponse;
import com.devptit.event_app.exception.ErrorCode;
import com.devptit.event_app.service.EventCalendarService;
import com.devptit.event_app.service.EventService;
import com.devptit.event_app.service.EventTypeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class Event {
    EventService eventService;
    EventCalendarService eventCalendarService;
    EventTypeService eventTypeService;

    @GetMapping("/{eventID}")
    public APIResponse<EventResponse> getEvent(@PathVariable("eventID") String eventID){
        return APIResponse.<EventResponse>builder()
                .code(ErrorCode.SUCCESS.getCode())
                .result(eventService.findById(eventID))
                .build();
    }

    @PostMapping("/{eventTypeID}/add_event")
    public APIResponse<EventResponse> createEvent(@PathVariable("eventTypeID") Integer eventTypeID,
                                                  @RequestBody EventCreateRequest request){
        return APIResponse.<EventResponse>builder()
                .code(ErrorCode.SUCCESS.getCode())
                .result(eventService.create(eventTypeID, request))
                .build();
    }

    @PostMapping("/add_event_type")
    public  APIResponse<EventTypeResponse> createEventType(@RequestBody EventTypeCreateRequest request){
        return APIResponse.<EventTypeResponse>builder()
                .code(ErrorCode.SUCCESS.getCode())
                .result(eventTypeService.create(request))
                .build();
    }

    @PostMapping("/{eventID}/add_event_calendar")
    public APIResponse<EventCalendarResponse> createEventCalendar(@PathVariable("eventID") String eventID
            ,@RequestBody EventCalendarCreateRequest request){

        return APIResponse.<EventCalendarResponse>builder()
                .code(ErrorCode.SUCCESS.getCode())
                .result(eventCalendarService.addEventCalendar(eventID, request))
                .build();
    }


    @GetMapping("/{eventID}/calendar")
    public APIResponse<List<EventCalendarResponse>> getCalendars(
            @PathVariable("eventID") String eventID){
        return APIResponse.<List<EventCalendarResponse>>builder()
                .code(ErrorCode.SUCCESS.getCode())
                .result(eventCalendarService.findByEventID(eventID))
                .build();
    }



}
