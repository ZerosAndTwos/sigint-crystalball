Meta:
@API
@component Admin
@story designations

Lifecycle:
Before:
Given I sign in as admin user
When I send create new random designation request
Then Request is successful
And Designation is correct
After:
Given I sign in as admin user
When I send delete designation request
Then Request is successful

Scenario: API.Create designation
When I send get list of designations request
Then Request is successful
Then Designations list size is more than 0
Then Designation is in list

Scenario: API.Get designation by id
When I send view designation request
Then Request is successful
And Designation is correct

Scenario: API.Update designation
When I send update designation request
Then Request is successful
And Designation is correct

Scenario: API.Delete designation
When I send view designation request
Then Request is successful

Scenario: API.Get designations list
When I send get list of designations request
Then Request is successful
And Designations list size is more than 0

Scenario: API.Search designations by filters
When I send search designations by <criteria> with <value> request
Then Request is successful
And Designations list size is more than 0
And Designations search result is correct

Examples:
| criteria      | value  |
| name          | random |
| updatedAfter  | random |
| empty         |        |