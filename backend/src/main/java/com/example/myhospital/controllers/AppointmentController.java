package com.example.myhospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myhospital.DTOs.AppointmentReq;
import com.example.myhospital.entities.Appointment;
import com.example.myhospital.services.AppointmentService;

@RestController
@RequestMapping(path = "/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @PostMapping(path = "/create")
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentReq appointmentReq) {
        try {
            appointmentService.createAppointment(appointmentReq);
            return new ResponseEntity<>("Appointment created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Appointment not created", HttpStatus.CONFLICT);

        }
    }

    @GetMapping(path = "/list")
    public @ResponseBody Iterable<Appointment> getAppointments(@RequestParam String department) {
        return appointmentService.getAppointments(department);
    }

}
