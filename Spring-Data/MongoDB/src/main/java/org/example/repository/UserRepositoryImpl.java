package org.example.repository;

import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public boolean insertUser(User user) {
        try {
            mongoTemplate.save(user);
            return true;
        } catch (DuplicateKeyException exception) {
            return false;
        }
    }

    @Override
    public boolean deleteUser(String id) {
        return mongoTemplate.findAndRemove(new Query(Criteria.where("_id").is(id)), User.class) != null;
    }

    @Override
    public User getUser(String id) {
        return mongoTemplate.findOne(new Query(Criteria.where("_id").is(id)), User.class);
    }

    @Override
    public User updateUser(String id, User user) {
        user.setId(id);
        return mongoTemplate.findAndReplace(new Query(Criteria.where("_id").is(id)), user);
    }


}
