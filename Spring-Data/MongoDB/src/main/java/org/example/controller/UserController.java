package org.example.controller;

import org.example.dto.ErrorDTO;
import org.example.dto.UserDTO;
import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/insertUser",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity insertUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.insertUser(userMapper.userDtoToUser(userDTO)) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDTO("User already exists!"));
    }

    @RequestMapping(value = "/deleteUser/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable String id) {
        return userService.deleteUser(id) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("User doesn't exist!"));
    }

    @RequestMapping(value = "/getUser/{id}",
            method = RequestMethod.GET)
    public ResponseEntity getUser(@PathVariable String id) {
        User user = userService.getUser(id);
        return user != null ?
                ResponseEntity.status(HttpStatus.OK).body(userMapper.userToUserDto(user)) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("There is no such user!"));
    }

    @RequestMapping(value = "/updateUser/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userMapper.userDtoToUser(userDTO)) != null ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("There is no such user!"));
    }

}
