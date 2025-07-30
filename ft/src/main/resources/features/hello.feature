Feature: greeting

  Scenario: Greeting should return Hello, Spring!
    When I call the hello endpoint
    Then the response should be "Hello, Spring!"
