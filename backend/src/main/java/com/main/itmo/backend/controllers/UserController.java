package com.main.itmo.backend.controllers;

import com.main.itmo.backend.DAO.UserDAO;
import com.main.itmo.backend.DAO.UserRepository;
import com.main.itmo.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @CrossOrigin
    @PostMapping("/sign_up")
    public ResponseEntity<?> createUser(@RequestBody User newUser) {
        if (userRepository.findByUsername(newUser.getUsername()) != null) {
            System.out.println("username Already exist " + newUser.getUsername());
            return new ResponseEntity<>(
                    new RuntimeException("User with username " + newUser.getUsername() + "already exist"),
                    HttpStatus.CONFLICT);

        }
        System.out.println("user registered " + newUser.getUsername());
        return new ResponseEntity<>(userRepository.save(new User(newUser.getUsername(),
                encoder.encode(newUser.getPassword()))), HttpStatus.CREATED);
    }

    @CrossOrigin
    @PostMapping("/sign_in")
    public ResponseEntity<?> authUser(@RequestBody User newUser) {
        User userForCheck = userRepository.findByUsername(newUser.getUsername());
        System.out.println(userForCheck);
        if (userForCheck != null) {
            System.out.println(newUser.getPassword());
            System.out.println(encoder.encode(userForCheck.getPassword()));
            System.out.println(encoder.encode(newUser.getPassword()));
            if (encoder.encode(userForCheck.getPassword()).equals(encoder.encode(newUser.getPassword()))) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else { return new ResponseEntity<>(HttpStatus.FORBIDDEN); }
        }
        System.out.println("here");
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
