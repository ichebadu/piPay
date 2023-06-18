package com.example.pipay.domain.repository;

import com.example.pipay.domain.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository <Loan, Long>{
}
