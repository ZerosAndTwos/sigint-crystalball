Meta:
@API
@component Workflow
@story records
@deprecated


Scenario: User is able to create a manual record (SMS TEXT)
Given I sign in as admin user
When I send create manual record - SMS
Then Request is successful
And Created record is correct

Scenario: User is able to create a manual record (CALL)
Given I sign in as admin user
When I send create manual record - Voice
Then Request is successful
And Created record is correct