package com.example.pipay.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public final class LoanApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private  int amount;
    @ManyToOne
    private User borrower;
    private int repaymentDays;
    private double interestRate;

    public LoanApplication() {
    }

    public LoanApplication(int amount, User borrower, int repaymentTerm, double interestRate) {
        this.amount = amount;
        this.borrower = borrower;
        this.repaymentDays = repaymentTerm;
        this.interestRate = interestRate;
    }

    @Override
    public String toString() {
        return "LoanRequest{" +
                "amount=" + amount +
                ", borrower=" + borrower +
                ", repaymentTerm=" + repaymentDays +
                ", interestRate=" + interestRate +
                '}';
    }
}
