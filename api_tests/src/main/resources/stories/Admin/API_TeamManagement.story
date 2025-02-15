Meta:
@API
@component Admin
@story teamManagement


Lifecycle:
Before:
Given I sign in as user with permissions UM_ADMIN
Then Request is successful


Scenario: Getting a list of all teams
When I send get list of teams
Then Request is successful
And Teams list size more than 0


Scenario: API.Creation of new team
When I send create a new team
Then Request is successful
And Team is correct


Scenario: User is able/unable to delete a team
When I send create a new team
Then Request is successful
And Team is correct

When I send create a new nested team
Then Request is successful

When I send delete parent team request
Then I got response code 400
And Message contains "AUTH_NON_EMPTY_TEAM"
And Message contains "Team containing users/teams cannot be deleted. Please first remove users/teams from this team and try again."
And Message contains "teams" : 1

Given I sign in as ADMIN user
When I send delete user request
Then Request is successful

When I send delete team request
Then Request is successful
When I send get list of teams
Then Request is successful
And Team is not in the list

When I send delete team request
Then Request is successful


Scenario: User is able to update a team
When I send create a new team
Then Request is successful

When I send update team request
Then Request is successful
And Team is correct


Scenario: API.Getting a team by id
When I send create a new team
Then Request is successful

When I send get team details request
Then Request is successful
And Team is correct


Scenario: User is able to search/filter teams
Meta: @skip
When I send create a new team
Then Request is successful

When I send search users and teams by <criteria> with <value> request
Then Request is successful
And Users and Teams list size more than 0
And Users and Teams search result are correct

When I send create a new user
Then Request is successful

When I send search users and teams by <criteria> with <value> request
Then Request is successful
And Users and Teams list size more than 0
And Users and Teams search result are correct

Examples:
| criteria      | value  |
| dataSources   | random |
| name          | random |
| orgTypes      | random |
| titles        | random |
| clearances    | random |
| orgids        | random |
| parentorgid   | random |


Scenario: User is able to create nested team
Meta:
@issue CB-22814
When I send create a new team
Then Request is successful

When I send create a new nested team
Then Request is successful
And Team is correct

When I send search users and teams by parentOrgId with random request
Then Request is successful
And Users and Teams search result are correct
And Users and Teams list contains created user or team


Scenario: User is able to move a team
When I send create a new team
Then Request is successful

When I send create a new team
Then Request is successful

When I send create a new nested team
Then Request is successful

When I send move team to other parent team
Then Request is successful
And Team is correct


Scenario: API.Cleanup Teams
When I send get list of teams
Then Request is successful
When I delete all qe empty teams