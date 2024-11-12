package com.example.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AppointmentResponse {
private Long id;
private Long doctorId;
private String doctorName;
private Long userId;
private String userName;
private LocalDateTime appointmentDate;
}
