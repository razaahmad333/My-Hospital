package com.example.myhospital.entities;

import com.example.myhospital.DTOs.LabTariffReq;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LabTariff {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String code;

    @Column(nullable = false)
    private double price;

    public LabTariff() {
    }

    public LabTariff(String name, String code, double price) {
        this.name = name;
        this.code = code;
        this.price = price;
    }

    public LabTariff(LabTariffReq labTariffReq) {
        this.name = labTariffReq.getName();
        this.code = labTariffReq.getCode();
        this.price = labTariffReq.getPrice();
    }

    public void update(LabTariffReq labTariffReq) {
        this.name = labTariffReq.getName();
        this.code = labTariffReq.getCode();
        this.price = labTariffReq.getPrice();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
