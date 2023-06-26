package com.example.pipay.domain.service;

import com.example.pipay.domain.entity.User;
import com.example.pipay.domain.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    private final UserRepository userRepository;
    private final TokenValidationService tokenValidationService;

    public List<User> findAll(HttpServletRequest req){
        tokenValidationService.validationTokenAndGetUser(req.getHeader(HttpHeaders.AUTHORIZATION));
        return userRepository.findAll();
    }
}
