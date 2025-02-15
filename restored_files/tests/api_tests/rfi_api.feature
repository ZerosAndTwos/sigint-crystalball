
Feature: Users can create, update, delete RFI records based on permissions

  @stable
  Scenario: Operator user can create new RFI request
    When I signed in as "tasker" user
    And  I create new RFI with default values
    Then I expect response code "200"
    And  Rfi record is created

  @stable
  Scenario: Operator user can update RFI with files
    When I signed in as "tasker" user
    And  I create new RFI with default values
    Then I expect response code "200"
    And  Rfi record is created
    When I update record with files
    Then Record has attached files


  @stable
  Scenario: Operator can find RFI searching by Max Request Date Filter
    When I signed in as "tasker" user
    And  I create new RFI with specific values
         | createdDate |
         | 2015-01-24  |
    Then I expect response code "200"
    And  Rfi record is created
    When I search for RFI with query
         |min_created_date    | max_created_date
         |2015-01-24T00:00:00 | 2015-01-24T23:59:59
    Then Search result is correct



  @stable
  Scenario: Operator can find RFI searching by status
    When I signed in as "tasker" user
    And  I create new RFI with specific values
         | state       |
         | ASSIGNED    |
    Then I expect response code "200"
    And  Rfi record is created
    When I search for RFI with query
         |state           |
         |ASSIGNED        |
    Then Search result is correct

  @stable
  Scenario: Operator can find RFI searching by Due Date filter
    When I signed in as "tasker" user
    And  I create new RFI with specific values
         | createdDate   | priority |
         | 2015-01-24    | 2        |
    Then I expect response code "200"
    And  Rfi record is created
    When I search for RFI with query
         |min_due_date           |  max_due_date        |
         | 2015-01-25T00:00:00   |  2015-01-25T23:59:59 |
    Then Search result is correct



  @stable
  Scenario: Operator can find RFI searching by status
    When I signed in as "tasker" user
    And  I create new RFI with specific values
         | requestSource  |
         | FBI_test       |
    Then I expect response code "200"
    And  Rfi record is created
    When I search for RFI with query
         |request_source  |
         |FBI_test        |
    Then Search result is correct

  @stable
  Scenario: Operator can find RFI searching by priority
    When I signed in as "tasker" user
    And  I create new RFI with specific values
         | priority  |
         | 1         |
    Then I expect response code "200"
    And  Rfi record is created
    When I search for RFI with query
         |min_priority  |
         |1             |
    Then Search result is correct

  @stable
  Scenario: Operator user can delete Draft request
    When I signed in as "tasker" user
    And  I create new RFI with specific values
         | state   |
         | DRAFT   |
    Then I expect response code "200"
    And  Rfi record is created
    When I delete rfi
    Then I expect response code "200"
    And RFI record is deleted

  @stable
  Scenario: Operator can cancel request
    When I signed in as "tasker" user
    And I create new RFI with default values
    Then I expect response code "200"
    And  Rfi record is created
    When I cancel RFI
    Then I expect response code "200"
    Then RFI has status "Cancelled"


  @stable
  Scenario: Operator user can open details page of RFI
    When I signed in as "tasker" user
    And I create new RFI with default values
    Then I expect response code "200"
    And  Rfi record is created
    When I get details of rfi
    Then I expect response code "200"
    And Details of rfi are correct


  @stable
  Scenario: Analyst user can assign RFI to himself
    When I signed in as "tasker" user
    And I create new RFI with default values
    Then I expect response code "200"
    And  Rfi record is created
    When I signed in as "analyst" user
    And I take ownership of rfi
    Then I expect response code "200"
    And RFI has status "assigned"
    And RFI assigned to analyst


  @stable
  Scenario: Operator, Analyst, Report Admin, Admin cannot create RFI
    When I signed in as "user" user
    And I create new RFI with default values
    Then I expect response code "403"
    When I signed in as "admin" user
    And I create new RFI with default values
    Then I expect response code "403"
    When I signed in as "analyst" user
    And I create new RFI with default values
    Then I expect response code "403"
    When I signed in as "approver" user
    And I create new RFI with default values
    Then I expect response code "403"


  @bug
  Scenario: Operator, Analyst, Report Admin, Admin cannot cancel RFI
    When I signed in as "tasker" user
    And I create new RFI with default values
    Then I expect response code "200"
    And  Rfi record is created
    When I signed in as "user" user
    And  I cancel RFI
    Then I expect response code "403"
    When I signed in as "admin" user
    And  I cancel RFI
    Then I expect response code "403"
    When I signed in as "approver" user
    And  I cancel RFI
    Then I expect response code "403"
    When I signed in as "analyst" user
    And  I cancel RFI
    Then I expect response code "403"


  @stable
  Scenario: Operator, Analyst, Report admin cannot delete RFI
    When I signed in as "tasker" user
    And  I create new RFI with specific values
         | state   |
         | DRAFT   |
    Then I expect response code "200"
    And  Rfi record is created
    When I signed in as "user" user
    And I delete rfi
    Then I expect response code "403"
    When I signed in as "admin" user
    Then I expect response code "403"
    When I signed in as "approver" user
    Then I expect response code "403"
    When I signed in as "analyst" user
    Then I expect response code "403"


  @bug
  Scenario: Operator user cannot delete non-draft request
    When I signed in as "tasker" user
    And I create new RFI with default values
    Then I expect response code "200"
    And  Rfi record is created
    When I delete rfi
    Then I expect response code "403"


  @stable
  Scenario: Only Analyst user can assign RFI to himself
    When I signed in as "tasker" user
    And I create new RFI with default values
    Then I expect response code "200"
    And  Rfi record is created
    When I signed in as "tasker" user
    And I take ownership of rfi
    Then I expect response code "403"
    When I signed in as "user" user
    And I take ownership of rfi
    Then I expect response code "403"
    When I signed in as "admin" user
    And I take ownership of rfi
    Then I expect response code "403"
    When I signed in as "approver" user
    And I take ownership of rfi
    Then I expect response code "403"



  @bug
  Scenario: Analyst user can assign RFI only from status Pending
    When I signed in as "tasker" user
    And  I create new RFI with specific values
         | state   |
         | DRAFT   |
    Then I expect response code "200"
    And  Rfi record is created
    When I signed in as "analyst" user
    And I take ownership of rfi
    Then I expect response code "403"
    

