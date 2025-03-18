
Feature: Account Summary Module Functionality
  Background:
    Given the user is on the login page
    When the user enters "username" in the username field and "password" in the password field
    And submits the login form
    Then the user should be taken to the account dashboard
  @summary
  Scenario: Account Summary Page Loads Correctly
    When User clicks on the Online Banking
    And User clicks on the Account Summary
    Then User should see all account types and balances displayed
 @summary
  Scenario: Validate Account Types
    When User clicks on the Online Banking
    And User navigates to the Account Summary page   
    Then User should see the following account types displayed:
      | Cash Accounts       |
      | Investment Accounts |
      | Credit Accounts     |
      | Loan Accounts       |