Feature: Fund Transfer Functionality

  Background:
    Given User is on the login page
    When User enters "username" as username and "password" as password
    And User clicks on the login button
    Then User should be redirected to the account dashboard
@fund
  Scenario: Successful Fund Transfer
    Given User navigates to the Fund Transfer page
    When User selects "Savings(Avail. balance = $ 1000)" from From Account dropdown
    And User selects "Credit Card(Avail. balance = $ -265)" from To Account dropdown
    And User enters "100" in Amount field
    And User clicks on Continue button
    Then User should be redirected to the Transfer Verification page
    When User clicks on Submit button
    Then User should see a success message "You successfully submitted your transaction."    
    
@fund
  Scenario: Fund Transfer with Empty Amount Field
    Given User navigates to the Fund Transfer page
    When User selects "Savings(Avail. balance = $ 1000)" from From Account dropdown
    And User selects "Credit Card(Avail. balance = $ -265)" from To Account dropdown
    And User leaves the Amount field empty
    And User clicks on Continue button
    Then User should see an error message "Amount field cannot be empty"
