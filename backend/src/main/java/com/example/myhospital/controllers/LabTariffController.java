package com.example.myhospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myhospital.DTOs.LabTariffReq;
import com.example.myhospital.entities.LabTariff;
import com.example.myhospital.services.LabTariffService;

@RestController
@RequestMapping(path = "/lab-tariff")
public class LabTariffController {

    @Autowired
    private LabTariffService labTariffService;


    @PostMapping()
    public ResponseEntity<?> create(@RequestBody LabTariffReq labTariffReq) {
        try {
            labTariffService.create(labTariffReq);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Failed", HttpStatus.CONFLICT);
        }
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody LabTariffReq labTariffReq) {
        try {
            labTariffService.update(id, labTariffReq);
            return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Failed", HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(path ="/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            labTariffService.delete(id);
            return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Failed", HttpStatus.CONFLICT);
        }
    }


    @GetMapping()
    public Iterable<LabTariff> getAll() {
        return labTariffService.getAll();
    }


    @GetMapping(path = "/{id}")
    public LabTariff getById(@PathVariable Long id) {
        return labTariffService.getById(id);
    }
}
