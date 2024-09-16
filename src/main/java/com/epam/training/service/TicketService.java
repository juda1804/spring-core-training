package com.epam.training.service;

import com.epam.training.dao.impl.TicketRepositoryImpl;
import com.epam.training.domain.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TicketService {
    private final TicketRepositoryImpl ticketRepositoryImpl;

    public TicketService(TicketRepositoryImpl ticketRepositoryImpl) {
        this.ticketRepositoryImpl = ticketRepositoryImpl;
    }

    public void bookTicket(Ticket ticket) {
        log.info("Booking ticket {}", ticket);
        ticketRepositoryImpl.save(ticket);
    }

    public Ticket getTicketById(Long id) {
        log.info("Get ticket by id {}", id);
        return ticketRepositoryImpl.findTicketById(id);
    }
}
