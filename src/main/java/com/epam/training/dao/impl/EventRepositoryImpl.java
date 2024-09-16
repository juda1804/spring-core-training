package com.epam.training.dao.impl;

import com.epam.training.dao.EventRepository;
import com.epam.training.domain.Event;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class EventRepositoryImpl implements EventRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Event findEventById(Long id) {
        String sql = "SELECT * FROM Events WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<Event>() {
            @Override
            public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Event(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getTimestamp("dateTime").toLocalDateTime()
                );
            }
        }, id);
    }

    public void save(Event event) {
        String sql = "INSERT INTO Events (title, dateTime) VALUES (?, ?)";
        jdbcTemplate.update(sql, event.getTitle(), event.getDateTime());
    }
}
