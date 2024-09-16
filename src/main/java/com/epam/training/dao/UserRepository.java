package com.epam.training.dao;

import com.epam.training.domain.User;
import java.util.List;

public interface UserRepository {
    User findUserById(Long id);

    void save(User user);

    List<User> findAllWithMoreThan100EventsAnd100TicketsInMarch2025();
}
