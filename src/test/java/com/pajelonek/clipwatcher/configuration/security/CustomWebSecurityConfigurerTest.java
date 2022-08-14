package com.pajelonek.clipwatcher.configuration.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@ActiveProfiles("DEV")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomWebSecurityConfigurerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void givenRequestWithWrongCredentials_shouldReturn401() {
        ResponseEntity<String> result = template.withBasicAuth("dummyUser", "dummyPassword")
                .getForEntity("/clips", String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }

    @Test
    public void givenRequestWithCorrectCredentials_shouldReturnMethodNotAllowed() {
        ResponseEntity<String> result = template.withBasicAuth("user", "password")
                .getForEntity("/clips", String.class);
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, result.getStatusCode());
    }

    @Test
    public void givenRequestToAppHealth_shouldReturn200() {
        ResponseEntity<String> result = template.getForEntity("/health", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenRequestToActuatorHealth_shouldReturn200() {
        ResponseEntity<String> result = template.getForEntity("/actuator/health", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}