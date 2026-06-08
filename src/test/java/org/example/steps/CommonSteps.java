package org.example.steps;

import io.cucumber.java.en.Then;
import org.example.context.ScenarioContext;

import static org.assertj.core.api.Assertions.assertThat;

public class CommonSteps {

    private final ScenarioContext context;

    public CommonSteps(ScenarioContext context) {
        this.context = context;
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int expectedCode) {
        assertThat(context.getLastResponse().getStatusCode())
                .as("HTTP status code")
                .isEqualTo(expectedCode);
    }
}
