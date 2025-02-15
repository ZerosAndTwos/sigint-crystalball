Meta:
@story recordCategory
@deprecated

Scenario: API.Create record category
Given I sign in as admin user
When I send create new random record category request
Then Request is successful
When I send get list of record categories request
Then Request is successful
And Record categories list size more than 0
And Record category is in list


Scenario: API.Get list of record categories
Given I sign in as admin user
When I send get list of record categories request
Then Request is successful
And Record categories list size more than 0


Scenario: API.View record category details
Given I sign in as admin user
When I send get list of record categories request
Then Request is successful
And Record categories list size more than 0
When I get random record category from list
And I send view record category request
Then Request is successful
And Record category is correct


Scenario: API.Update record category
Given I sign in as admin user
When I send get list of record categories request
Then Request is successful
And Record categories list size more than 0
When I get random record category from list
And I send update record category request
Then Request is successful
When I send view record category request
Then Record category is correct
