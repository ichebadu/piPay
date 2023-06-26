package com.example.pipay.application;

import com.example.pipay.domain.entity.Loan;
import com.example.pipay.domain.entity.LoanApplication;
import com.example.pipay.domain.entity.User;
import com.example.pipay.domain.service.LoanApplicationServiceImpl;
import com.example.pipay.domain.service.LoanServiceImpl;
import com.example.pipay.domain.service.TokenValidationService;
import com.example.pipay.domain.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
@Slf4j
@RequiredArgsConstructor
public class LoanController {
    private final UserServiceImpl userService;
    private final LoanApplicationServiceImpl loanApplicationService;
    private final LoanServiceImpl loanService;
    private final TokenValidationService tokenValidationService;

    @PostMapping("/loan/request")
    public LoanApplication requestLoan(@RequestBody final LoanRequest loanRequest, HttpServletRequest request){
        return loanApplicationService.requestLoan(loanRequest,request);
    }
    @GetMapping("/loan/requests")
    public List<LoanApplication> findAllLoanApplication(HttpServletRequest request){
        return loanApplicationService.findAllLoanApplication(request);
    }
    @GetMapping("/users")
    public List<User> findAll(HttpServletRequest request){
        log.info("user");
        return userService.findAll(request);
    }
    @PostMapping("/loan/accept/{loanApplicationId}")
    public void acceptLoan(@PathVariable final String loanApplicationId,
                           HttpServletRequest request){
        loanService.acceptLoan(Long.parseLong(loanApplicationId), (request));
    }
    @GetMapping("/loans")
    public List<Loan> getLoans(HttpServletRequest request){
        return loanService.getLoans(request);
    }
}