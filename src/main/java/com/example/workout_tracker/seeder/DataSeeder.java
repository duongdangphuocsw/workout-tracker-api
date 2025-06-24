package com.example.workout_tracker.seeder;

import com.example.workout_tracker.model.Category;
import com.example.workout_tracker.model.Role;
import com.example.workout_tracker.model.User;
import com.example.workout_tracker.repository.CategoryRepository;
import com.example.workout_tracker.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(
            CategoryRepository categoryRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        seedCategories();
        seedAdminUser();
    }

    private void seedCategories() {
        List<String> categories = List.of("Cardio", "Strength", "Flexibility", "Duration", "Intensity");
        for (String name : categories) {
            if (!categoryRepository.existsByName(name)) {
                Category category = new Category();
                category.setName(name);
                categoryRepository.save(category);
            }
        }
    }

    private void seedAdminUser() {
        String adminEmail = "admin_workoutforlife@gmail.com";

        if (!userRepository.existsByEmail(adminEmail)) {
            User admin = new User();
            admin.setName("Admin");
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode("adminworkoutforlife")); // default password
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
        }
    }

}
