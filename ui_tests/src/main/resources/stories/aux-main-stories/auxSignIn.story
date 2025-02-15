Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: scenario description
Given I open Sign In page
Then I should see Sign In page
Given I enter login <login>
Given I enter password <password>
Then I should see enabled Sign In button
When I push Sign In button
Then I should see Confirm or Logout dialog
Given I push Accept button in the Confirm or Logout dialog

Examples:
|login           |password|
|admin@pegasus.ae|123456  |