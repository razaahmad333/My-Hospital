package com.example.myhospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myhospital.entities.UhidSequence;
import com.example.myhospital.repositories.UhidSequenceRepository;

import jakarta.transaction.Transactional;

@Service
public class UhidSequenceService {

    @Autowired
    private UhidSequenceRepository uhidSequenceRepository;

    @Transactional
    public synchronized String generateUhid() {
        UhidSequence uhidSequence = uhidSequenceRepository.findById(1L)
                .orElseThrow(() -> new IllegalStateException("Uhid sequence not initialized"));

        int newValue = uhidSequence.getCurrentValue() + 1;

        uhidSequence.setCurrentValue(newValue);

        uhidSequenceRepository.save(uhidSequence);

        return "MH-" + String.format("%05d", newValue);
    }
}
