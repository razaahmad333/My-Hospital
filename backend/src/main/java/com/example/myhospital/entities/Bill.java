package com.example.myhospital.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.example.myhospital.utils.Bill.BillStatus;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private double billAmount = 0.0;
    private double amountPaid = 0.0;
    private double discount = 0.0;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime billedAt;

    @Transient
    private BillStatus billStatus;

    public long getId() {
        return id;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    public BillStatus getBillStatus() {
        if (billAmount == amountPaid) {
            return BillStatus.PAID;
        } else if (billAmount > amountPaid) {
            return BillStatus.PENDING;
        } else {
            return BillStatus.REFUND;
        }
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

}
