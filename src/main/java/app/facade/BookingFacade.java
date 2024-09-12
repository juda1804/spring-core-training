package app.facade;

import app.domain.Event;
import app.domain.Ticket;
import app.domain.User;

public interface BookingFacade {
    User createUser(Long id, String name, String email);
    Event createEvent(Long id, String title, String date);
    Ticket bookTicket(Long ticketId, Long eventId, Long userId, Integer place, String category);
}