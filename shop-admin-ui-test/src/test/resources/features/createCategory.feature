Feature: createCategory

  Scenario Outline: Create new category


    Given I open web browser
    When I navigate to login page
    And I provide username as "<username>" and password as "<password>"
    And I click on login button
    When I navigate to admin page
    When I click on categories
    And I click on Create button
    And I create new category as "<category>"
    When Open dropdown menu
    And click logout button
    Then user logged out

    Examples:
      | username | password | category |
      | admin | admin | test_object |