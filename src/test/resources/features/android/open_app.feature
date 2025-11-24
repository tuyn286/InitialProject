Feature: Open the mobile app


  Scenario: Verify that the app launches successfully
    Given the app is launched
    And grant permission for app
    And accept old version alert
    Then the app should display the main screen

  Scenario Outline: Verify that the app can add new contact
    Given the app is launched
    And open add new contact form
    When type "<username>" and "<password>"
    Then contact list should have new contact
    And contact list should have specific contact
    | username1 | password1 |
  Examples:
    | username | password |
    | tuyen    | 1234     |
    | tuyen12  | 12343    |