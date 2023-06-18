package com.example.pipay.domain.service;

import com.example.pipay.domain.entity.Loan;
import com.example.pipay.domain.entity.LoanApplication;
import com.example.pipay.domain.entity.User;
import com.example.pipay.domain.exception.LoanApplicationNotFoundException;
import com.example.pipay.domain.exception.UserNotFoundException;
import com.example.pipay.domain.repository.LoanApplicationRepository;
import com.example.pipay.domain.repository.LoanRepository;
import com.example.pipay.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoanServiceImpl {
    private final UserRepository userRepository;
    private final LoanApplicationRepository loanApplicationRepository;
    private final LoanRepository loanRepository;

    public void acceptLoan( final long loanApplicationId, final long lenderId ){
        User lender = userRepository.findById(lenderId).orElseThrow(()-> new UserNotFoundException(lenderId));
        LoanApplication loanApplication = loanApplicationRepository.findById(lenderId).orElseThrow(()-> new LoanApplicationNotFoundException(loanApplicationId));
        loanRepository.save(new Loan(lender,loanApplication));

    }
}
