package org.example.controller;

import org.example.dto.EmployeeDTO;
import org.example.dto.ErrorDTO;
import org.example.dto.UserDTO;
import org.example.entity.primary.User;
import org.example.entity.secondary.Employee;
import org.example.mapper.EmployeeMapper;
import org.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @RequestMapping(value = "/insertEmployee",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity insertUser(@RequestBody @Valid EmployeeDTO employeeDTO) {
        return employeeService.insertEmployee(employeeMapper.EmployeeDtoToEmployee(employeeDTO)) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDTO("Employee already exists!"));
    }

    @RequestMapping(value = "/getEmployee/{id}",
            method = RequestMethod.GET)
    public ResponseEntity getUser(@PathVariable int id) {
        Employee employee = employeeService.getEmployeeByID(id);
        return employee != null ?
                ResponseEntity.status(HttpStatus.OK).body(employeeMapper.EmployeeToEmployeeDto(employee)) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("There is no such employee!"));
    }

    @RequestMapping(value = "/deleteEmployee/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable int id) {
        return employeeService.deleteEmployeeByID(id) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("Employee doesn't exist!"));
    }


    @RequestMapping(value = "/updateEmployee/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUser(@PathVariable int id, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployeeByID(employeeMapper.EmployeeDtoToEmployee(employeeDTO), id) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("There is no such user!"));
    }

}
