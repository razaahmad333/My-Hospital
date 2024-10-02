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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myhospital.DTOs.PatientReq;
import com.example.myhospital.entities.Patient;
import com.example.myhospital.services.PatientService;
import com.example.myhospital.utils.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping()
    public @ResponseBody Iterable<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Patient getById(@PathVariable Long id) {
        return patientService.getById(id);
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody PatientReq patientReq) {
        try {
            patientService.createPatient(patientReq);
            return new ResponseEntity<>("Patient Created", HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Failed", HttpStatus.CONFLICT);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PatientReq patientReq) {
        try {
            patientService.update(id, patientReq);
            return new ResponseEntity<>("Patient Updated", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed", HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            patientService.delete(id);
            return new ResponseEntity<>("Patient Deleted", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed", HttpStatus.CONFLICT);
        }
    }

    // @PostMapping(path = "/login")
    // public String login(@RequestParam String phone, @RequestParam String
    // password) {
    // String token = patientService.login(phone, password);

    // return String.format(redirecturl, "/patient/login-success.html?token=" +
    // token);
    // }

    @GetMapping(path = "/me")
    public @ResponseBody Patient getMyPatient(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");
        String phone = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            phone = jwtUtil.extractUsername(jwt);
        }

        if (phone != null && jwtUtil.validateToken(jwt, jwt)) {

        }

        return patientService.loadPatientByPhone(phone);
    }

}
