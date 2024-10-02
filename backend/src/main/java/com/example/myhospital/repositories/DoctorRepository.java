package com.example.myhospital.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.myhospital.entities.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    Iterable<Doctor> findByDepartment(String department);

    @Query("SELECT SUM(a.doctorFee) from Appointment a WHERE a.doctor.id = :doctorId")
    int getDoctorEarning(Long doctorId);
}
