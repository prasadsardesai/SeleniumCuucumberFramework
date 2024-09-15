Feature: Verify My Account Information page


@Test
  Scenario Outline: Verify My Account Information from My Account Page
    Given user is on the Software TestingBoard login page
    When user enters username as "prasadsardesai@gmail.com"
    And user enters password as "Meghmalhar@123"
    And user clicks on the login button
    Then the page title of homepage should be "My Account"
    And user verify username as "<userName>" and emailID as "<emailId>" under Contact Information

    Examples: 
      | userName        | emailId                  |
      | prasad sardesai | prasadsardesai@gmail.com |
