package com.example.model;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
@Entity
@Data
public class User implements UserDetails{
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;
	    private String email;
	    private String password;
	    private String phone;
		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub
			  return Collections.emptyList();
		}
		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return null;
		}
		public Collection<? extends GrantedAuthority> getRoles() {
			// TODO Auto-generated method stub
			return null;
		}
		
}
