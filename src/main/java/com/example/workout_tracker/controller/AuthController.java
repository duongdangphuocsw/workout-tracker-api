// package com.example.workout_tracker.controller;

// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.http.ResponseEntity;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.BadCredentialsException;
// import
// org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.validation.annotation.Validated;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.MethodArgumentNotValidException;

// import com.example.workout_tracker.dto.AuthRequest;
// import com.example.workout_tracker.dto.AuthResponse;
// import com.example.workout_tracker.security.JwtUtil;

// @RestController
// @RequestMapping("/auth")
// @Validated
// public class AuthController {

// @Autowired
// private AuthenticationManager authenticationManager;

// @Autowired
// private JwtUtil jwtUtil;

// @PostMapping("/login")
// public ResponseEntity<?> login(@Validated @RequestBody AuthRequest request) {
// try {
// Authentication auth = authenticationManager.authenticate(
// new UsernamePasswordAuthenticationToken(request.getEmail(),
// request.getPassword()));
// String token = jwtUtil.generateToken(auth.getName());
// return ResponseEntity.ok(new AuthResponse(token));
// } catch (BadCredentialsException e) {
// return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or
// password");
// } catch (Exception e) {
// return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
// .body("An error occurred during authentication");
// }
// }

// @ExceptionHandler(MethodArgumentNotValidException.class)
// public ResponseEntity<?>
// handleValidationExceptions(MethodArgumentNotValidException ex) {

// return
// ResponseEntity.badRequest().body(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
// }
// }
