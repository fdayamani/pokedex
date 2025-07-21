package com.dayamani.pokedex.hello;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import reactor.test.StepVerifier;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource(properties = "spring.main.web-application-type=reactive")
public class GreetingClientTest {

    @Autowired
    private GreetingClient greetingClient;

    @Test
    void testGetGreeting() {
        StepVerifier.create(greetingClient.getMessage())
                .expectNextMatches(greeting -> greeting.message().equals("Hello, Spring!"))
                .verifyComplete();
    }

}
