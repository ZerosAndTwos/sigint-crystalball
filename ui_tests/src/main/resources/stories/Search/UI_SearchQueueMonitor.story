Meta:
@UI
@component Search
@queueMonitor

Lifecycle:
Before:
Scope: SCENARIO
Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login.table
After:
Scope: SCENARIO
Outcome: ANY
Given I Sign Out

Scenario: Single Search query is reflected in the search queue
Given I navigate to Search
When I enter search criteria (<searchCriteria1>) on the Search page
Given I open Search Filter on the Search page
Given I set Object Type to Entity on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least <SIGINTEntity1> of SIGINT events on current view
Given I open Queues page
Then I should see search queue 1: Query (<searchQuery1>), Type (<searchType>), Status (<searchStatus1>), Created At (<searchCreatedAt>) with standard actions set
Given I close Queues page

Examples:
data/SearchQueue.table

Scenario: Multiple Search query is reflected and can be reruned in the search queue
Given I navigate to Search
When I enter search criteria (<searchCriteria1>) on the Search page
Given I open Search Filter on the Search page
Given I set Object Type to Entity on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least <SIGINTEntity1> of SIGINT entities on current view
When I enter search criteria (<searchCriteria2>) on the Search page
When I start search on the Search page
Then I should see at least <SIGINTEntity2> of SIGINT entities on current view
Given I open Queues page
Then I should see search queue 1: Query (<searchQuery2>), Type (<searchType>), Status (<searchStatus2>), Created At (<searchCreatedAt>) with standard actions set
Then I should see search queue 2: Query (<searchQuery1>), Type (<searchType>), Status (<searchStatus1>), Created At (<searchCreatedAt>) with standard actions set

When I perform Rerun action for the search queue 2
Given I wait until progress bar for the search queue 1 disappear
When I click to the Query cell of the search queue 1
Then I should see search criteria (<searchCriteria1>) on the Search page
Then I should see at least <SIGINTEntity1> of SIGINT entities on current view
Given I open Queues page
Then I should see search queue 1: Query (<searchQuery1>), Type (<searchType>), Status (<searchStatus1>), Created At (<searchCreatedAt>) with standard actions set

Examples:
data/SearchQueue.table

Scenario: User can deleta a search from search queue
Given I navigate to Search
When I enter search criteria (<searchCriteria1>) on the Search page
Given I open Search Filter on the Search page
Given I set Object Type to Entity on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least <SIGINTEntity1> of SIGINT entities on current view
When I enter search criteria (<searchCriteria2>) on the Search page
When I start search on the Search page
Then I should see at least <SIGINTEntity2> of SIGINT entities on current view
Given I open Queues page
Then I should see search queue 1: Query (<searchQuery2>), Type (<searchType>), Status (<searchStatus2>), Created At (<searchCreatedAt>) with standard actions set
Then I should see search queue 2: Query (<searchQuery1>), Type (<searchType>), Status (<searchStatus1>), Created At (<searchCreatedAt>) with standard actions set


When I perform Remove action for the search queue 1

Then I should see search queue 1: Query (<searchQuery1>), Type (<searchType>), Status (<searchStatus1>), Created At (<searchCreatedAt>) with standard actions set

Examples:
data/SearchQueue.table

Scenario: Failed query is reflected in the search queue
Given I navigate to Search
When I enter search criteria (<searchCriteriaFail>) on the Search page
Given I open Search Filter on the Search page
Given I set Object Type to Entity on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization for failed query
Given I open Queues page
Then I should see search queue 1: Query (<searchQueryFail>), Type (<searchType>), Status (<searchStatusFail>), Created At (<searchCreatedAt>) with standard actions set

Examples:
data/SearchQueue.table

Scenario: Failed query can be reruned in the search queue
Given I navigate to Search
When I enter search criteria (<searchCriteriaFail>) on the Search page
Given I open Search Filter on the Search page
Given I set Object Type to Entity on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization for failed query
Given I open Queues page
Then I should see search queue 1: Query (<searchQueryFail>), Type (<searchType>), Status (<searchStatusFail>), Created At (<searchCreatedAt>) with standard actions set
Given I close Queues page

When I enter search criteria (<searchCriteria2>) on the Search page
When I start search on the Search page
Then I should see at least <SIGINTEntity2> of SIGINT entities on current view
Given I open Queues page
Then I should see search queue 1: Query (<searchQuery2>), Type (<searchType>), Status (<searchStatus2>), Created At (<searchCreatedAt>) with standard actions set
Then I should see search queue 2: Query (<searchQueryFail>), Type (<searchType>), Status (<searchStatusFail>), Created At (<searchCreatedAt>) with standard actions set

When I perform Rerun action for the search queue 2
Given I open Queues page
Given I wait until progress bar for the search queue 1 disappear
Then I should see search queue 1: Query (<searchQueryFail>), Type (<searchType>), Status (<searchStatusFail>), Created At (<searchCreatedAt>) with standard actions set
When I click to the Query cell of the search queue 1
Given I close Queues page
Then I should see at least <SIGINTEntity2> of SIGINT entities on current view

Examples:
data/SearchQueue.table
Scenario: User can delete a failed query from the search queue
Given I navigate to Search
When I enter search criteria (<searchCriteriaFail>) on the Search page
Given I open Search Filter on the Search page
Given I set Object Type to Entity on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization for failed query
Given I open Queues page
Then I should see search queue 1: Query (<searchQueryFail>), Type (<searchType>), Status (<searchStatusFail>), Created At (<searchCreatedAt>) with standard actions set
Given I close Queues page

When I enter search criteria (<searchCriteria2>) on the Search page
When I start search on the Search page
Then I should see at least <SIGINTEntity2> of SIGINT entities on current view
Given I open Queues page
Then I should see search queue 1: Query (<searchQuery2>), Type (<searchType>), Status (<searchStatus2>), Created At (<searchCreatedAt>) with standard actions set
Then I should see search queue 2: Query (<searchQueryFail>), Type (<searchType>), Status (<searchStatusFail>), Created At (<searchCreatedAt>) with standard actions set

Given I open Queues page
When I perform Remove action for the search queue 2
Then I should see search queue 1: Query (<searchQuery2>), Type (<searchType>), Status (<searchStatus2>), Created At (<searchCreatedAt>) with standard actions set


Examples:
data/SearchQueue.table