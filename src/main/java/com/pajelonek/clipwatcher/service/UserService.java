package com.pajelonek.clipwatcher.service;

import com.pajelonek.clipwatcher.domain.user.RegistrationResponse;
import com.pajelonek.clipwatcher.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final PasswordEncoder passwordEncoder;

    public UserService( PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<RegistrationResponse> register(User userRequest) {
        log.info("Incoming POST request for /register: " + userRequest);

        // todo validate data regex
        // todo check if user exits, if yes -> error handling
        // todo add + response
//        if (userMapper.checkIfUserExist(userRequest.getEmail())) {
//            throw new IllegalArgumentException("User already exists for this email");
//        }

        // encode password

        return new ResponseEntity<>(RegistrationResponse.builder().status("OK").build(), HttpStatus.OK);
    }

}
