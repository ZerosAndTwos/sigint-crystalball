Meta:
@UI
@component Search
@component smoke-test
@stage
@search


Lifecycle:
Before:
Scope: SCENARIO
Given load story ../aux-main-stories/auxSignIn.story

Scenario: User can see search results in different represenation
Given I navigate to Search
Then I should see Search page
When I enter search criteria (*) on the Search page
Given I setup Search Authorization

When I open Card View
Given load story ../aux-search-stories/auxCheckSearchResultsNumbers.story
Given load story ../aux-search-stories/auxCheckSearchResulsVerticalScroll.story


When I open Grid View
Given load story ../aux-search-stories/auxCheckSearchResultsNumbers.story
Given load story ../aux-search-stories/auxCheckSearchResulsVerticalScroll.story
Given load story ../aux-search-stories/auxCheckSearchResulsHorizontalScroll.story

When I open Map View
Given load story ../aux-search-stories/auxCheckSearchResultsNumbers.story


Examples:
|SIGINT_events|EID_events|GOVINT_events|FININT_events|TRAFFIC_events|OSINT_events|CIO_events|Profiler_entities|Documents_entities|SIGINT_entities|EID_entities|GOVINT_entities|OSINT_entities|CIO_entities|
|1000         |1         |400          |1|1|1000        |0          |1               |1                 |100            |100         |100            |100           |0       |
