Feature: Trello Board Management

  Scenario: Create a new board
    When I create a board with name "Automation Test Board"
    Then the response status code should be 200
    And the board name should be "Automation Test Board"

  Scenario: Get a board by ID
    Given a board named "Readable Board" exists
    When I get the board by id
    Then the response status code should be 200
    And the board name should be "Readable Board"

  Scenario: Delete a board
    Given a board named "Board To Delete" exists
    When I delete the board
    Then the response status code should be 200
