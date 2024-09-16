package com.epam.training.service;

import com.epam.training.dao.impl.UserRepositoryImpl;
import com.epam.training.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
    private final UserRepositoryImpl userRepositoryImpl;

    public UserService(UserRepositoryImpl userRepositoryImpl) {
        this.userRepositoryImpl = userRepositoryImpl;
    }

    public User createUser(User user) {
        log.info("Creating user with id {} and name {}", user.getId(), user.getName());
        userRepositoryImpl.save(user);
        return user;
    }

    public User getUserById(Long id) {
        log.info("Retrieving user with id {}", id);
        return userRepositoryImpl.findUserById(id);
    }
}
