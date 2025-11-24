Feature: Policy management
  Scenario Outline: Verify user can search policy by exactly input
    Given user open app successfully
    When user perform search functionality with input "<search>"
    Then search result must contain "<search>"
    And search result have 1 result
  Examples:
    | search                |
    | Set screen brightness |