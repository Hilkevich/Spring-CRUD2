package web.dao;

import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional
public class UserDaoImp implements UserDAO {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> index() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }


    @Override
    public User show(int id) {
        return entityManager.find(User.class, id);
    }


    @Override
    public void save(User user) {
        entityManager.persist(user);
    }


    @Override
    public void update(User user) {
        entityManager.merge(user);
    }


    @Override
    public void delete(int id) {
        entityManager.remove(show(id));
    }
}
