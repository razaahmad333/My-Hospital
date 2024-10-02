package com.example.myhospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myhospital.DTOs.DoctorReq;
import com.example.myhospital.DTOs.UpdateDocFee;
import com.example.myhospital.entities.Doctor;
import com.example.myhospital.repositories.DoctorRepository;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public void createDoctor(String name, String department, String qualification) {
        Doctor newDoctor = new Doctor(name, department, qualification);
        doctorRepository.save(newDoctor);
    }

    public void createDoctor(DoctorReq doctorReq) {
        Doctor newDoctor = new Doctor();
        newDoctor.update(doctorReq);
        doctorRepository.save(newDoctor);
    }

    public void update(Long id, DoctorReq doctorReq) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow();
        doctor.update(doctorReq);
        doctorRepository.save(doctor);
    }

    public void delete(Long id) {
        doctorRepository.deleteById(id);
    }

    public Doctor getById(Long id) {
        return doctorRepository.findById(id).orElseThrow();
    }

    public Iterable<Doctor> findByDepartment(String department) {
        return doctorRepository.findByDepartment(department);
    }

    public Iterable<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public void updateFee(UpdateDocFee updateDocFee) {
        Doctor doctor = doctorRepository.findById(updateDocFee.getId()).orElse(null);
        doctor.setFee(updateDocFee.getFee());
        doctorRepository.save(doctor);
    }

    public int getDoctorEarning(Long id) {
        return doctorRepository.getDoctorEarning(id);
    }
}
