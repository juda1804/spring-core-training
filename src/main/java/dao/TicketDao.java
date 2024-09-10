package dao;

import domain.Ticket;
import java.util.*;

public class TicketDao {
    private final Map<String, Ticket> storage = new HashMap<>();

    public Ticket save(Ticket ticket) {
        storage.put("ticket:" + ticket.getId(), ticket);
        return ticket;
    }

    public Ticket findById(Long id) {
        return storage.get("ticket:" + id);
    }

    public List<Ticket> findAll() {
        return new ArrayList<>(storage.values());
    }

    // Other methods like delete, update, etc.
}

