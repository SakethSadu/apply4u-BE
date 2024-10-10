package com.example.applicationautomated.apply4u.services;

import com.example.applicationautomated.apply4u.models.User;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private com.example.applicationautomated.apply4u.repositories.UserRepository UserRepository;

    //private PasswordEncoder passwordEncoder;

    public User registerUser(User user){
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        return UserRepository.save(user);
    }

    public User getUserByEmail(String email){
        return UserRepository.findByEmail(email).orElse(null);
    }
}
