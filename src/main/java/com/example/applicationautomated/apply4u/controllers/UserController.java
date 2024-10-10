package com.example.applicationautomated.apply4u.controllers;

import com.example.applicationautomated.apply4u.models.User;
import com.example.applicationautomated.apply4u.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    //public ResponseEntity<String> signUp(@RequestBody NewUser user) {
    public ResponseEntity<?> signUp(@RequestBody User user){
       /* if (user.getFirstName() == null || user.getLastName() == null ||
                user.getEmail() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        } */
        try{
            //return ResponseEntity.ok("User registered successfully!");
            User newUser = userService.registerUser(user);
            return ResponseEntity.ok(newUser);

        }
        catch(Exception e){
            System.out.println("In Catch Block. Exception is caught");
            //return ResponseEntity.ok("Not Found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error","Failed to Register User"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        User user = userService.getUserByEmail(loginRequest.getEmail());
        if (user != null && (loginRequest.getPassword()).equals(user.getPassword())) {
            // Authentication successful
            return ResponseEntity.ok("Login successful");
        }
        // Authentication failed
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }

}


/*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// Other imports

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User newUser = userService.registerUser(user);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        User user = userService.getUserByEmail(loginRequest.getEmail());
        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            // Authentication successful
            return ResponseEntity.ok("Login successful");
        }
        // Authentication failed
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }
}
*/