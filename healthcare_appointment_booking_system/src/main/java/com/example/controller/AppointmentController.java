package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dto.AppointmentRequest;
import com.example.dto.AppointmentResponse;
import com.example.model.Appointment;
import com.example.service.AppointmentService;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    @PostMapping("/book")
    public ResponseEntity<AppointmentResponse> bookAppointment(@RequestBody AppointmentRequest request) {
    	  System.out.println("Received appointment request: " + request);
        try {
            AppointmentResponse response = appointmentService.createAppointment(request);
          
            return ResponseEntity.ok(response);  // Ensure the response is correctly returned
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);  // Handle any error appropriately
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Appointment>> getUserAppointments(@PathVariable Long userId) {
        List<Appointment> responses = appointmentService.getUserAppointments(userId);
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/cancel/{appointmentId}/{userId}")
    public ResponseEntity<String> cancelAppointment(@PathVariable Long appointmentId, @PathVariable Long userId) {
        appointmentService.cancelAppointment(appointmentId, userId);
        return ResponseEntity.ok("Appointment canceled successfully");
    }
}
