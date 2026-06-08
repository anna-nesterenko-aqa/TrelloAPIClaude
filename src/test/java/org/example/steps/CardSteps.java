package org.example.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.example.api.CardApi;
import org.example.api.TrelloListApi;
import org.example.context.ScenarioContext;
import org.example.models.Card;
import org.example.models.TrelloList;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class CardSteps {

    private final ScenarioContext context;
    private final CardApi cardApi = new CardApi();
    private final TrelloListApi listApi = new TrelloListApi();

    public CardSteps(ScenarioContext context) {
        this.context = context;
    }

    @Given("the board has a list named {string}")
    public void getBoardList(String listName) {
        TrelloList[] lists = listApi.getListsOnBoard(context.getBoardId()).as(TrelloList[].class);
        String listId = Arrays.stream(lists)
                .filter(l -> listName.equals(l.getName()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("List '" + listName + "' not found on board"))
                .getId();
        context.setListId(listId);
    }

    @When("I create a card with name {string} on the list")
    public void createCard(String name) {
        Response response = cardApi.createCard(name, context.getListId());
        context.setLastResponse(response);
        context.setCardId(response.jsonPath().getString("id"));
    }

    @Given("a card named {string} exists on the list")
    public void aCardExists(String name) {
        Response response = cardApi.createCard(name, context.getListId());
        context.setCardId(response.jsonPath().getString("id"));
    }

    @When("I update the card name to {string}")
    public void updateCard(String name) {
        context.setLastResponse(cardApi.updateCard(context.getCardId(), name));
    }

    @When("I delete the card")
    public void deleteCard() {
        context.setLastResponse(cardApi.deleteCard(context.getCardId()));
        context.setCardId(null);
    }

    @Then("the card name should be {string}")
    public void verifyCardName(String expectedName) {
        Card card = context.getLastResponse().as(Card.class);
        assertThat(card.getName()).as("card name").isEqualTo(expectedName);
    }
}
