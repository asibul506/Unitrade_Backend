package com.uniTrade.uniTrade.controller;

import com.uniTrade.uniTrade.model.User;
import com.uniTrade.uniTrade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/user/{matriculation}")
    public ResponseEntity<User> getUserByMatriculation(@PathVariable int matriculation) {
        Optional<User> userOptional = userRepository.findByMatriculation(matriculation);

        return userOptional.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = this.userRepository.save(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/update/{matriculation}")
    public ResponseEntity<User> updateUserByMatriculation(@PathVariable int matriculation, @RequestBody User user) {
        Optional<User> userOptional = this.userRepository.findByMatriculation(matriculation);

        if (userOptional.isPresent()) {
            User userToUpdate = userOptional.get();

            userToUpdate.setFirstName(user.getFirstName());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setDob(user.getDob());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setPassword(user.getPassword());

            if (userToUpdate.getLastUpdatedAt() == null) {
                userToUpdate.setLastUpdatedAt(LocalDateTime.now());
            }

            return new ResponseEntity<>(this.userRepository.save(userToUpdate), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/delete/{matriculation}")
    public ResponseEntity<Void> deleteUserByMatriculation(@PathVariable int matriculation) {
        Optional<User> userOptional = userRepository.findByMatriculation(matriculation);

        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
