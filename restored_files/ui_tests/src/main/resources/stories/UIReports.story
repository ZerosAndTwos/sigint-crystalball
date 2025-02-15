Meta:
@modules UI
@phase sanity
@component reports


Scenario: User is able to create a manual report and save it as Draft
Given I logged in as Operator
When I navigate to Reports -> All page
And I press 'Create Manual Report' button
And I fill out required fields on 'Create Manual Report' page
And I press 'Save As Draft' button
When I navigate to Reports -> All page
Then I should see report on page
And report status is 'DRAFT'


Scenario: User is able to remove ownership from itΓÇÖs own draft report
Given I logged in as Operator
When I navigate to Reports -> All page
And I create new manual draft report
When I navigate to Reports -> Draft page
And I select any report in the table
And I press 'Edit Report' button against it
When I press 'Remove Ownership' button on 'Edit Report' page
When I navigate to Reports -> Draft page
Then I should not see report on page
When I navigate to Reports -> Ready page
Then I should see report on page
When I navigate to Reports -> All page
Then report status is 'UNASSIGNED'
And report owner should be empty


Scenario: User is able to take ownership on unassigned draft report
Given I logged in as Operator
When I navigate to Reports -> All page
And I create new manual draft report
When I navigate to Reports -> Draft page
And I select any report in the table
And I press 'Edit Report' button against it
When I press 'Remove Ownership' button on 'Edit Report' page
When I navigate to Reports -> All page
When I select a report in Unassigned status and owner is Empty
And I press 'Edit Report' button against it
And I press 'Take Ownership' button on 'Edit Report' page
Then Edit report form is enabled
