package com.example.myhospital.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class LabTest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "lab_tariff_id", nullable = false)
    private LabTariff labTariff;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name = "bill_id", nullable = false)
    private Bill bill;

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setLabTariff(LabTariff labTariff) {
        this.labTariff = labTariff;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public long getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public LabTariff getLabTariff() {
        return labTariff;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Bill getBill() {
        return bill;
    }

}
