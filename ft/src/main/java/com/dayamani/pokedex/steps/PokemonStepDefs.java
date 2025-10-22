package com.dayamani.pokedex.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PokemonStepDefs {
    @When("I call the pokemon endpoint with {string}")
    public void iCallThePokemonEndpointWith(String pokemonName) {

    }

    @Then("the response should have:")
    public void theResponseShouldHave(DataTable dataTable) {
        assertTrue(true);
    }
}
