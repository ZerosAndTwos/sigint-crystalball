Meta:

Scenario: Delete Target
Given I navigate to Search
Given I reset search on the Search page
When I enter search criteria (<TargetName>) on the Search page
When I start search on the Search page
Given I setup Search Authorization
Given I delete all Profiler entity cards on the Search page having identifiers: |<TargetName>|<TargetCategory>|