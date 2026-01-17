package com.employee.Employee_Management.service;

import com.employee.Employee_Management.entity.User;
import com.employee.Employee_Management.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void registerUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if(user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER"); // just USER, not ROLE_USER
        }
        userRepository.save(user);
    }

}
