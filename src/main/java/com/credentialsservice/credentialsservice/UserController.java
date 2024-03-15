package com.credentialsservice.credentialsservice;

import com.credentialsservice.credentialsservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{_id}")
    public User getById(@PathVariable("_id") String id) {
        return userRepository
                .findBy_Id(id);
    }

    @GetMapping("")
    public List<User> getUsers() {
        return userRepository
                .findAll();
    }

    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created OK");
    }

    @PutMapping("/{_id}")
    public ResponseEntity<String> updateUser(@PathVariable("_id") String id, @RequestBody User user) {
        if(userRepository.existsById(id)) {
            user.set_id(id);
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("Updated OK");
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Found");
    }

    @DeleteMapping("/{_id}")
    public ResponseEntity<String> deleteUser(@PathVariable("_id") String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted OK");
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Found");
    }
}
