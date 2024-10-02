package com.example.myhospital.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.myhospital.entities.Patient;



public interface PatientRepository extends CrudRepository<Patient, Long> {
    Patient findByUhid(String uhid);
    Patient findByPhone(String phone);
}