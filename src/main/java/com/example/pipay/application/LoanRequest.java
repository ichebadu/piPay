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
    private final long borrowerId;
    private final int repaymentInDays;
    private final double interestRate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoanRequest that)) return false;
        return amount == that.amount && borrowerId == that.borrowerId && repaymentInDays == that.repaymentInDays && Double.compare(that.interestRate, interestRate) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, borrowerId, repaymentInDays, interestRate);
    }

    @Override
    public String toString() {
        return "loanRequest{" +
                "amount=" + amount +
                ", borrowerId=" + borrowerId +
                ", repaymentInDays=" + repaymentInDays +
                ", interestRate=" + interestRate +
                '}';
    }
}
