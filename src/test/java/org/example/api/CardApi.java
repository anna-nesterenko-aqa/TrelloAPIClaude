package org.example.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CardApi {

    public Response createCard(String name, String listId) {
        return given(ApiClient.spec())
                .queryParam("name", name)
                .queryParam("idList", listId)
                .when()
                .post("/cards")
                .then()
                .extract().response();
    }

    public Response getCard(String cardId) {
        return given(ApiClient.spec())
                .pathParam("id", cardId)
                .when()
                .get("/cards/{id}")
                .then()
                .extract().response();
    }

    public Response updateCard(String cardId, String name) {
        return given(ApiClient.spec())
                .pathParam("id", cardId)
                .queryParam("name", name)
                .when()
                .put("/cards/{id}")
                .then()
                .extract().response();
    }

    public Response deleteCard(String cardId) {
        return given(ApiClient.spec())
                .pathParam("id", cardId)
                .when()
                .delete("/cards/{id}")
                .then()
                .extract().response();
    }
}
