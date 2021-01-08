package org.example.repository.employee;

import org.example.entity.secondary.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(transactionManager = "secondaryTransactionManager")
public interface EmployeeRepository {

    Employee getEmployeeByID(int id);

    boolean insertEmployee(Employee employee);

    boolean deleteEmployeeByID(int id);

    boolean updateEmployeeByID(Employee employee, int id);
}
