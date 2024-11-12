package com.example.service;
import com.example.model.Doctor;
import com.example.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    // Register a new doctor
    public Doctor registerDoctor(String availability,
    		int experience,String name, String specialization) {
        Doctor doctor = new Doctor();
        doctor.setAvailability(availability);
        doctor.setExperience(experience);
        doctor.setName(name);
        doctor.setSpecialization(specialization);
        
        // Validate if doctor already exists (you can add a unique constraint check if required)
        if (doctorRepository.findByName(doctor.getName()) != null) {
            throw new IllegalArgumentException("Doctor with this name already exists.");
        }

        // Additional validations can be added here, e.g., checking experience or availability format
        if (doctor.getExperience() <= 0) {
            throw new IllegalArgumentException("Experience must be a positive number.");
        }
        return doctorRepository.save(doctor);
       
    }

    // Get all doctors
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Get doctors by specialization
    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization);
    }
}

