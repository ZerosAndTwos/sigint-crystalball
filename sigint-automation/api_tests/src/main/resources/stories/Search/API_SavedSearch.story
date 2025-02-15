Meta:
@API
@component Saved Search
@story savedSearch
@stage

Lifecycle:
Before:
Given I sign in as admin user

Scenario: Sort savedSearch by name
When I send get savedSearches request: page:<page>, pageSize:<pageSize>, sortKey:<sortKey>, sortOrder:<sortOrder>
Then Request is successful
And SavedSearch result list size > 0
And Sort by <sortKey> order <sortOrder> is valid
Examples:
| page | pageSize | sortKey | sortOrder |
| 0    | 100      | name    | ASC |
| 0    | 100      | name    | DESC|

Scenario: Sort savedSearch by createdAt
When I send get savedSearches request: page:<page>, pageSize:<pageSize>, sortKey:<sortKey>, sortOrder:<sortOrder>
Then Request is successful
And SavedSearch result list size > 0
And Sort by <sortKey> order <sortOrder> is valid
Examples:
| page | pageSize | sortKey | sortOrder |
| 0    | 100      | createdAt | ASC |
| 0    | 100      | createdAt | DESC|

Scenario: Sort savedSearch by assignmentPriority
When I send get savedSearches request: page:<page>, pageSize:<pageSize>, sortKey:<sortKey>, sortOrder:<sortOrder>
Then Request is successful
And SavedSearch result list size > 0
And Sort by <sortKey> order <sortOrder> is valid
Examples:
| page | pageSize | sortKey | sortOrder |
| 0    | 100      | assignmentPriority | ASC |
| 0    | 100      | assignmentPriority | DESC|

Scenario: Sort savedSearch by expirationDate
When I send get savedSearches request: page:<page>, pageSize:<pageSize>, sortKey:<sortKey>, sortOrder:<sortOrder>
Then Request is successful
And SavedSearch result list size > 0
And Sort by <sortKey> order <sortOrder> is valid
Examples:
| page | pageSize | sortKey | sortOrder |
| 0    | 100      | expirationDate | ASC |
| 0    | 100      | expirationDate | DESC|