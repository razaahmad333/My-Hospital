package com.example.myhospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myhospital.DTOs.LabTestReq;
import com.example.myhospital.entities.Bill;
import com.example.myhospital.entities.Doctor;
import com.example.myhospital.entities.LabTariff;
import com.example.myhospital.entities.LabTest;
import com.example.myhospital.entities.Patient;
import com.example.myhospital.repositories.LabTestRepository;

@Service
public class LabTestService {

    @Autowired
    private LabTestRepository labTestRepository;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private LabTariffService labTariffService;

    @Autowired
    private BillService billService;

    public void create(LabTestReq labTestReq) {
        LabTest labTest = new LabTest();

        Patient patient = patientService.getById(labTestReq.getPatientId());
        Doctor doctor = doctorService.getById(labTestReq.getDoctorId());
        LabTariff labTariff = labTariffService.getById(labTestReq.getLabTariffId());

        Bill bill = billService.generateBill(
                labTestReq.getPaidAmount(),
                labTestReq.getPrice(),
                labTestReq.getDiscount(),
                patient);

        labTest.setPatient(patient);
        labTest.setDoctor(doctor);
        labTest.setLabTariff(labTariff);
        labTest.setBill(bill);

        labTestRepository.save(labTest);
    }
}
