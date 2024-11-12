package com.example.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy; // Added for @Lazy annotation
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.config.JwtUtil;
import com.example.dto.LoginRequest;
import com.example.dto.SignupRequest;
import com.example.dto.UpdateUserProfileRequest;
import com.example.model.User;
import com.example.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, 
                       @Lazy AuthenticationManager authenticationManager, @Lazy JwtUtil jwtUtil) { // Added @Lazy here
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    // Method for loading user by email for authentication
    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
    

    // Register a new user
    public User registerUser(SignupRequest signupRequest) {
        if (userRepository.findByEmail(signupRequest.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }

        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword())); // Encrypt the password
        user.setName(signupRequest.getName());
        user.setPhone(signupRequest.getPhone());

        return userRepository.save(user); // Save the user to the database
    }

    // Log in a user and generate JWT token
    public String loginUser(LoginRequest loginRequest) {
        // Authenticating the user
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        // Generate JWT token after successful authentication
        UserDetails userDetails = loadUserByUsername(loginRequest.getEmail());
        return jwtUtil.generateToken(userDetails); // Return JWT token
    }

    // Update user profile (excluding password)
    public User updateUserProfile(Long userId, UpdateUserProfileRequest updateRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        if (updateRequest.getName() != null) {
            user.setName(updateRequest.getName());
        }
        if (updateRequest.getPhone() != null) {
            user.setPhone(updateRequest.getPhone());
        }

        return userRepository.save(user); // Save the updated user profile
    }
}
