Meta:
@story profileDraft
@deprecated

Lifecycle:
Before:
Given I sign in as admin user
When I send create profile draft request
Then Request is successful
Then Profile draft is correct


Scenario: Addition of new profile draft
When I send get profile draft details request
Then Request is successful
And Profile draft is correct

When I send delete profile draft request
Then Request is successful


Scenario: Deleting of profile draft
When I send get profile draft details request
Then Request is successful

When I send delete profile draft request
Then Request is successful


Scenario: Displaying of profile draft details
When I send get profile draft details request
Then Request is successful
And Profile draft is correct

When I send delete profile draft request
Then Request is successful


Scenario: Updating of profile draft
When I send create finder file request
Then Request is successful
When I add profile draft to finder file

When I send update profile request
Then Request is successful
And Profile draft is correct

When I send get profile draft details request
Then Request is successful
And Profile draft is correct

When I send delete profile draft request
Then Request is successful
When I send delete finder file request
Then Request is successful


Scenario: Get list of all profile drafts
When I send get list of profile drafts request
Then Request is successful
And Profile drafts list size > 0

When I send delete profile draft request
Then Request is successful

Scenario: Remove all draft
Meta: @skip
When delete all profile drafts
