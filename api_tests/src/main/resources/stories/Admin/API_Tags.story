Meta:
@API
@component Admin
@story tag


Lifecycle:
Before:
Given I sign in as user with all permissions


Scenario: API.Create, Delete, Get List of tags
When I send create new tag request
Then Request is successful

When I send get list of tags request
Then Request is successful
And Tags list size more than 0

When I get created tag from list of tags

When I send delete tag request
Then Request is successful


Scenario: API.Search of tags by filters
When I send create new tag request
Then Request is successful

When I send search tags by <criteria> with <value> request
Then Request is successful
And Tags list size more than 0
And tags search result are correct

When I get created tag from list of tags

When I send delete tag request
Then Request is successful

Examples:
| criteria      | value  |
| name          | random |
| empty         |        |