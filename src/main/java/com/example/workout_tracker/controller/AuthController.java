package com.example.workout_tracker.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.example.workout_tracker.dto.AuthResponse;
import com.example.workout_tracker.dto.LoginRequest;
import com.example.workout_tracker.dto.SignUpRequest;
import com.example.workout_tracker.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody LoginRequest request) {
        String token = authService.authenticate(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Validated @RequestBody SignUpRequest request) {
        String result = authService.register(request);
        if (result == null) {
            return ResponseEntity.badRequest().body("User registration failed");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {

        return ResponseEntity.badRequest().body(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }
}
