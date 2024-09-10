package dao;

import domain.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDao {
    private final Map<String, User> storage;

    @Autowired
    public UserDao(Map<String, User> userStorage) {
        this.storage = userStorage;
    }

    public User save(User user) {
        storage.put("user:" + user.getId(), user);
        return user;
    }

    public User findById(Long id) {
        return storage.get("user:" + id);
    }

    public List<User> findAll() {
        return new ArrayList<>(storage.values());
    }

    // Other methods like delete, update, etc.
}
