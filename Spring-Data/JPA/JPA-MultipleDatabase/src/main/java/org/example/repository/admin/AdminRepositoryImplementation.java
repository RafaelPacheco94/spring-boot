package org.example.repository.admin;

import org.example.entity.tertiary.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class AdminRepositoryImplementation implements AdminRepository {

    private EntityManager entityManager;

    @Autowired
    public AdminRepositoryImplementation(@Qualifier("tertiaryEntityManagerFactory") EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Admin getAdminByID(int id) {
        return entityManager.createQuery("SELECT u FROM Admin u WHERE id=?1", Admin.class).setParameter(1, id).getSingleResult();
    }

    @Override
    public boolean insertAdmin(Admin admin) {
        try {
            entityManager.persist(admin);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public boolean deleteAdminByID(int id) {
        Query q = entityManager.createQuery("DELETE FROM Admin u WHERE id=?1", Admin.class).setParameter(1, id);
        return q.executeUpdate() != 0;
    }

    @Override
    public boolean updateAdminByID(Admin admin, int id) {
        Query q = entityManager.createQuery("UPDATE Admin u SET name=?1 WHERE id=?2", Admin.class)
                .setParameter(1, admin.getName())
                .setParameter(2, id);
        return q.executeUpdate() != 0;

    }


}
