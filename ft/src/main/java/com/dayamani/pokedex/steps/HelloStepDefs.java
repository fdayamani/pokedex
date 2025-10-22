package com.dayamani.pokedex.steps;

import com.dayamani.pokedex.client.http.Request;
import com.dayamani.pokedex.config.FtConfiguration;
import com.dayamani.pokedex.scenario.ScenarioState;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@CucumberContextConfiguration
@ContextConfiguration(classes = FtConfiguration.class)
public class HelloStepDefs {
    private final Request request;
    private final ScenarioState scenarioState;

    public HelloStepDefs(Request request, ScenarioState scenarioState) {
        this.request = request;
        this.scenarioState = scenarioState;
    }

    @When("I call the hello endpoint with {string}")
    public void callTheService(String name) {
        String uri = "/hello";
        if (!"no name".equals(name)) {
            uri += "?name=" + name;
        }

        request.setPath(uri);
        request.setMethod("GET");
        request.addHeader("Accept", APPLICATION_JSON.toString());

        scenarioState.sendRequest();
    }

    @Then("the response should be {string}")
    public void verifyResponse(String expectedMessage) throws JsonProcessingException {
        String responseBody = scenarioState.getResponseState().body();
        String actualMessage = new ObjectMapper().readTree(responseBody).get("message").asText();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }
}
