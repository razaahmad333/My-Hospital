package com.example.myhospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myhospital.DTOs.LabTariffReq;
import com.example.myhospital.entities.LabTariff;
import com.example.myhospital.repositories.LabTariffRepository;

@Service
public class LabTariffService {

    @Autowired
    private LabTariffRepository labTariffRepository;

    public void create(LabTariffReq labTariffReq) {
        labTariffRepository.save(new LabTariff(labTariffReq));
    }

    public void update(Long id, LabTariffReq labTariffReq) {
        LabTariff labTariff = labTariffRepository.findById(id).orElseThrow();
        labTariff.update(labTariffReq);
        labTariffRepository.save(labTariff);
    }

    public void delete(Long id) {
        labTariffRepository.deleteById(id);
    }

    public Iterable<LabTariff> getAll() {
        return labTariffRepository.findAll();
    }

    public LabTariff getById(Long id) {
        return labTariffRepository.findById(id).orElseThrow();
    }
}
