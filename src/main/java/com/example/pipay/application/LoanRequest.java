package com.example.pipay.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public class LoanRequest {
    private final int amount;
    private final int repaymentInDays;
    private final double interestRate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoanRequest that)) return false;
        return amount == that.amount && repaymentInDays == that.repaymentInDays && Double.compare(that.interestRate, interestRate) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, repaymentInDays, interestRate);
    }

    @Override
    public String toString() {
        return "loanRequest{" +
                "amount=" + amount +
                ", repaymentInDays=" + repaymentInDays +
                ", interestRate=" + interestRate +
                '}';
    }
}
