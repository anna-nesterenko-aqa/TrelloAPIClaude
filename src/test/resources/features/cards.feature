Feature: Trello Card Management

  Background:
    Given a board named "Card Test Board" exists
    And the board has a list named "To Do"

  Scenario: Create a card on a list
    When I create a card with name "New Task" on the list
    Then the response status code should be 200
    And the card name should be "New Task"

  Scenario: Update a card name
    Given a card named "Original Task" exists on the list
    When I update the card name to "Updated Task"
    Then the response status code should be 200
    And the card name should be "Updated Task"

  Scenario: Delete a card
    Given a card named "Task To Delete" exists on the list
    When I delete the card
    Then the response status code should be 200
