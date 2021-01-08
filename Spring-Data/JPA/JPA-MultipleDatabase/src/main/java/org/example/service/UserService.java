package org.example.service;

import org.example.entity.primary.User;
import org.example.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User getUserByID(int id) {
        return userRepository.getUserByID(id);
    }

    public boolean insertUser(User user) {
        return userRepository.insertUser(user);
    }

    public boolean deleteUser(int id) {
        return userRepository.deleteUserByID(id);
    }

    public boolean updateUser(User user, int id) {
        return userRepository.updateUserByID(user, id);
    }
}
