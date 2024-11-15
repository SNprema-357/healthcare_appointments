package com.example.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "doctors")
public class Doctor {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String availability;
	    private int experience;
	    private String name;
	    private String specialization;
	    
	   
	    @OneToMany(mappedBy = "doctor")
	    private List<Appointment> appointments;
}
