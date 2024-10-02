package com.example.myhospital.DTOs;

public class DoctorReq {
    private String name;
    private String department;
    private String qualification;
    private double fee;

    public DoctorReq() {
    }

    public DoctorReq(String name, String department, String qualification, double fee) {
        this.name = name;
        this.department = department;
        this.qualification = qualification;
        this.fee = fee;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getQualification() {
        return qualification;
    }

    public double getFee() {
        return fee;
    }

}
