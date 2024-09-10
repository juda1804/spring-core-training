package facade;

import domain.Event;
import domain.Ticket;
import domain.User;

public interface BookingFacade {
    User createUser(User user);
    Event createEvent(Event event);
    Ticket bookTicket(Ticket ticket);
}
