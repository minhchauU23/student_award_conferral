package com.devptit.event_app.repository;

import com.devptit.event_app.entity.EventCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventCalendarRepository extends JpaRepository<EventCalendar, String> {
    List<EventCalendar> findByEventId(String eventID);
}
