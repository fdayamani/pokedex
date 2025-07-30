package com.dayamani.pokedex.steps;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@CucumberContextConfiguration
public class HelloStepDefs {
    private String response;

    @When("I call the hello endpoint")
    public void callTheService() {
        WebClient webClient = WebClient.create("http://localhost:8080");
        Mono<String> response = webClient.get()
                .uri("/hello")
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
        this.response = response.block();
    }

    @Then("the response should be {string}")
    public void verifyResponse(String expectedMessage) throws JsonProcessingException {
        String actualMessage = new ObjectMapper().readTree(response).get("message").asText();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }
}
