package com.example.pipay.domain.service;

import com.example.pipay.domain.entity.User;
import com.example.pipay.domain.exception.UserNotFoundException;
import com.example.pipay.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenValidationService {
    private final UserRepository userRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final String securityContextBaseUrl;

    public TokenValidationService(UserRepository userRepository, @Value("${security.baseurl}") final String securityContextBaseUrl) {
        this.userRepository = userRepository;
        this.securityContextBaseUrl = securityContextBaseUrl;
    }

    public User validationTokenAndGetUser(final String token ){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(httpHeaders.AUTHORIZATION, token);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        ResponseEntity<String> response = restTemplate
                .exchange(securityContextBaseUrl + "/user/validate", HttpMethod.POST, httpEntity, String.class);
        if(response.getStatusCode().equals(HttpStatus.OK)){
            return userRepository.findById(response.getBody())
                    .orElseThrow(()-> new UserNotFoundException(response.getBody()));
        }
        throw new RuntimeException("Invalid token");
    }
}
