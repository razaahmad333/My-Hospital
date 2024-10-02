package com.example.myhospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myhospital.DTOs.DoctorReq;
import com.example.myhospital.DTOs.UpdateDocFee;
import com.example.myhospital.entities.Doctor;
import com.example.myhospital.services.DoctorService;

@RestController
@RequestMapping(path = "/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping()
    public @ResponseBody Iterable<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody DoctorReq doctorReq) {
        try {
            doctorService.createDoctor(doctorReq);
            return new ResponseEntity<>("Doctor Created", HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Doctor already exists", HttpStatus.CONFLICT);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody DoctorReq doctorReq) {
        try {
            doctorService.update(id, doctorReq);
            return new ResponseEntity<>("Doctor Updated", HttpStatus.ACCEPTED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Doctor already exists", HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            doctorService.delete(id);
            return new ResponseEntity<>("Doctor Deleted", HttpStatus.ACCEPTED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Failed", HttpStatus.CONFLICT);
        }
    }

    @GetMapping(path = "/list")
    public Iterable<Doctor> getDoctors(@RequestParam String department) {
        return doctorService.findByDepartment(department);
    }

    @PutMapping(path = "/update-fee")
    public ResponseEntity<?> updateFee(@RequestBody UpdateDocFee updateDocFee) {
        try {
            doctorService.updateFee(updateDocFee);
            return new ResponseEntity<>("Doctor Fee updated", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Doctor Fee not updated", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/get-earning")
    public int getDoctorEarning(@RequestParam Long id) {
        return doctorService.getDoctorEarning(id);
    }

}
