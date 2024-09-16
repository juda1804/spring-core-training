package com.epam.training.service;

import com.epam.training.dao.impl.EventRepositoryImpl;
import com.epam.training.domain.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EventService {
    private final EventRepositoryImpl eventRepositoryImpl;

    public EventService(EventRepositoryImpl eventRepositoryImpl) {
        this.eventRepositoryImpl = eventRepositoryImpl;
    }

    public Event createEvent(Event event) {
        log.info("Creating new event {}", event);
        eventRepositoryImpl.save(event);
        return event;
    }

    public Event getEventById(Long id) {
        log.info("Retrieving event by id {}", id);
        return eventRepositoryImpl.findEventById(id);
    }
}

