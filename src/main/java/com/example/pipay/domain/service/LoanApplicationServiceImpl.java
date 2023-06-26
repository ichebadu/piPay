package com.example.pipay.domain.service;

import com.example.pipay.application.LoanRequest;
import com.example.pipay.domain.entity.LoanApplication;
import com.example.pipay.domain.entity.User;
import com.example.pipay.domain.exception.UserNotFoundException;
import com.example.pipay.domain.repository.LoanApplicationRepository;
import com.example.pipay.domain.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LoanApplicationServiceImpl {
    private final LoanApplicationRepository loanApplicationRepository;
    private final UserRepository userRepository;
    private final TokenValidationService tokenValidationService;


    public LoanApplication requestLoan(LoanRequest request, HttpServletRequest req){
        User borrower = tokenValidationService.validationTokenAndGetUser(req.getHeader(HttpHeaders.AUTHORIZATION));
        Optional<User> findUser = userRepository.findById(borrower.getUsername());
        if(findUser.isPresent()){
            LoanApplication loanApplication = new LoanApplication(request.getAmount(), findUser.get(),request.getRepaymentInDays(),request.getInterestRate());
            return loanApplicationRepository.save(loanApplication);
        } else{
            throw new UserNotFoundException(borrower.getUsername());
        }
    }
    public List<LoanApplication> findAllLoanApplication(HttpServletRequest req){
        tokenValidationService.validationTokenAndGetUser(req.getHeader(HttpHeaders.AUTHORIZATION));
       return loanApplicationRepository.findAll();
    }
}
