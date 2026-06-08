package org.example.steps;

import io.cucumber.java.After;
import org.example.api.BoardApi;
import org.example.context.ScenarioContext;

public class Hooks {

    private final ScenarioContext context;
    private final BoardApi boardApi = new BoardApi();

    public Hooks(ScenarioContext context) {
        this.context = context;
    }

    @After
    public void cleanup() {
        if (context.getBoardId() != null) {
            boardApi.deleteBoard(context.getBoardId());
            context.setBoardId(null);
        }
    }
}
