package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.AppointmentRequest;
import com.example.dto.AppointmentResponse;
import com.example.model.Appointment;
import com.example.model.Doctor;
import com.example.model.User;
import com.example.repository.AppointmentRepository;
import com.example.repository.DoctorRepository;
import com.example.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public AppointmentResponse createAppointment(AppointmentRequest request) {
    	// Fetch User and Doctor based on IDs in the request
        Optional<User> userOptional = userRepository.findById(request.getUserId());
        Optional<Doctor> doctorOptional = doctorRepository.findById(request.getDoctorId());

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        if (doctorOptional.isEmpty()) {
            throw new IllegalArgumentException("Doctor not found");
        }

        // Check if doctor is available at the requested date and time
        boolean isDoctorAvailable = appointmentRepository
            .findByDoctorIdAndAppointmentDate(request.getDoctorId(), request.getAppointmentDate())
            .isEmpty();

        if (!isDoctorAvailable) {
            throw new IllegalArgumentException("Doctor is not available at the requested time");
        }
    	// Create and save new appointment
        Appointment appointment = new Appointment();
        appointment.setUser(userOptional.get());
        appointment.setDoctor(doctorOptional.get());
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setStatus("Scheduled");

        Appointment savedAppointment = appointmentRepository.save(appointment);

        // Prepare and return the AppointmentResponse
        AppointmentResponse response = new AppointmentResponse();
        response.setId(savedAppointment.getId()); 
        response.setUserId(savedAppointment.getUser().getId());
        response.setDoctorId(savedAppointment.getDoctor().getId());
        response.setAppointmentDate(savedAppointment.getAppointmentDate());
        response.setDoctorName(savedAppointment.getDoctor().getName());  // Added doctor name
        response.setUserName(savedAppointment.getUser().getName());  // Added user name

        return response;
    }

    public List<Appointment> getUserAppointments(Long userId) {
        return appointmentRepository.findByUserId(userId);
    }

    public void cancelAppointment(Long appointmentId, Long userId) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentId);

        if (appointmentOptional.isPresent()) {
            Appointment appointment = appointmentOptional.get();
            LocalDateTime now = LocalDateTime.now();

            if (appointment.getAppointmentDate().isAfter(now.plusHours(24))) {
                appointmentRepository.delete(appointment);
            } else {
                throw new IllegalArgumentException("Cannot cancel within 24 hours of the scheduled time");
            }
        } else {
            throw new IllegalArgumentException("Appointment not found");
        }
    }
}
