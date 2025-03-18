Feature: Logout Functionality
  @logsout
  Scenario: Logout Successfully
    Given you in the hompage
    When Enter the credentials "username" and "password" and login
    And I log in and redirect to the home page
    When I logout
    Then The user is redirected to the login page
@logsout
  Scenario: Verify Session Expiry After Logout
    Given I am on the homepage
    When I enter "username" and "password" and click login
    Then I should be logged in and redirected to the homepage
    When I click on the logout button
    And I press the back button
    Then I should not be able to access the previous page