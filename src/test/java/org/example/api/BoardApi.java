package org.example.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BoardApi {

    public Response createBoard(String name) {
        return given(ApiClient.spec())
                .queryParam("name", name)
                .when()
                .post("/boards")
                .then()
                .extract().response();
    }

    public Response getBoard(String boardId) {
        return given(ApiClient.spec())
                .pathParam("id", boardId)
                .when()
                .get("/boards/{id}")
                .then()
                .extract().response();
    }

    public Response deleteBoard(String boardId) {
        return given(ApiClient.spec())
                .pathParam("id", boardId)
                .when()
                .delete("/boards/{id}")
                .then()
                .extract().response();
    }
}
