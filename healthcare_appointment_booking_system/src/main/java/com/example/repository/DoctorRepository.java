package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{
	List<Doctor> findBySpecialization(String specialization);
	 // Find doctors by specialization
    
    
    // Find all doctors (to list all)
    List<Doctor> findAll();
    Doctor findByName(String name);

}
