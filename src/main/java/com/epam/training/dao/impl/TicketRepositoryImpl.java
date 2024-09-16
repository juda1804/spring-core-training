package com.epam.training.dao.impl;

import com.epam.training.dao.TicketRepository;
import com.epam.training.domain.Ticket;
import com.epam.training.domain.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRepositoryImpl implements TicketRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Ticket ticket) {
        String sql = "INSERT INTO Tickets (userId, eventId, place, category) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                ticket.getUser().getId(),
                ticket.getEventId(),
                ticket.getPlace(),
                ticket.getCategory().toString());
    }

    public Ticket findTicketById(Long id) {
        String sql = """
            SELECT t.id AS ticket_id, t.userId, t.eventId, t.place, t.category, 
                   u.id AS user_id, u.name, u.email
            FROM Tickets t
            JOIN Users u ON t.userId = u.id
            WHERE t.id = ?
        """;

        return jdbcTemplate.queryForObject(sql, new RowMapper<Ticket>() {
            @Override
            public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
                // Create the User object from the result set
                User user = new User(
                        rs.getLong("user_id"),
                        rs.getString("name"),
                        rs.getString("email")
                );

                // Create and return the Ticket object with the User reference
                return new Ticket(
                        rs.getLong("ticket_id"),
                        user, // Or you can directly pass the user reference instead of userId
                        rs.getLong("eventId"),
                        rs.getInt("place"),
                        Ticket.TicketCategory.valueOf(rs.getString("category"))
                );
            }
        }, id);
    }
}
