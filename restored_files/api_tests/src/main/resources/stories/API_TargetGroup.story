Meta:
@story targetGroup

Lifecycle:
Before:
Given I sign in as admin user
When I send create target group request
Then Request is successful
After:
When I send delete target group request
Then Request is successful


Scenario: Create and delete of new target group
Then Created target group is correct


Scenario: API.Displaying of top level target groups list
When I send get list of target groups request
Then Request is successful
Then Created target group in list


Scenario: Addition of new child target group
When I send create new child target group request
Then Request is successful

When I send get target group details request
Then Request is successful
Then created nested target group is correct

When I send get content of parent target group
Then Request is successful
Then Target group content contains created nested group

When I send delete target group request
Then Request is successful


Scenario: API.Get contents of target group
When I send create profile draft request
Then Request is successful
And Profile draft is correct
When I add profile draft to target group
When I send publish profile draft request
Then Request is successful

When I send create new child target group request
Then Request is successful
When I send get target group details request
Then Request is successful
Then created nested target group is correct

When I send get content of parent target group
Then Request is successful
Then Target group content contains created nested group
And Target group content contains created profile

When I send delete profile request
Then Request is successful
When I send delete target group request
Then Request is successful
