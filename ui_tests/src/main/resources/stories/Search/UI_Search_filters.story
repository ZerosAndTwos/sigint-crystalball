Meta:
@UI
@component Search
@stage
@search
@skip


Lifecycle:
Before:
Scope: SCENARIO
Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_analyst.table
After:
Scope: SCENARIO
Outcome: ANY
Given I Sign Out




Scenario: All the data sources are working in card view
Meta:@devsmoke11
Given I navigate to Search
Then I should see Search page
When I enter search criteria (*) on the Search page
When I start search on the Search page
Given I setup Search Authorization

When I open Card View
Given load story ../aux-search-stories/entities/auxCheckSearchResultsEntitiesNumbers.story

Given I open Search Filter on the Search page
Given load story ../aux-search-filter-stories/auxSearchFilterShowAllEvents.story
Given I Apply Search using Search Filter on the Search page

When I open Card View
Given load story ../aux-search-stories/events/auxCheckSearchResultsEventsNumbers.story


Examples:
|SIGINT_events|EID_events|GOVINT_events|OSINT_events|CIO_events|FININT_events|TRAFFIC_events|Profiler_entities|Documents_entities|SIGINT_entities|EID_entities|GOVINT_entities|OSINT_entities|CIO_entities|
|1         |1         |1          |1        |1         |1            |1             |1                |1                 |1            |1         |1            |1           |1       |



Scenario: Verfiy  data source filters are working
Meta:@devsmokexx
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





Scenario: Verfiy all Record Type for Documnets
Given I navigate to Search
Given I open Search Filter on the Search page
Given I set Source Type to Documents on the Search Filter page
Given I set Record Type to (|<RecordType>|) on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view

Examples:
|RecordType|
|Operator Report|
|Request For Approval|


Scenario: Verfiy all data sources for SIGINT
Given I navigate to Search
Given I open Search Filter on the Search page
Given I set date (01/01/2016) as Earliest Event Time on the Search Filter page

Given I set Source Type to SIGINT on the Search Filter page
Given I set Data Source to (|<DataSource>|) on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view

Examples:
|DataSource|
|du|
|Etisalat|
|F|
|J1|
|J2|
|Manual|
|O|
|Phonebook|
|S|
|T|


Scenario: Verfiy all data and Record Type for SIGINT
Meta:@fix1
Given I navigate to Search
Given I open Search Filter on the Search page
Given I set date (01/01/2016) as Earliest Event Time on the Search Filter page

Given I set Source Type to SIGINT on the Search Filter page
Given I set Data Source to (|<DataSource>|) on the Search Filter page
Given I set Record Type to (|<RecordType>|) on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view

Examples:
|DataSource||RecordType|
|du||Call|
|du||Domestic Movement|
|du||International Roaming|
|du||Subscriber|
|du||Texting event|
|Etisalat||Call|
|Etisalat||Domestic Movement|
|Etisalat||International Roaming|
|Etisalat||Subscriber|
|Etisalat||Texting event|
|J2||Call|
|J2||Texting|
|Manual||Call|
|Manual||E-mail|
|Manual||Fax|
|Manual||Texting event|
|O||Call|
|O||E-mail|
|O||International Roaming|
|O||Fax|
|O||Texting event|
|S||Call|
|S||E-mail|
|S||International Roaming|
|S||Fax|
|S||Texting event|
|S||VOIP|
|T||Call|
|T||Texting|


Scenario: Verfiy all data sources for SIGINT Event
Given I navigate to Search
Given I open Search Filter on the Search page
Given load story ../aux-search-filter-stories/auxSearchFilterShowAllEvents.story
Given I set Source Type to SIGINT on the Search Filter page
Given I set Data Source to (|<DataSource>|) on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view

Examples:
|DataSource|
|du|
|Etisalat|
|F|
|J1|
|J2|
|Manual|
|O|
|Phonebook|
|S|
|T|



Scenario: Verfiy all data sources for SIGINT Entity
Given I navigate to Search
Given I open Search Filter on the Search page
Given I set Source Type to SIGINT on the Search Filter page
Given I set Object Type to Entity on the Search Filter page
Given I set Data Source to (|<DataSource>|) on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view

Examples:
|DataSource|
|du|
|Etisalat|
|F|
|J1|
|J2|
|Manual|
|O|
|Phonebook|
|S|
|T|


Scenario: Verfiy all data sources for GOVINT Event
Given I navigate to Search
Given I open Search Filter on the Search page
Given load story ../aux-search-filter-stories/auxSearchFilterShowAllEvents.story
Given I set Source Type to GOVINT on the Search Filter page
Given I set Data Source to (|<DataSource>|) on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view

Examples:
|DataSource|
|SITA|
|UDB|

Scenario: Verfiy all data sources for GOVINT Entity
Given I navigate to Search
Given I open Search Filter on the Search page
Given I set Source Type to GOVINT on the Search Filter page
Given I set Object Type to Entity on the Search Filter page
Given I set Data Source to (|<DataSource>|) on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view

Examples:
|DataSource|
|SITA|
|UDB|


Scenario: Verfiy all data sources for GOVINT
Given I navigate to Search
Given I open Search Filter on the Search page
Given I set date (01/01/2016) as Earliest Event Time on the Search Filter page
Given I set Source Type to GOVINT on the Search Filter page
Given I set Data Source to (|<DataSource>|) on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view

Examples:
|DataSource|
|SITA|
|UDB|









Scenario: Verfiy all data sources for EID Entity
Given I navigate to Search
Given I open Search Filter on the Search page
Given I set Source Type to EID on the Search Filter page
Given I set Object Type to Entity on the Search Filter page
Given I set Data Source to (|<DataSource>|) on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view

Examples:
|DataSource|
|Passport|
|Person|
|Visa|


Scenario: Verfiy all data sources for EID
Given I navigate to Search
Given I open Search Filter on the Search page
Given I set date (01/01/2016) as Earliest Event Time on the Search Filter page
Given I set Source Type to GOVINT on the Search Filter page
Given I set Data Source to (|<DataSource>|) on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view

Examples:
|DataSource|
|Passport|
|Person|
|Visa|
|EID Investigation|

Scenario: All the data sources are working

Given I navigate to Search
Then I should see Search page
When I enter search criteria (*) on the Search page
When I start search on the Search page
Given I setup Search Authorization

When I open Card View
Given load story ../aux-search-stories/entities/auxCheckSearchResultsEntitiesNumbers.story

When I open Grid View
Given load story ../aux-search-stories/entities/auxCheckSearchResultsEntitiesNumbers.story

When I open Map View
Given load story ../aux-search-stories/entities/auxCheckSearchResultsEntitiesNumbers.story

Given I open Search Filter on the Search page
Given load story ../aux-search-filter-stories/auxSearchFilterShowAllEvents.story
Given I Apply Search using Search Filter on the Search page

When I open Card View
Given load story ../aux-search-stories/events/auxCheckSearchResultsEventsNumbers.story

When I open Grid View
Given load story ../aux-search-stories/events/auxCheckSearchResultsEventsNumbers.story

When I open Map View
Given load story ../aux-search-stories/events/auxCheckSearchResultsEventsNumbers.story


Examples:
|SIGINT_events|EID_events|GOVINT_events|OSINT_events|CIO_events|FININT_events|TRAFFIC_events|Profiler_entities|Documents_entities|SIGINT_entities|EID_entities|GOVINT_entities|OSINT_entities|CIO_entities|
|1000         |1         |400          |1000        |0         |0            |0             |1                |1                 |100            |100         |100            |100           |0       |

Scenario: User is able to scroll in card view
Given I navigate to Search
Then I should see Search page
When I enter search criteria (*) on the Search page
When I start search on the Search page
Given I setup Search Authorization

When I open Card View

Given load story ../aux-search-stories/entities/auxCheckSearchResulsEntitiesVerticalScroll.story

Given I open Search Filter on the Search page
Given load story ../aux-search-filter-stories/auxSearchFilterShowAllEvents.story
Given I Apply Search using Search Filter on the Search page

Given load story ../aux-search-stories/events/auxCheckSearchResulsEventsVerticalScroll.story


Examples:
|SIGINT_events|EID_events|GOVINT_events|OSINT_events|CIO_events|Profiler_entities|Documents_entities|SIGINT_entities|EID_entities|GOVINT_entities|OSINT_entities|CIO_entities|
|1000         |1         |400          |1000        |0          |1               |1                 |100            |100         |100            |100           |0       |


Scenario: User is able to scroll in Grid view
Given I navigate to Search
Then I should see Search page
When I enter search criteria (*) on the Search page
When I start search on the Search page
Given I setup Search Authorization

When I open Grid View

Given load story ../aux-search-stories/entities/auxCheckSearchResulsEntitiesVerticalScroll.story
Given load story ../aux-search-stories/entities/auxCheckSearchResulsEntitiesHorizontalScroll.story

Given I open Search Filter on the Search page
Given load story ../aux-search-filter-stories/auxSearchFilterShowAllEvents.story
Given I Apply Search using Search Filter on the Search page

Given load story ../aux-search-stories/events/auxCheckSearchResulsEventsVerticalScroll.story
Given load story ../aux-search-stories/events/auxCheckSearchResulsEventsHorizontalScroll.story

Examples:
|SIGINT_events|EID_events|GOVINT_events|OSINT_events|CIO_events|Profiler_entities|Documents_entities|SIGINT_entities|EID_entities|GOVINT_entities|OSINT_entities|CIO_entities|
|1            |1         |1            |1           |1         |1                |1                 |1              |1           |1              |1             |1           |
