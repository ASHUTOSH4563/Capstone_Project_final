Feature: New Payee

  Background: You have to Login
    Given you are in the hompage
    When click login
    And Enter the "username" and "password" and login
    Then I get logged in and redirect to the home page
@newpayee
  Scenario: New Payee all details
    Given I am in the Homepage
    When I click on online banking
    And I click on pay bills
    And I click on new payee
    And fill out the new payee details
    And click Add
    Then I make a new payee
@newpayee
  Scenario: New Payee incomplete details
    Given Navigate to homepage
    When clicked on the online banking
    And click on pay bills
    And click on new payee
    And fill incomplete details
    And click add button
    Then confirm new payee not added
