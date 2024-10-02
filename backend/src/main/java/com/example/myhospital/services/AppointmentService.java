package com.example.myhospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myhospital.DTOs.AppointmentReq;
import com.example.myhospital.entities.Appointment;
import com.example.myhospital.entities.Bill;
import com.example.myhospital.entities.Doctor;
import com.example.myhospital.entities.Patient;
import com.example.myhospital.repositories.AppointmentRepository;
import com.example.myhospital.repositories.DoctorRepository;
import com.example.myhospital.repositories.PatientRepository;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private BillService billService;

    public void createAppointment(AppointmentReq appointmentReq) {
        Appointment appointment = new Appointment();

        Doctor doctor = doctorRepository.findById(appointmentReq.getDoctorId()).orElse(null);
        Patient patient = patientRepository.findById(appointmentReq.getPatientId()).orElse(null);

        Bill bill = billService.generateBill(appointmentReq.getPaidAmount(), doctor.getFee(), 0, patient);

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentTime(appointmentReq.getAppointmentTime());
        appointment.setDoctorFee(doctor.getFee());
        appointment.setBill(bill);

        appointmentRepository.save(appointment);

    }

    public Iterable<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Iterable<Appointment> getAppointments(String department) {
        return appointmentRepository.findByDoctorAppointment(department);
    }
}
