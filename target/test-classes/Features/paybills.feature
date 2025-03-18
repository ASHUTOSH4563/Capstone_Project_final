Feature: Pay Bills Functionality

  Background:
    Given the user navigates to the login page
    When the user provides "username" as the username and "password" as the password
    And clicks the login button
    Then the user should be directed to the account dashboard

  Scenario: Successful Bill Payment
    Given User navigates to Pay Bills via More Services
    When User selects payee "Sprint"
    And User selects account "Savings"
    And User enters amount "100"
    And User enters date
    And User clicks Pay button
    Then User sees a success message "The payment was successfully submitted."

  Scenario: Pay Bills Without Entering Amount
    Given User navigates to Pay Bills via More Services
    When User selects payee "Sprint"
    And User selects account "Savings"
    And User leaves Amount field empty
    And User enters date
    And User clicks Pay button
    Then User would see an error message "Amount field cannot be empty."

  Scenario: Schedule a Bill Payment for a Future Date
    Given User navigates to Pay Bills via More Services
    When User selects payee "Sprint"
    And User selects account "Savings"
    And User enters amount "200"
    And User enters date
    And User clicks Pay button
    Then The scheduled payment should be visible in the transactions list