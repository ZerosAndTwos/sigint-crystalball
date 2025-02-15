Meta:
@UI
@component Search
@stage
@search


Lifecycle:
Before:
Scope: SCENARIO
Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_analyst.table
After:
Scope: SCENARIO
Outcome: ANY
Given I Sign Out





Scenario: Basic Search - Search authorisation input is working
Meta: @L0  @test C83435
Given I navigate to Search
Then I should see Search page
When I enter search criteria (*) on the Search page
When I start search on the Search page
Then I should see Cancel and Continue buttons on Search Authorization page
Given I setup Search Authorization
When I open Card View




Scenario: Basic Search - User can search by Entity&Event
Meta: @L0  @test C83455
Given I navigate to Search
Then I should see Search page
When I enter search criteria (*) on the Search page
Given I open Search Filter on the Search page
Given I set Object Type to Entity on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
When I open Card View
Given load story ../aux-search-stories/entities/auxCheckSearchResultsEntitiesNumbers.story
Given I open Search Filter on the Search page
Given I set Object Type to Event on the Search Filter page
Given load story ../aux-search-filter-stories/auxSearchFilterShowAllEvents.story
Given I Apply Search using Search Filter on the Search page

When I open Card View
Given load story ../aux-search-stories/events/auxCheckSearchResultsEventsNumbers.story


Examples:
|SIGINT_events|EID_events|GOVINT_events|OSINT_events|CIO_events|FININT_events|TRAFFIC_events|Profiler_entities|Documents_entities|SIGINT_entities|EID_entities|GOVINT_entities|OSINT_entities|CIO_entities|
|1         |1         |1          |1        |1         |1            |1             |1                |1                 |1            |1         |1            |1           |1       |




Scenario: Basic Search - User can search by keyword
Meta: @L0  @test  C83456

Given I navigate to Search
When I enter search criteria (063769296482132) on the Search page
Given I open Search Filter on the Search page
Given I set Source Type to SIGINT on the Search Filter page


Given load story ../aux-search-filter-stories/auxSearchFilterShowAllEvents.story
Given I set Data Source to (|Manual|) on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
When I open Card View
Then I should see 1 search result(s) on the current view


Scenario: Basic Search - Detailed card view
Meta: @L0  @test  C83463

Given I navigate to Search
When I enter search criteria (063769296482132) on the Search page
Given I open Search Filter on the Search page
Given I set Source Type to SIGINT on the Search Filter page


Given load story ../aux-search-filter-stories/auxSearchFilterShowAllEvents.story
Given I set Data Source to (|Manual|) on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
When I open Card View
Then I should see 1 search result(s) on the current view


When I open details of 1-st displayed card
Then I should not see From Phone Number field and value
Then I should not see To Phone Number field and value
Given I close search result Details page


Scenario: Basic Search - Scroll pagination
Meta: @L0  @test C83458
Given I navigate to Search
Then I should see Search page
When I enter search criteria (*) on the Search page
When I start search on the Search page
Given I setup Search Authorization
When I open Card View
Then I should be able to scroll SIGINT events vertically on current view

Scenario: Basic Search - Card, map and grid view shows data
Meta: @L0  @test C83459
Given I navigate to Search
Then I should see Search page
When I enter search criteria (*) on the Search page
Given I open Search Filter on the Search page
Given I set Object Type to Entity on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization

When I open Card View
Given load story ../aux-search-stories/entities/auxCheckSearchResultsEntitiesNumbers.story

When I open Grid View
Given load story ../aux-search-stories/entities/auxCheckSearchResultsEntitiesNumbers.story

When I open Map View
Given load story ../aux-search-stories/entities/auxCheckSearchResultsEntitiesNumbers.story


Given I open Search Filter on the Search page
Given I set Object Type to Event on the Search Filter page
Given load story ../aux-search-filter-stories/auxSearchFilterShowAllEvents.story
Given I Apply Search using Search Filter on the Search page

When I open Card View
Given load story ../aux-search-stories/events/auxCheckSearchResultsEventsNumbers.story

When I open Grid View
Given load story ../aux-search-stories/events/auxCheckSearchResultsEventsNumbers.story

When I open Map View
Given load story ../aux-search-stories/events/auxCheckSearchResultsEventsNumbers.story

When I open Card View

Examples:
|SIGINT_events|EID_events|GOVINT_events|FININT_events|TRAFFIC_events|OSINT_events|CIO_events|Profiler_entities|Documents_entities|SIGINT_entities|EID_entities|GOVINT_entities|OSINT_entities|CIO_entities|
|1         |1         |1          |1|1|1        |0          |1               |1                 |1            |1         |1            |1           |0       |




Scenario: Basic Search - User can export record
Meta: @L0  @test  C83457

Given I navigate to Search
When I enter search criteria (063769296482132) on the Search page
Given I open Search Filter on the Search page
Given I set Source Type to SIGINT on the Search Filter page


Given load story ../aux-search-filter-stories/auxSearchFilterShowAllEvents.story
Given I set Data Source to (|Manual|) on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
When I open Card View
Then I should see 1 search result(s) on the current view
Given I select all cards on the Search page
When I export selected items




Scenario: All the data sources are up and returns Event/Entity in card view
Meta:@devsmoke
Given I navigate to Search
Then I should see Search page
When I enter search criteria (*) on the Search page
When I start search on the Search page
Then I should see Cancel and Continue buttons on Search Authorization page
Given I setup Search Authorization
When I open Card View

Given I open Search Filter on the Search page
Given I set Object Type to Event on the Search Filter page
Given load story ../aux-search-filter-stories/auxSearchFilterShowAllEvents.story
Given I Apply Search using Search Filter on the Search page

When I open Card View
Given load story ../aux-search-stories/events/auxCheckSearchResultsEventsNumbers.story


Examples:
|SIGINT_events|EID_events|GOVINT_events|OSINT_events|CIO_events|FININT_events|TRAFFIC_events|Profiler_entities|Documents_entities|SIGINT_entities|EID_entities|GOVINT_entities|OSINT_entities|CIO_entities|
|1         |1         |1          |1        |1         |1            |1             |1                |1                 |1            |1         |1            |1           |1       |




Scenario: Verfiy  data source filters are working
Given I navigate to Search
Given I open Search Filter on the Search page
Given I set Source Type to SIGINT on the Search Filter page
Given I set period range Last 90 days as Event Time Period on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view


Given I open Search Filter on the Search page
Given I set Source Type to EID on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view

Given I open Search Filter on the Search page
Given I set Source Type to GOVINT on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view


Given I open Search Filter on the Search page
Given I set Source Type to FININT on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view

Given I open Search Filter on the Search page
Given I set Source Type to Traffic on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view

Given I open Search Filter on the Search page
Given I set Source Type to OSINT on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view





