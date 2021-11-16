package com.pajelonek.clipwatcher.security;

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
                .getForEntity("/demo/test", String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }

    @Test
    public void givenRequestWithCorrectCredentials_shouldReturn200() {
        ResponseEntity<String> result = template.withBasicAuth("user", "password")
                .getForEntity("/demo/test", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenRequestWithoutBasicAuth_shouldReturn401() {
        ResponseEntity<String> result = template.getForEntity("/demo/test", String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }
}