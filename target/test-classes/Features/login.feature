Feature: Login to Zero Bank

Background:
    Given User opens the Zero Bank home page
    When User navigates to the Signin page
@logsin
Scenario: Successful login from Zero Bank home page
    And User enters valid username "username"
    And User enters valid password "password"
    And User clicks on the sign-in button
    Then User should be logged in successfully
@logsin
Scenario: Unsuccessful login from Zero Bank home page with invalid credentials
    And User enters invalid username "invalid_username"
    And User enters invalid password "invalid_password"
    And User clicks on the sign-in button1
    Then User should see an error message
    And User should remain on the login page
@logsin
Scenario: Unsuccessful login from Zero Bank home page with blank credentials
    And User leaves the username field blank
    And User leaves the password field blank
    And User clicks on the sign-in button2
    Then User should see an error message
    And User should remain on the login page
