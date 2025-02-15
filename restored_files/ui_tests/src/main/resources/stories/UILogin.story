Meta:
@component login


Scenario: User as <role> does not able to sign in with incorrect credentials
Given I as <role> try sign in with incorrect credentials
Then I should see Login page
And I should see invalid credentials error

Examples:
|role|
|ADMIN|


Scenario: User as <role> is able to sign in and sign out with correct credentials
Given I logged in as <role>
When I sign out
Then I should see Login page

Examples:
|role|
|ADMIN|
