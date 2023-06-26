package com.example.pipay.domain.service;

import com.example.pipay.domain.entity.Loan;
import com.example.pipay.domain.entity.LoanApplication;
import com.example.pipay.domain.entity.User;
import com.example.pipay.domain.exception.LoanApplicationNotFoundException;
import com.example.pipay.domain.exception.UserNotFoundException;
import com.example.pipay.domain.repository.LoanApplicationRepository;
import com.example.pipay.domain.repository.LoanRepository;
import com.example.pipay.domain.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LoanServiceImpl {
    private final UserRepository userRepository;
    private final LoanApplicationRepository loanApplicationRepository;
    private final LoanRepository loanRepository;
    private final TokenValidationService tokenValidationService;

    public void acceptLoan(final long loanApplicationId, HttpServletRequest req){
        User len = tokenValidationService.validationTokenAndGetUser(req.getHeader(HttpHeaders.AUTHORIZATION));
        User lender = userRepository.findById(len.getUsername()).orElseThrow(()-> new UserNotFoundException(len.getUsername()));
        LoanApplication loanApplication = loanApplicationRepository.findById(loanApplicationId).orElseThrow(()-> new LoanApplicationNotFoundException(loanApplicationId));
        loanRepository.save(new Loan(lender,loanApplication));
    }
    public List<Loan> getLoans(HttpServletRequest req){
        tokenValidationService.validationTokenAndGetUser(req.getHeader(HttpHeaders.AUTHORIZATION));
        return loanRepository.findAll();
    }
}
