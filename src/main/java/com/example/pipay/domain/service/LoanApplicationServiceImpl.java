package com.example.pipay.domain.service;

import com.example.pipay.application.LoanRequest;
import com.example.pipay.domain.entity.LoanApplication;
import com.example.pipay.domain.entity.User;
import com.example.pipay.domain.exception.UserNotFoundException;
import com.example.pipay.domain.repository.LoanApplicationRepository;
import com.example.pipay.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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


    public LoanApplication requestLoan(LoanRequest request){
        Optional<User> findUser = userRepository.findById(request.getBorrowerId());
        if(findUser.isPresent()){
            LoanApplication loanApplication = new LoanApplication(request.getAmount(), findUser.get(),request.getRepaymentInDays(),request.getInterestRate());
            return loanApplicationRepository.save(loanApplication);
        } else{
            throw new UserNotFoundException(request.getBorrowerId());
        }
    }
    public List<LoanApplication> findAllLoanApplication(){
       return loanApplicationRepository.findAll();
    }
    @PostMapping("/loan/accept/{lenderId}/{loanApplicationId}")
    public void acceptLoan(@PathVariable final String lenderId, @PathVariable final String loanApplicationId){

    }
}
