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
    public User findById(long id) {
        return em.find(User.class, id);
    }

    @Override
    public void persist(User user) {
        em.persist(user);
    }

    @Override
    public void update(long id, User user) {
        User u = em.find(User.class, id);
        u.setName(user.getName());
        u.setEmail(user.getEmail());
        em.merge(u);
    }

    @Override
    public void remove(Long id) {
        em.remove(em.find(User.class, id));
    }
}
