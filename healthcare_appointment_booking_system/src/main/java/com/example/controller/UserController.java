package com.example.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.config.JwtUtil;
import com.example.dto.LoginRequest;
import com.example.dto.SignupRequest;
import com.example.dto.UpdateUserProfileRequest;
import com.example.model.User;
import com.example.service.UserService;


@RestController
@RequestMapping("/api/auth")
public class UserController {

	private final UserService userService;
    private final JwtUtil jwtUtil;

   
    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // for user registration
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignupRequest signupRequest) {
        try {
            // Register the user
            User user = userService.registerUser(signupRequest);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // user login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
       try {
            // Login the user and get JWT token
            String token = userService.loginUser(loginRequest);
            return ResponseEntity.ok(token);  // Send JWT token as response
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // updating user profile
    @PutMapping("/profile")
    public ResponseEntity<String> updateUserProfile(@RequestParam Long userId,
                                                    @RequestBody UpdateUserProfileRequest updateRequest) {
        try {
            // Update user profile
            User updatedUser = userService.updateUserProfile(userId, updateRequest);
            return ResponseEntity.ok("Profile updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
