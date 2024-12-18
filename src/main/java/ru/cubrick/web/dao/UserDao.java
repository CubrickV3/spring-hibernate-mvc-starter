package ru.cubrick.web.dao;

import ru.cubrick.web.models.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
    User findById(long id);
    void persist(User user);
    void update(long id, User user);
    void remove(Long id);
}
