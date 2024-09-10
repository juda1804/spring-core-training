package dao;

import domain.Event;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventDao {
    private final Map<String, Event> storage = new HashMap<>();

    public Event save(Event event) {
        storage.put("event:" + event.getId(), event);
        return event;
    }

    public Event findById(Long id) {
        return storage.get("event:" + id);
    }

    public List<Event> findAll() {
        return new ArrayList<>(storage.values());
    }

    // Other methods like delete, update, etc.
}