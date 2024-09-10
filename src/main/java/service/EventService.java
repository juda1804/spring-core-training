package service;

import dao.EventDao;
import domain.Event;
import java.util.List;

public class EventService {
    private EventDao eventDao;

    public Event createEvent(Event event) {
        return eventDao.save(event);
    }

    public Event getEventById(Long id) {
        return eventDao.findById(id);
    }

    public List<Event> getAllEvents() {
        return eventDao.findAll();
    }

    // Setters for dependency injection
    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }
}

