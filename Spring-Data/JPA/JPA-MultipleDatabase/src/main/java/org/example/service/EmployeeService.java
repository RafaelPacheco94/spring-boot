package org.example.service;


import org.example.entity.secondary.Employee;
import org.example.repository.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee getEmployeeByID(int id) {
        return employeeRepository.getEmployeeByID(id);
    }

    public boolean insertEmployee(Employee employee) {
        return employeeRepository.insertEmployee(employee);
    }

    public boolean deleteEmployeeByID(int id) {
        return employeeRepository.deleteEmployeeByID(id);
    }

    public boolean updateEmployeeByID(Employee employee, int id) {
        return employeeRepository.updateEmployeeByID(employee, id);
    }
}
