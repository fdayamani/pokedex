package com.dayamani.pokedex.hello;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class GreetingHandler {
    public Mono<ServerResponse> hello(ServerRequest request) {
        String name = request.queryParam("name").orElse("Spring");
        return ServerResponse.ok().contentType(APPLICATION_JSON)
                .body(BodyInserters.fromValue(new Greeting("Hello, " + name + "!")));
    }
}
