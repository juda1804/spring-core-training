package com.epam.training.facade;

import com.epam.training.domain.Event;
import com.epam.training.domain.Ticket;
import com.epam.training.domain.User;
import java.time.LocalDateTime;

public interface BookingFacade {
    User createUser(Long id, String name, String email);
    Event createEvent(Long id, String title, LocalDateTime date);
    Ticket bookTicket(Long ticketId, Long eventId, Long userId, Integer place, String category);
}