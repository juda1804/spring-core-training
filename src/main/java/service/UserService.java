package service;

import dao.UserDao;
import domain.User;
import java.util.List;

public class UserService {
    private UserDao userDao;

    public User createUser(User user) {
        return userDao.save(user);
    }

    public User getUserById(Long id) {
        return userDao.findById(id);
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    // Setters for dependency injection
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
