package com.example.pipay;

import com.example.pipay.domain.entity.User;
import com.example.pipay.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class PiPayApplication implements CommandLineRunner {


	private final UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(PiPayApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User("ichebadu","ichebadu","chukwu",14,"java developer"));
		userRepository.save(new User("iche","ichebadu","badu",24,"react developer"));
		userRepository.save(new User("chukwu","badu","chukwu",26,"javascript developer"));
		log.info(userRepository.findAll().toString());
	}
}
