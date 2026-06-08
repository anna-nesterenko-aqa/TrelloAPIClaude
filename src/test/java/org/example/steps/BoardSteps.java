package org.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.example.api.BoardApi;
import org.example.context.ScenarioContext;
import org.example.models.Board;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardSteps {

    private final ScenarioContext context;
    private final BoardApi boardApi = new BoardApi();

    public BoardSteps(ScenarioContext context) {
        this.context = context;
    }

    @Given("a board named {string} exists")
    public void aBoardExists(String name) {
        Response response = boardApi.createBoard(name);
        context.setBoardId(response.jsonPath().getString("id"));
    }

    @When("I create a board with name {string}")
    public void createBoard(String name) {
        Response response = boardApi.createBoard(name);
        context.setLastResponse(response);
        context.setBoardId(response.jsonPath().getString("id"));
    }

    @When("I get the board by id")
    public void getBoard() {
        context.setLastResponse(boardApi.getBoard(context.getBoardId()));
    }

    @When("I delete the board")
    public void deleteBoard() {
        context.setLastResponse(boardApi.deleteBoard(context.getBoardId()));
        context.setBoardId(null);
    }

    @Then("the board name should be {string}")
    public void verifyBoardName(String expectedName) {
        Board board = context.getLastResponse().as(Board.class);
        assertThat(board.getName()).as("board name").isEqualTo(expectedName);
    }
}
