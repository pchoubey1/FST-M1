@activity4
Feature: Data driven test without Examples
@loginTest @loginSuccess
Scenario: Testing with correct data and input
     Given User is on Login page
    When User enters "admin" and "password"
    And clicks the submit button
    Then get the confirmation text  and verify message as "Welcome Back,admin"