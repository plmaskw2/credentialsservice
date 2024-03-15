package com.credentialsservice.credentialsservice;

import com.credentialsservice.credentialsservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/username/{username}")
    public User getByUsername(@PathVariable("username") String username) {
        return userRepository
                .findByUsername(username);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<User>> getById(@PathVariable("id") String id) {
        if (userRepository.existsById(id)) {
            Optional<User> user = userRepository.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
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

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        if(userRepository.existsById(id)) {
            user.setId(id);
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("Updated OK");
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted OK");
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Found");
    }
}
