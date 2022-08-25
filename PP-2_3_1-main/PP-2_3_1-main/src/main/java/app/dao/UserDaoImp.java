package app.dao;

import app.model.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoImp implements UserDao {

    private EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("persistence").createEntityManager();
    }

    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try  {
            entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            list = entityManager.createQuery("FROM User").getResultList();
        } catch (Exception e) {
            System.out.println("В методе getAllUsers возникло исключение: " + e);
        } finally {
            entityManager.close();
        }
        return list;
    }

    @Override
    public User getUserById(long id) {
        List<User> list = new ArrayList<>();
        try {
            entityManager = getEntityManager();
            entityManager.getTransaction().begin();;
            list = entityManager.createQuery("select a from User a", User.class).getResultList();
        } catch (Exception e) {
            System.out.println("В методе getUserById возникло исключение: " + e);
        } finally {
            entityManager.close();
        }

        return list.stream().filter(person -> person.getId() == id).findAny().orElse(null);

    }

    @Override
    public void saveUser(User user) {
        try {
            entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("В методе saveUser возникло исключение: " + e);
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void updateUser(long id, User updatedUser) {

        try {
            entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(updatedUser);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("В методе updateUser возникло исключение: " + e);
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

    }

    @Override
    public void deleteUser(long id) {

        try {
            entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(User.class, id));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("В методе deleteUser возникло исключение: " + e);
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

}
