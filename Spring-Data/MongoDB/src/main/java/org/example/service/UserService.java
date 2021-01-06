package org.example.service;

import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public  boolean insertUser(User user) {
        return userRepository.insertUser(user);
    }

    public  boolean deleteUser(String id) {
        return userRepository.deleteUser(id);
    }

    public  User getUser(String id){
        return userRepository.getUser(id);
    }

    public  User updateUser(String id, User user){
        return userRepository.updateUser(id, user);
    }

}
