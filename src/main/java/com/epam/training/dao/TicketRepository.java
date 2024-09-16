package com.epam.training.dao;

import com.epam.training.domain.Ticket;
public interface TicketRepository {
    Ticket findTicketById(Long id);

    void save(Ticket ticket);
}
