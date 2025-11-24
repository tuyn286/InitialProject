Feature: Login functionality
  @now
  Scenario: Verify user can login with valid credentials
    Given user navigate to login page
    When user type "tuyn@gmail.com" and "1111111"
    And user submit login form
    Then redirect to account page