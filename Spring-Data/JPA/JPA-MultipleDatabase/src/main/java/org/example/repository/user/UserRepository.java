package org.example.repository.user;

import org.example.entity.primary.User;
import org.example.entity.secondary.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(transactionManager = "primaryTransactionManager")
public interface UserRepository {
    User getUserByID(int id);

    boolean insertUser(User user);

    boolean deleteUserByID(int id);

    boolean updateUserByID(User user, int id);
}
