Feature: Checks if user can login
  Scenario: Admin can login in system
    When I login as "admin"
    Then User is logged in
