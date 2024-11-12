package com.example.config;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.model.User;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    private final User user; // Your custom User model

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return user authorities (roles, permissions, etc.)
        return user.getRoles(); // Modify based on your User model
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // Return the user's encrypted password
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // Return the email or username for authentication
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Define according to your requirements
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Define according to your requirements
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Define according to your requirements
    }

    @Override
    public boolean isEnabled() {
        return true; // Define according to your requirements
    }

    public User getUser() {
        return user; // Access the custom User object
    }
}


