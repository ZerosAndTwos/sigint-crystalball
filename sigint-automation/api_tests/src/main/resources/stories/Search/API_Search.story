TODO list:
GOVINT - Fuzzy, Wildcard
PROFILER - Fuzzy, Wildcard
entity - add Wildcard, Fuzzy scenarios

Meta:
@API
@component Search
@story search
@stage

Lifecycle:
Before:
Given I sign in as admin user

Scenario: test excel
Meta: @skip
When Excel Driven Search (/Data/Search.xlsx)


Scenario: SIGINT. Wild Card Search
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0
Then CB search contains results for query

Examples:
| eventFeed | objectType | query | pageNumber | pageSize |
| SIGINT | event | knowledge | 0 | 20 |
| SIGINT | event | kno?le?ge | 0 | 20 |
| SIGINT | event | k*ledge   | 0 | 20 |
| SIGINT | event | k*le?ge  | 0 | 20 |


Scenario: OSINT. Wild Card Search
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0
Then CB search contains results for query

Examples:
| eventFeed | objectType | query | pageNumber | pageSize |
| OSINT | event | knowledge | 0 | 20 |
| OSINT | event | kno?le?ge | 0 | 20 |
| OSINT | event | k*ledge   | 0 | 20 |
| OSINT | event | k*le?ge  | 0 | 20 |


Scenario: SIGINT. Fuzzy Search
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0
Then CB search contains results for query

Examples:
| eventFeed | objectType | query | pageNumber | pageSize |
| SIGINT | event | knoeledge~1 | 0 | 20 |
| SIGINT | event | knowledge~2  | 0 | 20 |
| SIGINT | event | nowledge~  | 0 | 20 |


Scenario: OSINT. Fuzzy Search
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0
Then CB search contains results for query

Examples:
| eventFeed | objectType | query | pageNumber | pageSize |
| OSINT | event | knoeledge~1 | 0 | 20 |
| OSINT | event | knowledge~2  | 0 | 20 |
| OSINT | event | nowledge~  | 0 | 20 |


Scenario: Search. [SIGINT] Workflow filters. Record status: Unassigned
When I send workflow search request: record status:<recordStatus>, source:<source>, objectType:<objectType>,
pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0
And CB search results match the recordStatus filters

Examples:
| source | objectType | recordStatus | pageNumber | pageSize |
| SIGINT | event | unassigned | 0 | 100 |


Scenario: Search. [SIGINT] Workflow filters. Record status: Unprocessed
When I send workflow search request: record status:<recordStatus>, source:<source>, objectType:<objectType>,
pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0
And CB search results match the recordStatus filters

Examples:
| source | objectType | recordStatus | pageNumber | pageSize |
| SIGINT | event | unprocessed | 0 | 100 |


Scenario: Search. [SIGINT] Workflow filters. Record status: Reported
When I send workflow search request: record status:<recordStatus>, source:<source>, objectType:<objectType>,
pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0
And CB search results match the recordStatus filters

Examples:
| source | objectType | recordStatus | pageNumber | pageSize |
| SIGINT | event | reported | 0 | 100 |


Scenario: Search. [SIGINT] Workflow filters. Record status: Unimportant
When I send workflow search request: record status:<recordStatus>, source:<source>, objectType:<objectType>,
pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0
And CB search results match the recordStatus filters

Examples:
| source | objectType | recordStatus | pageNumber | pageSize |
| SIGINT | event | unimportant | 0 | 100 |


Scenario: Search. [SIGINT] Data Source filters.
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0
And CB search results contains only sourceType from query

Examples:
| eventFeed | objectType | query  | pageNumber | pageSize |
| SIGINT | event | dataSource:"DU"| 0 | 100 |
| SIGINT | event | dataSource:"E" | 0 | 100 |
| SIGINT | event | dataSource:"F" | 0 | 100 |
| SIGINT | event | dataSource:"O"| 0 | 100 |
| SIGINT | event | dataSource:"H" | 0 | 100 |
| SIGINT | event | dataSource:"S" | 0 | 100 |
| SIGINT | event | dataSource:"T" | 0 | 100 |
| SIGINT | entity | dataSource:"DU" | 0 | 100 |
| SIGINT | entity | dataSource:"E" | 0 | 100 |
| SIGINT | entity | dataSource:"PHONEBOOK" | 0 | 100 |


Scenario: Search. [SIGINT] Data Subsource filters
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0
And CB search results contains only subSource from query

Examples:
| eventFeed | objectType | query  | pageNumber | pageSize |
| SIGINT | event | subSource:"CDR" | 0 | 100 |
| SIGINT | event | subSource:"CELL" | 0 | 100 |
| SIGINT | event | subSource:"FAX" | 0 | 100 |
| SIGINT | event | subSource:"SMS" | 0 | 100 ||
| SIGINT | event | subSource:"VLR" | 0 | 100 |
| SIGINT | event | subSource:"Voice" | 0 | 100 |
| SIGINT | event | subSource:"MMS" | 0 | 100 |
| SIGINT | event | subSource:"NLD" | 0 | 100 |
| SIGINT | entity | subSource:"Subscriber" | 0 | 100 |


Scenario: Search. [SIGINT] Record Type filters
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0
And CB search results contains only recordType from query

Examples:
| eventFeed | objectType | query  | pageNumber | pageSize |
| SIGINT | event | type:"CALL" | 0 | 100 |
| SIGINT | event | type:"LOCATION" | 0 | 100 |
| SIGINT | event | type:"EMAIL" | 0 | 100 |
| SIGINT | event | type:"FAX" | 0 | 100 |
| SIGINT | event | type:"VLR" | 0 | 100 |
| SIGINT | event | type:"MMS_ROAMING" | 0 | 100 |
| SIGINT | event | type:"SMS" | 0 | 100 |
| SIGINT | event | type:"MMS" | 0 | 100 |
| SIGINT | event | type:"VSMS" | 0 | 100 |
| SIGINT | event | type:"SIP_VIDEO" | 0 | 100 |
| SIGINT | entity | type:"TELECOM_SUBSCRIBER" | 0 | 100 |


Scenario: Search. [SIGINT] EventTime filter
When I send CB search request - eventTime:<eventTime>, source:<source>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0
And CB search results contains only eventTime from query

Examples:
| source | objectType | eventTime | pageNumber | pageSize |
| SIGINT | event | LAST_MONTH | 0 | 100 |


Scenario: POST /api/search/count
Meta: @skip
When I send CB search count request - query:<query>, objectType:<objectType>, sources:<source>
Then Request is successful
And TotalCount's in search results <criteria> <size>

Examples:
| source                                    | objectType | query | criteria | size |
| SIGINT, OSINT, GOVINT                     | event      |       | >        | 0    |
| SIGINT, INFORMATION_MANAGEMENT, OSINT, GOVINT, GOVINT2, PROFILER | entity |   | > | 0 |


Scenario: Search. Source Type filters
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0
And CB search results contains only eventFeed:<eventFeed> and type:<resultType> records

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event | EventVO | | 0 | 100 |
| SIGINT | entity | EntityVO | | 0 | 100 |
| OSINT | event | EventVO | | 0 | 100 |
| OSINT | entity | EntityVO | | 0 | 100 |
| GOVINT | event | EventVO | | 0 | 100 |
| GOVINT | entity | EntityVO | | 0 | 100 |
| GOVINT2 | event | EventVO | | 0 | 100 |
| GOVINT2 | entity | EntityVO | | 0 | 100 |
| INFORMATION_MANAGEMENT | entity | EntityVO | | 0 | 100 |
| PROFILER | entity | TargetVO | | 0 | 100 |