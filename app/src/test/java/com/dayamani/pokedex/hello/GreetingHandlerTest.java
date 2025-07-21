package com.dayamani.pokedex.hello;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.mock;

class GreetingHandlerTest {
    @Test
    void hello_returnsGreeting() {
        GreetingHandler handler = new GreetingHandler();

        ServerRequest request = mock(ServerRequest.class);

        Mono<ServerResponse> responseMono = handler.hello(request);

        StepVerifier.create(responseMono)
                .expectNextMatches(response -> response.statusCode().is2xxSuccessful())
                .verifyComplete();
    }
}
