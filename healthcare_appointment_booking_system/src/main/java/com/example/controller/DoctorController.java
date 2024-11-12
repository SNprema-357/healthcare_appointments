package com.example.controller;

import com.example.model.Doctor;
import com.example.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // API Endpoint for doctor registration
    @PostMapping("/register")
    public ResponseEntity<String> registerDoctor(
            @RequestParam String availability,
            @RequestParam int experience,
            @RequestParam String name,
            @RequestParam String specialization) {
        try {
            // Register the doctor
            Doctor savedDoctor = doctorService.registerDoctor(availability, experience, name, specialization);
            return ResponseEntity.ok("Doctor registered successfully with ID: " + savedDoctor.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // To get all doctors
    @GetMapping("/list")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        try {
            List<Doctor> doctors = doctorService.getAllDoctors();
            return ResponseEntity.ok(doctors);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Get doctors by specialization
    @GetMapping("/specialization")
    public ResponseEntity<List<Doctor>> getDoctorsBySpecialization(@RequestParam String specialization) {
        try {
            List<Doctor> doctors = doctorService.getDoctorsBySpecialization(specialization);
            return ResponseEntity.ok(doctors);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
