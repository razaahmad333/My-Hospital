package com.example.myhospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myhospital.entities.Bill;
import com.example.myhospital.entities.Patient;
import com.example.myhospital.repositories.BillRepository;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public Bill generateBill(double amountPaid, double billAmount, double billDiscount, Patient patient) {
        Bill newBill = new Bill();
        newBill.setBillAmount(billAmount);
        newBill.setAmountPaid(amountPaid);
        newBill.setPatient(patient);
        newBill.setDiscount(billDiscount);
        return billRepository.save(newBill);
    }

    public Iterable<Bill> getAllBills() {
        return billRepository.findAll();
    }
}
