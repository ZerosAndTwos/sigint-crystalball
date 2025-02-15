Feature: Users can manage records based on permissions and workflow rules

  @stable
  Scenario: Admin user can create new manual record
    When I signed in as "admin" user
    And I create new manual record with default values
    Then I expect response code "200"
    And Record is created
