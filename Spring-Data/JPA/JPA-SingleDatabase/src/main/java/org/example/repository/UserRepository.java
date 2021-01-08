package org.example.repository;


import org.example.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(transactionManager = "primaryTransactionManager")
public interface UserRepository {

    User getUserByID(int id);

    boolean insertUser(User user);

    boolean deleteUserByID(int id);

    boolean updateUserByID(User user, int id);

}
