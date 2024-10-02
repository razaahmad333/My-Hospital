package com.example.myhospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myhospital.DTOs.LabTestReq;
import com.example.myhospital.services.LabTestService;

@RestController
@RequestMapping(path = "/lab-test")
public class LabTestController {

    @Autowired
    private LabTestService labTestService;

    @PostMapping(path="/book")
    public ResponseEntity<?> create(@RequestBody LabTestReq labTestReq) {
        try {
            labTestService.create(labTestReq);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Failed", HttpStatus.CONFLICT);
        }
    }

}
