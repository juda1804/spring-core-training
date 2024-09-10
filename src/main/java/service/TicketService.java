package service;

import dao.TicketDao;
import domain.Ticket;
import java.util.List;

public class TicketService {
    private TicketDao ticketDao;

    public Ticket bookTicket(Ticket ticket) {
        return ticketDao.save(ticket);
    }

    public Ticket getTicketById(Long id) {
        return ticketDao.findById(id);
    }

    public List<Ticket> getAllTickets() {
        return ticketDao.findAll();
    }

    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }
}
