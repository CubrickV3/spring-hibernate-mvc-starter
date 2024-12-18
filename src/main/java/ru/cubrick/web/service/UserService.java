package ru.cubrick.web.service;

import ru.cubrick.web.models.User;
import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(long id);
    void persist(User user);
    void update(long id, User user);
    void remove(Long id);
}
