package com.example.myhospital.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.myhospital.DTOs.PatientReq;
import com.example.myhospital.entities.Patient;
import com.example.myhospital.repositories.PatientRepository;
import com.example.myhospital.utils.JwtUtil;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UhidSequenceService uhidSequenceService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public Patient createPatient(PatientReq patientReq) {
        Patient newPatient = new Patient();
        newPatient.update(patientReq);
        String uhid = uhidSequenceService.generateUhid();
        newPatient.updatePassword(passwordEncoder.encode(patientReq.getPassword()));
        newPatient.setUhid(uhid);
        return patientRepository.save(newPatient);
    }

    public void update(Long id, PatientReq patientReq) {
        Patient patient = patientRepository.findById(id).orElseThrow();
        patient.update(patientReq);
        patientRepository.save(patient);
    }

    public void delete(Long id) {
        patientRepository.deleteById(id);
    }

    public Patient getById(Long id) {
        return patientRepository.findById(id).orElseThrow();
    }

    public Iterable<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient loadPatientByPhone(String phone) {
        return patientRepository.findByPhone(phone);
    }

    public String login(String phone, String password) {

        Patient patient = patientRepository.findByPhone(phone);

        if (patient == null) {
            throw new RuntimeException("Patient not found");
        }

        boolean passwordMatch = passwordEncoder.matches(password, patient.getPassword());

        if (!passwordMatch) {
            throw new RuntimeException("Invalid Password");
        }

        String token = jwtUtil.generateToken(phone);

        return token;
    }

}
