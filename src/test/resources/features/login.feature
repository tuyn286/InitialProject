Feature: Login functionality
  Scenario: User can login with valid credentials
    Given user open open login page by url "https://aristino.com/account/login"
    And user type username and password
    | username | password |
    | testuser | 123456   |
    When user click submit button
    Then account page is displayed