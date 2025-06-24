package com.example.workout_tracker.dto;

import jakarta.validation.constraints.NotNull;

public class CreateCategoryDTO {
    @NotNull(message = "Name is required")
    private String name;

    public CreateCategoryDTO() {
    }

    public CreateCategoryDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
