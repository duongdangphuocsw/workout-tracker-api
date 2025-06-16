// package com.example.workout_tracker.security;

// import java.util.Date;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Component;

// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;

// @Component
// public class JwtUtil {
// @Value("${jwt.secret}")
// private String secret;

// @Value("${jwt.expiration}")
// private long expirationTime;

// public String generateToken(String username) {
// return Jwts.builder()
// .setSubject(username)
// .setIssuedAt(new Date())
// .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
// .signWith(SignatureAlgorithm.HS512, secret)
// .compact();
// }

// public String extractEmail(String token) {
// return Jwts.parser().setSigningKey(secret)
// .parseClaimsJws(token)
// .getBody()
// .getSubject();
// }

// public boolean validateToken(String token, UserDetails userDetails) {
// String email = extractEmail(token);
// return email.equals(userDetails.getUsername());
// }
// }
