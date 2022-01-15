package com.main.itmo.backend.controllers;

import com.main.itmo.backend.DAO.UserDAO;
import com.main.itmo.backend.DAO.UserRepository;
import com.main.itmo.backend.entity.User;
import com.main.itmo.backend.security.JWT.JWTResponse;
import com.main.itmo.backend.security.JWT.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    JWTUtils jwtUtils;

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
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(newUser.getUsername(), newUser.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            // NICE CLASS NAME COLLISIONS
            org.springframework.security.core.userdetails.User user =
                    (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

            return ResponseEntity.ok(new JWTResponse(jwt, user.getUsername()));
        } catch (BadCredentialsException ex) {
            System.out.println("Failed Authorization");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }
}
