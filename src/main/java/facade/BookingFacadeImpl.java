package facade;


import domain.Event;
import domain.Ticket;
import domain.User;
import service.EventService;
import service.TicketService;
import service.UserService;

public class BookingFacadeImpl implements BookingFacade {
    private final UserService userService;
    private final EventService eventService;
    private final TicketService ticketService;

    public BookingFacadeImpl(UserService userService, EventService eventService, TicketService ticketService) {
        this.userService = userService;
        this.eventService = eventService;
        this.ticketService = ticketService;
    }

    @Override
    public User createUser(User user) {
        return userService.createUser(user);
    }

    @Override
    public Event createEvent(Event event) {
        return eventService.createEvent(event);
    }

    @Override
    public Ticket bookTicket(Ticket ticket) {
        return ticketService.bookTicket(ticket);
    }

}