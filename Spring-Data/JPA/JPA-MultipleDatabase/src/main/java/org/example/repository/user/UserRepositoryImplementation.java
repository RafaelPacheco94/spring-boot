package org.example.repository.user;

import org.example.entity.primary.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class UserRepositoryImplementation implements UserRepository {

    private EntityManager entityManager;

    @Autowired
    public UserRepositoryImplementation(@Qualifier("primaryEntityManagerFactory") EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User getUserByID(int id) {
        return entityManager.createQuery("SELECT u FROM User u WHERE id=?1", User.class).setParameter(1, id).getSingleResult();
    }

    @Override
    public boolean insertUser(User user) {
        try {
            entityManager.persist(user);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public boolean deleteUserByID(int id) {
        Query q = entityManager.createQuery("DELETE FROM User u WHERE id=?1", User.class).setParameter(1, id);
        return q.executeUpdate() != 0;
    }

    @Override
    public boolean updateUserByID(User user, int id) {
        Query q = entityManager.createQuery("UPDATE User u SET name=?1 WHERE id=?2", User.class)
                .setParameter(1, user.getName())
                .setParameter(2, id);
        return q.executeUpdate() != 0;
    }

}
