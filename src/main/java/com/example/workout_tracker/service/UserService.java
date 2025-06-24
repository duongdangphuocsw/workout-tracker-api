package com.example.workout_tracker.service;

import com.example.workout_tracker.dto.UserDTO;
import com.example.workout_tracker.model.User;
import com.example.workout_tracker.repository.UserRepository;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get current user
    public UserDTO getCurrentUser(String email) {
        // Check if user exists
        if (!userRepository.existsByEmail(email)) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = userRepository.findByEmail(email);

        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> updateUser(Long id, User newUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            user.setPassword(newUser.getPassword());
            return userRepository.save(user);
        });
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
