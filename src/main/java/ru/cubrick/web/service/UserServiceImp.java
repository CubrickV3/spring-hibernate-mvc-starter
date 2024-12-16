package ru.cubrick.web.service;

import ru.cubrick.web.dao.UserDao;
import ru.cubrick.web.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Transactional
    @Override
    public void persist(User user) {
        userDao.persist(user);
    }

    @Transactional
    @Override
    public void update(User user, String name, String email) {
        userDao.update(user, name, email);
    }

    @Transactional
    @Override
    public void remove(User user) {
        userDao.remove(user);
    }
}
