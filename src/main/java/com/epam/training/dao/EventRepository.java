package com.epam.training.dao;

import com.epam.training.domain.Event;
public interface EventRepository {
    Event findEventById(Long id);
    void save(Event event);
}
