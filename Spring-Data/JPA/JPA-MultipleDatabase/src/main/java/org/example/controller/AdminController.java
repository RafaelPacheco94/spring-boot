package org.example.controller;

import org.example.dto.AdminDTO;
import org.example.dto.EmployeeDTO;
import org.example.dto.ErrorDTO;
import org.example.entity.secondary.Employee;
import org.example.entity.tertiary.Admin;
import org.example.mapper.AdminMapper;
import org.example.mapper.EmployeeMapper;
import org.example.service.AdminService;
import org.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminMapper adminMapper;

    @RequestMapping(value = "/insertAdmin",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity insertUser(@RequestBody @Valid AdminDTO adminDTO) {
        return adminService.insertAdmin(adminMapper.AdminDtoToAdmin(adminDTO)) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDTO("Employee already exists!"));
    }

    @RequestMapping(value = "/getAdmin/{id}",
            method = RequestMethod.GET)
    public ResponseEntity getUser(@PathVariable int id) {
        Admin admin = adminService.getAdminByID(id);
        return admin != null ?
                ResponseEntity.status(HttpStatus.OK).body(adminMapper.AdminToAdminDTO(admin)) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("There is no such employee!"));
    }

    @RequestMapping(value = "/deleteAdmin/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable int id) {
        return adminService.deleteAdminByID(id) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("Employee doesn't exist!"));
    }

    @RequestMapping(value = "/updateEmployee/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUser(@PathVariable int id, @RequestBody AdminDTO adminDTO) {
        return adminService.updateAdminByID(adminMapper.AdminDtoToAdmin(adminDTO), id) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("There is no such user!"));
    }

}
