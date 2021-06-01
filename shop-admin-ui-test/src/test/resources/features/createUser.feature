Feature: createUser

  Scenario Outline: Create new user


    Given I open web browser
    When I navigate to login page
    And I provide username as "<username>" and password as "<password>"
    And I click on login button

    When I navigate to admin page
    When I click on users
    And I click on Create button
    And I create new user as "<user>" with password as "<password>"

    When Open dropdown menu
    And click logout button
    Then user logged out

    Examples:
      | username | password | user |
      | admin | admin | test_user |