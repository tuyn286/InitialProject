Feature: Open home page
  Scenario: As a customer, I want to open comprehensive home page
    Given user has home page url
    When user navigate to home page url
    Then home page has a logo