Meta:
@API
@component Admin
@story source

Scenario: API.Create, Read, Update, Delete source
Given I sign in as admin user

When I send create new random Source request
Then Request is successful
And Source is correct

When I send view source request
Then Request is successful
And Source is correct

When I send update source request
Then Request is successful
When I send view source request
Then Source is correct

When I send delete Source request
Then Request is successful
When I send get list of sources request
Then Request is successful
Then Source not in list


Scenario: API.Get list of sources
Given I sign in as admin user
When I send get list of sources request
Then Request is successful
And Source list size more than 0