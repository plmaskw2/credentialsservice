package com.credentialsservice.credentialsservice;

import com.credentialsservice.credentialsservice.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    List<User> findAll();

    User findByUsername(String username);
}
