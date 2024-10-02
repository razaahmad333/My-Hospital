package com.example.myhospital.DTOs;

public class LabTestReq {
    private long patientId;
    private long labTariffId;
    private long doctorId;

    private double price;
    private double discount;

    private double paidAmount;

    public long getPatientId() {
        return patientId;
    }

    public long getLabTariffId() {
        return labTariffId;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

}
