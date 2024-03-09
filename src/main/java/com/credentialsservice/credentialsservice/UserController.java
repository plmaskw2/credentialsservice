package com.credentialsservice.credentialsservice;

import com.credentialsservice.credentialsservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/{username}")
    public User getByUsername(@PathVariable("username") String username) {
        return userRepository
                .findByUsername(username);
    }

    @GetMapping("")
    public List<User> getUsers() {
        return userRepository
                .findAll();
    }

    @PostMapping("")
    public void saveUser(@RequestBody User user) {
        userRepository
                .save(user);
    }
}
