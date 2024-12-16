package ru.cubrick.web.dao;

import ru.cubrick.web.models.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
    void persist(User user);
    void update(User user, String name, String email);
    void remove(User user);
}
