package com.epam.training.dao.impl;

import com.epam.training.dao.UserRepository;
import com.epam.training.domain.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void save(User user) {
        String sql = "INSERT INTO Users (id, name, email) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getId(), user.getName(), user.getEmail());
    }

    public User findUserById(Long id) {
        String sql = "SELECT * FROM Users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    @Override
    public List<User> findAllWithMoreThan100EventsAnd100TicketsInMarch2025() {
        String sql = """
            SELECT DISTINCT u.name, u.email
            FROM Users u
            JOIN Tickets t ON u.id = t.userId
            JOIN Events e ON t.eventId = e.id
            WHERE t.id IN (
                SELECT userId FROM Tickets
                WHERE dateTime BETWEEN '2025-03-01' AND '2025-03-31'
                GROUP BY userId HAVING COUNT(*) > 100
            )
            GROUP BY u.id
            HAVING COUNT(DISTINCT e.id) > 100;
        """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
}


