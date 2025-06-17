package com.example.workout_tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.example.workout_tracker.dto.LoginRequest;
import com.example.workout_tracker.dto.SignUpRequest;
import com.example.workout_tracker.model.Role;
import com.example.workout_tracker.model.User;
import com.example.workout_tracker.repository.UserRepository;
import com.example.workout_tracker.security.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired

    private PasswordEncoder passwordEncoder;
    @Autowired

    private JwtUtil jwtUtil;
    @Autowired

    private AuthenticationManager authManager;

    public String register(SignUpRequest request) {
        try {
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new RuntimeException("Email already in use");
            }

            User user = new User();
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(Role.USER); // Set default role to USER
            userRepository.save(user);
            return "User registered successfully.";
        } catch (Exception e) {
            throw new RuntimeException("User registration failed");
        }
    }

    public String authenticate(LoginRequest request) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword()));
            return jwtUtil.generateToken(request.getEmail());
        } catch (Exception e) {
            throw new RuntimeException("Invalid credentials");
        }
    }
}