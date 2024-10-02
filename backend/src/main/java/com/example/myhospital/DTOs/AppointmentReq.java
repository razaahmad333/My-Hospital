package com.example.myhospital.DTOs;

import java.time.LocalDateTime;
import java.util.Optional;

public class AppointmentReq {
    private Long doctorId;
    private Long patientId;
    private LocalDateTime appointmentTime;

    private Optional<Double> paidAmount;

    public AppointmentReq() {
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public double getPaidAmount() {
        return paidAmount.orElse(0.0);
    }

}
