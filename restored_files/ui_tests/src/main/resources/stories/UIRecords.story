Meta:
@modules UI
@phase sanity
@component records


Scenario: User is able to create a manual record (SMS TEXT)
Given I logged in as OPERATOR
When I navigate to Records -> Search page
And I open create record form
And I fill the form for SMS record
When I navigate to Records -> Search page
And I filter records by current date
Then I should see new record on the table


Scenario: User is able to create a manual record (CALL)
Given I logged in as OPERATOR
When I navigate to Records -> Search page
And I open create record form
And I fill the form for Voice record
When I navigate to Records -> Search page
And I filter records by current date
Then I should see new record on the table


Scenario: User can create report from record processing page and save it as Draft
Given I logged in as OPERATOR
When I navigate to Records -> Search page
And I create new SMS record
And I navigate to Records -> Processed page
And I filter records by current date
When I checked checkbox on record with non-reported status
And I press 'Create Report' button
And Fill out required fields on 'Create Report' page based from record
And I press 'Save As Draft' button
When I navigate to Reports -> Draft page
Then I should see new report on page
When I navigate to Reports -> All page
Then report status is 'DRAFT'
When I navigate to Records -> Processed page
And I filter records by current date
Then Record status is REPORTED

Scenario: User is able to attach record to existing report
Given I logged in as OPERATOR
When I navigate to Reports -> All page
And I create new manual draft report
When I navigate to Records -> Search page
And I create new SMS record
And I navigate to Records -> Processed page
And I filter records by current date
When I checked checkbox on record with non-reported status
And I press 'Create report' button against it
And I attach record to draft report from Details dialog
And I press 'Save As Draft' button
When I navigate to Reports -> All page
Then record is attached to the report


Scenario: User can create report from record processing screen and send it to Analyst for review
Given I logged in as OPERATOR
When I navigate to Reports -> All page
And I create new manual draft report
When I navigate to Records -> Search page
And I create new SMS record
And I navigate to Records -> Processed page
And I filter records by current date
When I checked checkbox on record with non-reported status
And I press 'Create Report' button
And Fill out required fields on 'Create Report' page based from record
And I press 'Submit' button
And I sign out
Given I logged in as ANALYST
When I navigate to Reports -> Ready page
Then I should see new report on page
When I navigate to Reports -> All page
Then report status is 'UNASSIGNED'
When I navigate to Records -> Search page
And I filter records by current date
Then Record status is REPORTED


