package app.service;

import app.dao.UserDao;
import app.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User createUser(User user) {
        log.info("Creating user with id {} and name {}", user.getId(), user.getName());
        userDao.saveUser(user);
        return user;
    }

    public User getUserById(Long id) {
        log.info("Retrieving user with id {}", id);
        return userDao.findUserById(id);
    }
}
