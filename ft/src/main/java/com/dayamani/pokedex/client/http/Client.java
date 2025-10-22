package com.dayamani.pokedex.client.http;

import com.dayamani.pokedex.config.AppProps;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class Client {
    private final WebClient webClient;

    public Client(AppProps appProps, WebClient.Builder webClientBuilder) {
        String baseUrl = appProps.getScheme() + "://" + appProps.getHost() + ":" + appProps.getPort();
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    public Response execute(Request request) {
        return executeRequest(request);
    }

    private Response executeRequest(Request request) {
        try {
            ClientResponse clientResponse = webClient
                    .method(HttpMethod.valueOf(request.getMethod()))
                    .uri(request.getPath())
                    .headers(headers -> request.getHeaders().forEach(headers::add))
                    .bodyValue(request.getBody() != null && !request.getBody().isBlank() ? request.getBody() : "")
                    .exchange()
                    .block();

            if (clientResponse == null) {
                throw new RuntimeException("Failed to get response for request: " + request);
            }

            return populateResponse(clientResponse);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send request " + request, e);
        }
    }

    private Response populateResponse(ClientResponse clientResponse) {
        String responseBody = clientResponse.bodyToMono(String.class).block();
        if (responseBody == null) {
            responseBody = "";
        }

        Map<String, String> headers = new HashMap<>();
        clientResponse.headers().asHttpHeaders().forEach((name, values) -> {
            if (!values.isEmpty()) {
                headers.put(name, String.join(", ", values));
            }
        });

        int statusCode = clientResponse.statusCode().value();
        return new Response(statusCode, responseBody, headers);
    }
}
