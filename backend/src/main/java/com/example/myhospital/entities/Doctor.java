package com.example.myhospital.entities;

import java.util.List;

import com.example.myhospital.DTOs.DoctorReq;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String name;

    private String department;
    private String qualification;

    private double fee = 100;

    // @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    // private List<Appointment> appointments;

    public Doctor() {
    }

    public Doctor(String name, String department, String qualification) {
        this.name = name;
        this.department = department;
        this.qualification = qualification;
    }

    public void update(DoctorReq doctorReq) {
        this.name = doctorReq.getName();
        this.department = doctorReq.getDepartment();
        this.qualification = doctorReq.getQualification();
        this.fee = doctorReq.getFee();
    }

    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
    public double getFee() {
        return fee;
    }


    // public List<Appointment> getAppointments() {
    //     return appointments;
    // }

    // public void setAppointments(List<Appointment> appointments) {
    //     this.appointments = appointments;
    // }

}
