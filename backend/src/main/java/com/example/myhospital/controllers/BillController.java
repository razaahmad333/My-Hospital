package com.example.myhospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myhospital.entities.Bill;
import com.example.myhospital.services.BillService;

@RestController
@RequestMapping(path="/bill")
public class BillController {
    
    @Autowired
    private BillService billService;

    @GetMapping(path="/all")
    public Iterable<Bill> getAllBills(){
        return billService.getAllBills();
    }
}
