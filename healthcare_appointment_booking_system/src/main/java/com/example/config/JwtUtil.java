package com.example.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.example.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private int jwtExpirationInMs = 86400000; // Use a more secure key in production
    private final UserDetailsService userDetailsService;

    // Constructor to inject UserDetailsService
    public JwtUtil(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // Generate JWT token for a user
    public String generateToken(UserDetails userDetails) {
    	 return Jwts.builder()
    	            .setSubject(userDetails.getUsername())
    	            .setIssuedAt(new Date())
    	            .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
    	            .signWith(SECRET_KEY)
    	            .compact();
    }

    // Extract username (email) from JWT token
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Validate the JWT token
    public boolean validateToken(String token) {
        String username = extractUsername(token);
        return (username != null && !username.isEmpty());
    }

    // Create an Authentication object from the JWT token
    public Authentication getAuthentication(String token) {
        String username = extractUsername(token); // Get username (email) from token
        UserDetails userDetails = userDetailsService.loadUserByUsername(username); // Load user details from UserDetailsService
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
