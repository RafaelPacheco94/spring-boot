package org.example.repository;

import org.example.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    boolean insertUser(User user);

    boolean deleteUser(String id);

    User getUser(String id);

    User updateUser(String id, User user);

}
