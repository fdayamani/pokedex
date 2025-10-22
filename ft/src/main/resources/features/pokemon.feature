Feature: Get basic Pokemon details

  Scenario: Get Pikachu details
    When I call the pokemon endpoint with "pikachu"
    Then the response should have:
      | name    | type     |
      | pikachu | electric |
