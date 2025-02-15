Test user password strength validation.
Parent story: CB-2407

Meta:
@API
@component Auth
@skip
@story userPassword

Lifecycle:
Before:
Given I sign in as user with permissions UM_ADMIN
When I send create a new user
Then Request is successful
After:
Given I sign in as user with permissions UM_ADMIN
When I send delete user request
Then Request is successful

Scenario: API.Check short password

When I change user password to 123456
Then I got response code 400
And Message contains "MIN_LENGTH"

Scenario: API.Check long weak password
When I change user password to LONGPASSWORDHERE
Then I got response code 400
And Message contains "CHARACTERS"

Scenario: API.Check dictionary password
When I change user password to P@ssWord1
Then I got response code 400
And Message contains "STRENGTH"

Scenario: API.Check password with more than two successive identical characters
When I change user password to My-very-G00d-paswooord
Then I got response code 400
And Message contains "REPEAT_CHARACTERS"

Scenario: API.Check random password
!-- upper and lower case letters, and digits
When I change user password to random
Then I got response code 200

!-- upper and lower case letters
When I change user password to adoKSLAQxo
Then I got response code 200

!-- lower case letters and digits
When I change user password to 9h21b33wx0
Then I got response code 200

!-- upper case letters and digits
When I change user password to K9JJ782DXO
Then I got response code 200

!-- lower case letters and special characters
When I change user password to adoksl@q!@
Then I got response code 200

!-- upper case letters and special characters
When I change user password to HDK@HD!D&O
Then I got response code 200

!-- digits and special characters
When I change user password to 324@!421#$
Then I got response code 200

Scenario: API.Check password with username
When I change user password to username
Then I got response code 400
And Message contains "USERNAME_MATCH"

Scenario: API.Check password with emoji symbols
When I change user password to Em0j1🚀🚃🚑🚕
Then I got response code 200

Scenario: API.Check that password is different from the last 3 passwords used
When I change user password to This-happened-once-before
Then Request is successful
When I change user password to When-I-came-to-your-door
Then Request is successful
When I change user password to They-said-it-wasn't-you
Then Request is successful
When I change user password to But-I-saw-you-peep-through
Then Request is successful

When I change user password to When-I-came-to-your-door
Then I got response code 400
And Message contains "HISTORY_MATCH"
When I change user password to They-said-it-wasn't-you
Then I got response code 400
And Message contains "HISTORY_MATCH"
When I change user password to But-I-saw-you-peep-through
Then I got response code 400
And Message contains "HISTORY_MATCH"

When I change user password to This-happened-once-before
Then Request is successful

Scenario: API.Check last logon datetime
When I send sign out request
Then Request is successful
When I change temporary password
Then Request is successful
When I sign in as new created user
Then User last logon datetime is correct

When I send sign out request
And I wait for 10 seconds
And I sign in as new created user
Then User last logon datetime is correct

Scenario: API.Check password change after the first login attempt
When I send sign out request
And I sign in as new created user
Then I got response code 401
And Message contains "AUTH_CHANGE_PASSWORD_REQUIRED"
When I change temporary password
Then Request is successful
When I sign in as new created user
Then Request is successful
When I change temporary password
Then I got response code 403
And Message contains "AUTH_FORBIDDEN"

Scenario: API.Check that user will be locked after 5 sequential failed login attempts
When I send sign out request
And I change temporary password
Then Request is successful
When I set wrong user password
And I send sign out request
Then Request is successful

When I sign in as new created user
Then I got response code 400
When I sign in as new created user
Then I got response code 400
When I sign in as new created user
Then I got response code 400
When I sign in as new created user
Then I got response code 400
When I sign in as new created user
Then I got response code 400

When I sign in as new created user
Then I got response code 403
And Message contains "AUTH_ACCOUNT_IS_LOCKED"

Scenario: API.Check that user will be locked after 5 failed password changes
When I set wrong user password
And I send sign out request
Then Request is successful

When I change temporary password
Then I got response code 400
When I change temporary password
Then I got response code 400
When I change temporary password
Then I got response code 400
When I change temporary password
Then I got response code 400
When I change temporary password
Then I got response code 400

When I change temporary password
Then I got response code 403
And Message contains "AUTH_ACCOUNT_IS_LOCKED"

Scenario: Change password for currently logged in user
When I send sign out request
Then Request is successful
When I change temporary password
Then Request is successful

When I sign in as new created user
Then Request is successful
When I change user password to random
Then I got response code 200

When I send sign out request
Then Request is successful
When I sign in as new created user
Then Request is successful