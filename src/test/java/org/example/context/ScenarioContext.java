package org.example.context;

import io.restassured.response.Response;

public class ScenarioContext {

    private Response lastResponse;
    private String boardId;
    private String listId;
    private String cardId;

    public Response getLastResponse() { return lastResponse; }
    public void setLastResponse(Response lastResponse) { this.lastResponse = lastResponse; }

    public String getBoardId() { return boardId; }
    public void setBoardId(String boardId) { this.boardId = boardId; }

    public String getListId() { return listId; }
    public void setListId(String listId) { this.listId = listId; }

    public String getCardId() { return cardId; }
    public void setCardId(String cardId) { this.cardId = cardId; }
}
