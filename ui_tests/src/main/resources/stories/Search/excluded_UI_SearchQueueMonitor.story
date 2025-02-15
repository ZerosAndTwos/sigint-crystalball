Meta:
@UI
@component Search
@stage
@queueMonitor
Lifecycle:
Before:
Scope: SCENARIO
Given load story ../aux-main-stories/auxSignIn.story

Scenario: Search queue monitor
Given I navigate to Search
When I enter search criteria (<searchCriteria1>) on the Search page
Given I setup Search Authorization
Then I should see at least <SIGINTEvents1> of SIGINT events on current view
Given I open Queues page
Then I should see search queue 1: Query (<searchQuery1>), Type (<searchType>), Status (<searchStatus1>), Created At (<searchCreatedAt>) with standard actions set
Given I close Queues page
When I enter search criteria (<searchCriteria2>) on the Search page
Then I should see at least <SIGINTEvents2> of SIGINT events on current view
Given I open Queues page
Then I should see search queue 1: Query (<searchQuery2>), Type (<searchType>), Status (<searchStatus2>), Created At (<searchCreatedAt>) with standard actions set
Then I should see search queue 2: Query (<searchQuery1>), Type (<searchType>), Status (<searchStatus1>), Created At (<searchCreatedAt>) with standard actions set
When I perform Rerun action for the search queue 2
Given I wait until progress bar for the search queue 1 disappear
When I click to the Query cell of the search queue 1
Then I should see search criteria (<searchCriteria1>) on the Search page
Then I should see at least <SIGINTEvents1> of SIGINT events on current view




Scenario: Search queue monitor delete
Given I open Queues page
Then I should see search queue 1: Query (<searchQuery1>), Type (<searchType>), Status (<searchStatus1>), Created At (<searchCreatedAt>) with standard actions set
When I perform Remove action for the search queue 1
Then I should see search queue 1: Query (<searchQuery2>), Type (<searchType>), Status (<searchStatus2>), Created At (<searchCreatedAt>) with standard actions set


Examples:
|searchCriteria1|SIGINTEvents1|searchQuery1                         |searchCriteria2|SIGINTEvents2|searchQuery2                         |searchQueryCommon      |searchType|searchStatus1           |searchStatus2           |searchCreatedAt                             |
|*              |100000       |<searchCriteria1> <searchQueryCommon>|Call           |1500         |<searchCriteria2> <searchQueryCommon>|AND includeSpam:"false"|Basic     |Completed (10M+ results)|Completed (10k+ results)|{current_day}/{current_month}/{current_year}|

Scenario: Failed query
Given I navigate to Search
When I enter search criteria (<searchCriteria1>) on the Search page
Given I setup Search Authorization
Given load story ../aux-search-stories/auxCheckSearchResultsAbsent.story
Given I open Queues page
Then I should see search queue 1: Query (<searchQuery1>), Type (<searchType>), Status (<searchStatus1>), Created At (<searchCreatedAt>) with standard actions set

Given I close Queues page

When I enter search criteria (<searchCriteria2>) on the Search page
Then I should see at least <SIGINTEvents> of SIGINT events on current view
Given I open Queues page
Then I should see search queue 1: Query (<searchQuery2>), Type (<searchType>), Status (<searchStatus2>), Created At (<searchCreatedAt>) with standard actions set
Then I should see search queue 2: Query (<searchQuery1>), Type (<searchType>), Status (<searchStatus1>), Created At (<searchCreatedAt>) with standard actions set

When I perform Rerun action for the search queue 2
Given I open Queues page
Given I wait until progress bar for the search queue 1 disappear
Then I should see search queue 1: Query (<searchQuery1>), Type (<searchType>), Status (<searchStatus1>), Created At (<searchCreatedAt>) with standard actions set
When I click to the Query cell of the search queue 1
Given I close Queues page
Then I should see at least <SIGINTEvents> of SIGINT events on current view

Given I open Queues page
When I perform Remove action for the search queue 1
Then I should see search queue 1: Query (<searchQuery2>), Type (<searchType>), Status (<searchStatus2>), Created At (<searchCreatedAt>) with standard actions set

Examples:
|searchCriteria1|searchCriteria2|searchQuery1                         |searchQuery2                          |searchQueryCommon      |searchType|searchStatus1|searchCreatedAt                             |SIGINTEvents|searchStatus2           |
|&&             |Call           |<searchCriteria1> <searchQueryCommon>| <searchCriteria2> <searchQueryCommon>|AND includeSpam:"false"|Basic     |Failed       |{current_day}/{current_month}/{current_year}|150         |Completed (10k+ results)|
