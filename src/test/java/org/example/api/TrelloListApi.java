package org.example.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TrelloListApi {

    public Response getListsOnBoard(String boardId) {
        return given(ApiClient.spec())
                .pathParam("id", boardId)
                .when()
                .get("/boards/{id}/lists")
                .then()
                .extract().response();
    }

    public Response createList(String name, String boardId) {
        return given(ApiClient.spec())
                .queryParam("name", name)
                .queryParam("idBoard", boardId)
                .when()
                .post("/lists")
                .then()
                .extract().response();
    }
}
