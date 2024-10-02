package com.example.myhospital.DTOs;

import java.util.Optional;

public class LabTariffReq {
    private String name;
    private Optional<String> code;
    private Optional<Double> price;

    public String getName() {
        return name.trim();
    }

    public String getCode() {
        if (!code.isPresent() || code.get().trim().isEmpty()) {
            return name.trim().replaceAll(" ", "-").toLowerCase();
        }
        return code.get();
    }

    public double getPrice() {
        return price.orElse(0.0);
    }

}
