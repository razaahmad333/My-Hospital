package com.example.myhospital.DTOs;

import java.time.LocalDate;

public class PatientReq {
    private String name;
    private String phone;
    private LocalDate dob;
    private String password;

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getPassword() {
        return password;
    }


}
