package com.example.workout_tracker.dto;

import com.example.workout_tracker.model.Role;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private Role role;

    public UserDTO(Long id, String name, String email, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }
}
