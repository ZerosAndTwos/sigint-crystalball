Meta:

Scenario: Common search filter's settings for manual event
Given I set Source Type to SIGINT on the Search Filter page
Given I set Object Type to Event on the Search Filter page
Given I set date range (<RecordDate>) - (<RecordDate>) as Event Time Period on the Search Filter page
Given I set Data Source to (|Manual|) on the Search Filter page
