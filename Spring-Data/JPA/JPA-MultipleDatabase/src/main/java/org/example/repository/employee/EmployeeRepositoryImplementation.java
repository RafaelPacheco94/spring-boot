package org.example.repository.employee;

import org.example.entity.secondary.Employee;
import org.example.entity.tertiary.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class EmployeeRepositoryImplementation implements EmployeeRepository {

    private EntityManager entityManager;

    @Autowired
    public EmployeeRepositoryImplementation(@Qualifier("secondaryEntityManagerFactory") EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Employee getEmployeeByID(int id) {
        return entityManager.createQuery("SELECT u FROM Employee u WHERE id=?1", Employee.class).setParameter(1, id).getSingleResult();
    }

    @Override
    public boolean insertEmployee(Employee employee) {
        try {
            entityManager.persist(employee);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public boolean deleteEmployeeByID(int id) {
        Query q = entityManager.createQuery("DELETE FROM Employee u WHERE id=?1", Employee.class).setParameter(1, id);
        return q.executeUpdate() != 0;
    }

    @Override
    public boolean updateEmployeeByID(Employee employee, int id) {
        Query q = entityManager.createQuery("UPDATE Employee u SET name=?1 WHERE id=?2", Employee.class)
                .setParameter(1, employee.getName())
                .setParameter(2, id);
        return q.executeUpdate() != 0;    }



}
