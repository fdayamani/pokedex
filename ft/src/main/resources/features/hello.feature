Feature: greeting

  Scenario: Greeting should return Hello, Spring!
    When I call the hello endpoint with "no name"
    Then the response should be "Hello, Spring!"

  Scenario: Greeting with name
    When I call the hello endpoint with "John"
    Then the response should be "Hello, John!"
