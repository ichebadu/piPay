package com.example.pipay.application;

import com.example.pipay.domain.entity.LoanApplication;
import com.example.pipay.domain.entity.User;
import com.example.pipay.domain.service.LoanApplicationServiceImpl;
import com.example.pipay.domain.service.LoanServiceImpl;
import com.example.pipay.domain.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping("/loan/request")
    public LoanApplication requestLoan(@RequestBody final LoanRequest loanRequest){
        return loanApplicationService.requestLoan(loanRequest);
    }
    @GetMapping("/loan/requests")
    public List<LoanApplication> findAllLoanApplication(){
        return loanApplicationService.findAllLoanApplication();
    }
    @GetMapping("/users")
    public List<User> findAll(){
        log.info("user");
        return userService.findAll();
    }
    @PostMapping("/loan/accept/{lenderId}/{loanApplicationId}")
    public void acceptLoan(@PathVariable final String lenderId,
                           @PathVariable final String loanApplicationId){
        loanService.acceptLoan(Long.parseLong(loanApplicationId), Long.parseLong(lenderId));
    }
}