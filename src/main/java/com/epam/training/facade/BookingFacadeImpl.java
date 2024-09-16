package com.epam.training.facade;

import com.epam.training.domain.Event;
import com.epam.training.domain.Ticket;
import com.epam.training.domain.User;
import com.epam.training.service.EventService;
import com.epam.training.service.TicketService;
import com.epam.training.service.UserService;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
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
    public User createUser(Long id, String name, String email) {
        User user = new User(id, name, email);
        return userService.createUser(user);
    }

    @Override
    public Event createEvent(Long id, String title, LocalDateTime date) {
        return eventService.createEvent(new Event(id, title, date));
    }

    @Override
    public Ticket bookTicket(Long ticketId, Long eventId, Long userId, Integer place, String category) {
        Ticket.TicketCategory ticketCategory = Ticket.TicketCategory.valueOf(category);
        User user = userService.getUserById(userId);
        Ticket ticket = new Ticket(ticketId, user, eventId, place, ticketCategory);
        ticketService.bookTicket(ticket);
        return ticket;
    }
}