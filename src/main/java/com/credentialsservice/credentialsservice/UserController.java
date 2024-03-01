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
    public String getByUsername(@PathVariable("username") String username) {
        return userRepository
                .findByUsername(username)
                .getPassword();
    }

    @GetMapping("")
    public List<String> getUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(User::toString)
                .toList();
    }

    @PostMapping("")
    public void saveUser(@RequestBody User user) {
        userRepository
                .save(user);
    }
}
