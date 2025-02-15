Meta:
@story targetManagement


Scenario: API.Add new target group with target in body
Meta:
@deprecated
Given I sign in as admin user
When I send create target without targets group request
Then Request is successful
When I send create target group with targets request
Then Request is successful
And Created target group is correct

Scenario: API.Add new target with target groups
Meta:
@deprecated
Given I sign in as admin user
When I send create target group without targets request
Then Request is successful
When I send create target with targets group request
Then Request is successful
And Created target is correct

Scenario: API.View target details
Meta:
@deprecated
Given I sign in as admin user
When I send create target without targets group request
Then Request is successful
When I send get target details request
Then Viewed target is correct

Scenario: API.Update target entry
Meta:
@deprecated
Given I sign in as admin user
When I send create target without targets group request
Then Request is successful
When I send update target request
Then Request is successful
And Target updated correctly

Scenario: API.Deleting Target - DELETE
Meta:
@deprecated
Given I sign in as admin user
When I send create target without targets group request
Then Request is successful
When I send delete target request
Then Request is successful
And Target deleted correctly

Scenario: API.View target group details
Meta:
@deprecated
Given I sign in as admin user
When I send create target group without targets request
Then Request is successful
When I send get target group details request
Then Viewed target group is correct

Scenario: API.Update target group
Meta:
@deprecated
Given I sign in as admin user
When I send create target group without targets request
Then Request is successful
When I send update target group request
Then Request is successful
And Target group updated correctly

Scenario: API.Delete target group
Meta:
@deprecated
Given I sign in as admin user
When I send create target group without targets request
Then Request is successful
When I send delete target group request
Then Request is successful

Scenario: API.Upload updated target
Meta:
@deprecated
Given I sign in as admin user
When I send upload targets XLS file request with <targetCount> random targets
Then Request is successful
And Upload result of 1 targets is successful
When I send search targets by name and value random
Then targets search result are correct
And searched target in search result list
When I send upload updated target request
Then Request is successful
And Upload result of 1 targets is successful
When I send search targets by name and value random
Then targets search result are correct
And searched target in search result list


Scenario: API.Upload new target with existing group
Meta:
@deprecated
Given I sign in as admin user
When I send get list of target group request
Then Request is successful
When I send upload targets request with XLS file containing 1 targets with existing group request
Then Request is successful
And Upload result of 1 targets is successful
When I send search targets by name and value random
Then targets search result are correct
And searched target in search result list
When I send get list of target group request
Then existing group is listed in list only once
When I send get groups list of new target request
Then target group in list

Scenario: API.Upload new target with new group
Meta:
@deprecated
Given I sign in as admin user
When I send upload new 1 targets with new group request
Then Request is successful
And Upload result of 1 targets is successful
When I send search targets by name and value random
Then targets search result are correct
And searched target in search result list
When I send get list of target group request
Then target group in list
When I send get groups list of new target request
Then target group in list

