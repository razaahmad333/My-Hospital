package com.example.myhospital.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.myhospital.entities.Appointment;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {


    @Query("SELECT a FROM Appointment a JOIN a.doctor d WHERE d.department = :department")
    Iterable<Appointment> findByDoctorAppointment(String department);
}
