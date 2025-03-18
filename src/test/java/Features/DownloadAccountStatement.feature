Feature: Downloading Account Statements Functionality

    
    Background:
    Given User opens the Zero Bank home page
    When User navigates to the Signin page
    And User enters valid username "username" and password "password"
    And User clicks on the sign-in button
    Then User should be logged in successfully
    @download
    Scenario: Successful download of an account statement 
    		
    		When User clicks the Online Banking
    		And User clicks on the Online Statements
    		Then Online Statements page should be displayed
    		When User selects account type
    		And User selects year 
    		When User clicks on a statement for download 
    		Then The file statement.pdf should be downloaded successfully
