package com.example.myhospital.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.myhospital.entities.Bill;

public interface BillRepository extends CrudRepository<Bill, Long> {

}
