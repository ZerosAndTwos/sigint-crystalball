Meta:
@API
@component Information Management
@story reportCategory


Scenario: API.Create, Update, Delete report category
Given I sign in as admin user
When I create new report category
Then Request is successful
And Report category is correct

When I send update report category request
Then Request is successful
And Report category is correct

When I send delete report category request
Then Request is successful
And Report category is marked as deleted

Scenario: API.Get list of report categories
Given I sign in as admin user
When I send get list of report categories request
Then Request is successful
And Report categories list size more than 0