package com.example.pipay.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Loan {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private User borrower;
    @ManyToOne
    private User lender;
    private int amount;
    private double interest;
    private LocalDate dateLent;
    private LocalDate dateDue;
    public Loan() {
    }

    public Loan(User lender, LoanApplication loanApplication) {
        this.borrower = loanApplication.getBorrower();
        this.lender = lender;
        this.amount = loanApplication.getAmount();
        this.interest = loanApplication.getInterestRate();
        this.dateLent = LocalDate.now();
        this.dateDue = LocalDate.now().plusDays(loanApplication.getRepaymentDays());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Loan loan)) return false;
        return id == loan.id && amount == loan.amount && Double.compare(loan.interest, interest) == 0 && Objects.equals(borrower, loan.borrower) && Objects.equals(lender, loan.lender) && Objects.equals(dateLent, loan.dateLent) && Objects.equals(dateDue, loan.dateDue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, borrower, lender, amount, interest, dateLent, dateDue);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", borrower=" + borrower +
                ", lender=" + lender +
                ", amount=" + amount +
                ", interest=" + interest +
                ", dateLent=" + dateLent +
                ", dateDue=" + dateDue +
                '}';
    }
}
