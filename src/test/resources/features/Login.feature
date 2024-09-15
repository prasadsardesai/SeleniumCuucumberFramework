Feature: Verify Login Functionality for Software TestingBoard

	
	@Test
  Scenario: Verify user login to Software TestingBoard application with Valid Credentials
    Given user is on the Software TestingBoard login page
    When user enters username as "prasadsardesai@gmail.com"
    And user enters password as "Meghmalhar@123"
    And user clicks on the login button
    Then the page title of homepage should be "My Account"
    
   @Test
  Scenario: Verify user login to Software TestingBoard application with invalid credentials
    Given user is on the Software TestingBoard login page
    When user enters username as "prasadsardesai@gmail.com"
    And user enters password as "abcd@123"
    And user clicks on the login button
    Then verify it shows appropriate warning message "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later."