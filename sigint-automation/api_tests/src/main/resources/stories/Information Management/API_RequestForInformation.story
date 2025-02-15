Meta:
@API
@component Information Management
@story rfi
@stage

Lifecycle:
Before:
Given I sign in as admin user
When I send create finder file request
Then Request is successful

Scenario: Create/Delete a RFI
When I send generate RFI number request
Then Request is successful

When I get allowed RFI actions
Then Request is successful

When I send Save as Draft a <type> RFI request
Then Request is successful
And RFI is correct

When I send view a RFI request
Then Request is successful

When I get allowed RFI actions
Then Request is successful

When I send Delete a <type> RFI request
Then Request is successful

Examples:
|type|
|EXTERNAL|
|INTERNAL|

Scenario: Create/Delete a RFI
When I send generate RFI number request
Then Request is successful

When I get allowed RFI actions
Then Request is successful

When I send Save as Draft a <type> RFI request
Then Request is successful
And RFI is correct

When I send view a RFI request
Then Request is successful

When I get allowed RFI actions
Then Request is successful

When I send Delete a <type> RFI request
Then Request is successful

Examples:
|type|
|EXTERNAL|
|INTERNAL|


Scenario: Submit for Approval a RFI
When I send generate RFI number request
Then Request is successful

When I get allowed RFI actions
Then Request is successful

When I send Save as Draft a <type> RFI request
Then Request is successful
And RFI is correct

When I send view a RFI request
Then Request is successful

When I get allowed RFI actions
Then Request is successful

When I send get owner a RFI in Submit for Approval request
Then Request is successful

When I send Submit for Approval a <type> RFI request
Then Request is successful
Then RFI is submitted

Examples:
|type|
|EXTERNAL|
|INTERNAL|

Scenario: Approve a RFI via submit button
When I send generate RFI number request
Then Request is successful

When I get allowed RFI actions
Then Request is successful

When I send Save as Draft a <type> RFI request
Then Request is successful
And RFI is correct

When I send view a RFI request
Then Request is successful

When I get allowed RFI actions
Then Request is successful

When I send get owner a RFI in Submit for Approval request
Then Request is successful

When I send Submit for Approval a <type> RFI request
Then Request is successful
Then RFI is submitted

When I get allowed RFI actions
Then Request is successful

When I send get owner a RFI in Approve request
Then Request is successful

When I send Approve a <type> RFI request
Then Request is successful
Then RFI is approved

Examples:
|type|
|EXTERNAL|
|INTERNAL|

Scenario: Cancel a RFI
When I send generate RFI number request
Then Request is successful

When I get allowed RFI actions
Then Request is successful

When I send Save as Draft a <type> RFI request
Then Request is successful
And RFI is correct

When I send view a RFI request
Then Request is successful

When I get allowed RFI actions
Then Request is successful

When I send get owner a RFI in Submit for Approval request
Then Request is successful

When I send Submit for Approval a <type> RFI request
Then Request is successful
Then RFI is submitted

When I get allowed RFI actions
Then Request is successful

When I send Cancel a <type> RFI request
Then Request is successful
Then RFI is cancelled

Examples:
|type|
|EXTERNAL|
|INTERNAL|

Scenario: Send a RFI
When I send generate RFI number request
Then Request is successful

When I get allowed RFI actions
Then Request is successful

When I send Save as Draft a <type> RFI request
Then Request is successful

When I get allowed RFI actions
Then Request is successful

When I send Send a <type> RFI request
Then Request is successful
Then RFI is sent

When I send view a RFI request
Then Request is successful

Examples:
|type|
|EXTERNAL|
|INTERNAL|

Scenario: Submit for Approval a RFI
When I send generate RFI number request
Then Request is successful

When I get allowed RFI actions
Then Request is successful

When I send Save as Draft a <type> RFI request
Then Request is successful
And RFI is correct

When I get allowed RFI actions
Then Request is successful

When I send get owner a RFI in Submit for Approval request
Then Request is successful

When I send Submit for Approval a <type> RFI request
Then Request is successful
Then RFI is submitted

Examples:
|type|
|EXTERNAL|
|INTERNAL|

Scenario: Take ownership a RFI via send button
When I send generate RFI number request
Then Request is successful

When I get allowed RFI actions
Then Request is successful

When I send Save as Draft a <type> RFI request
Then Request is successful

When I get allowed RFI actions
Then Request is successful

When I send Send a <type> RFI request
Then Request is successful
Then RFI is sent

When I get allowed RFI actions
Then Request is successful

When I send get owner a RFI in Take Ownership request
Then Request is successful

When I send Take Ownership a <type> RFI request
Then Request is successful
Then RFI is ownershipped

Examples:
|type|
|EXTERNAL|
|INTERNAL|

Scenario: Approve a RFI
When I send generate RFI number request
Then Request is successful

When I get allowed RFI actions
Then Request is successful

When I send Save as Draft a <type> RFI request
Then Request is successful
And RFI is correct

When I get allowed RFI actions
Then Request is successful

When I send get owner a RFI in Submit for Approval request
Then Request is successful

When I send Submit for Approval a <type> RFI request
Then Request is successful
Then RFI is submitted

When I get allowed RFI actions
Then Request is successful

When I send get owner a RFI in Approve request
Then Request is successful

When I send Approve a <type> RFI request
Then Request is successful
Then RFI is Awaiting Assignment

Examples:
|type|
|EXTERNAL|
|INTERNAL|

Scenario: Unassign a RFI
Meta:@skip
When I send generate RFI number request
Then Request is successful

When I get allowed RFI actions
Then Request is successful

When I send Save as Draft a <type> RFI request
Then Request is successful

When I get allowed RFI actions
Then Request is successful

When I send Send a <type> RFI request
Then Request is successful
Then RFI is sent

When I get allowed RFI actions
Then Request is successful

When I send get owner a RFI in Take Ownership request
Then Request is successful

When I send Take Ownership a <type> RFI request
Then Request is successful
Then RFI is ownershipped

When I get allowed RFI actions
Then Request is successful

When I send get owner a RFI in Unassign request
Then Request is successful

When I send Unassign a <type> RFI request
Then Request is successful
Then RFI is unassigned

Examples:
|type|
|EXTERNAL|
|INTERNAL|

Scenario: Edit a RFI
Meta:@skip
When I send generate RFI number request
Then Request is successful

When I get allowed RFI actions
Then Request is successful

When I send Save as Draft a <type> RFI request
Then Request is successful

When I get allowed RFI actions
Then Request is successful

When I send Save a <type> RFI request
Then Request is successful
And RFI is correct

Examples:
|type|
|EXTERNAL|

