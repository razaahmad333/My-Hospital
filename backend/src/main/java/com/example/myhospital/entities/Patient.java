package com.example.myhospital.entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.example.myhospital.DTOs.PatientReq;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String uhid;

    @Column(unique = true)
    private String phone;
    private LocalDate dob;

    @JsonIgnore
    private String password;

    // @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    // private List<Appointment> appointments;

    public Patient() {
    }

    public void update(PatientReq patientReq) {
        this.name = patientReq.getName();
        this.phone = patientReq.getPhone();
        this.dob = patientReq.getDob();
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUhid() {
        return uhid;
    }

    public void setUhid(String uhid) {
        this.uhid = uhid;
    }

    public String getPassword() {
        return password;
    }

    // public List<Appointment> getAppointments() {
    //     return appointments;
    // }

    // public void setAppointments(List<Appointment> appointments) {
    //     this.appointments = appointments;
    // }

}
