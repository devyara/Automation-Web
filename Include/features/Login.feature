#Author: devy anugrah
Feature: Login

  @Login
  Scenario Outline: User successfully logged in
    Given User is on the login page
    When User input username <username> and password <password>
    Then User successfully logged in

    Examples: 
      | username      | password     |
      | standard_user | secret_sauce |
