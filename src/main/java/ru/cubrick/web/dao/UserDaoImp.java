package ru.cubrick.web.dao;

import ru.cubrick.web.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void persist(User user) {
        em.persist(user);
    }

    @Override
    public void update(User user, String name, String email) {
        em.merge(user);
    }

    @Override
    public void remove(User user) {
        em.remove(user);
    }
}
