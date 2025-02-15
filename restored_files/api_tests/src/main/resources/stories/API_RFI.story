Meta:
@story RFI
@deprecated

Scenario: API.Tasker can create new RFI with all fields
Given I sign in as tasker user
When I send create RFI request with approved copy and with original document
Then I got response code 200

Scenario: API.Tasker can update existing RFI
Given I sign in as tasker user
When I send create RFI request without approved copy and without original document
Then I got response code 200
When I update created RFI
Then I got response code 200
And RFI is updated


Scenario: API.Tasker can find RFI using search filters
Given I sign in as tasker user
When I send create RFI request without approved copy and without original document
And I put RFI to search query
Then I got response code 200
When I search RFI by <criteria> and value <value>
Then Search results are correct
And Searched RFI in list

Examples:
|criteria| value |
|state| random |
|min priority| random |
|created date| random |
|due date|  random
|created by|  random |


Scenario: API.Tasker can get details of RFI
Given I sign in as tasker user
When I send create RFI request without approved copy and without original document
Then I got response code 200
When I get details of created RFI
Then RFI details get via details are correct


Scenario: API.Tasker can delete RFI in status Draft
Given I sign in as tasker user
When I create new RFI in status draft
And I put RFI to search query
And I delete created RFI
Then I got response code 200
When I search RFI by state and value DRAFT
Then Search results are correct
And Searched RFI not in list

Scenario: API.Admin can cancel sended RFI
Given I sign in as tasker user
When I send create RFI request without approved copy and without original document
Then I got response code 200
Given I sign in as admin user
When I put RFI to search query
And I cancel RFI
Then I got response code 200
When I search RFI by state and value CANCELLED
Then Searched RFI in list

Scenario: API.Analyst can take ownership of RFI
Given I sign in as tasker user
When I send create RFI request without approved copy and without original document
Then I got response code 200
Given I sign in as analyst user
When I take ownership of RFI
Then RFI has status Assigned and assigned to current user
