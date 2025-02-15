Meta:
@API
@component Search
@story search
@stage


Lifecycle:
Before:
Given I sign in as admin user

Scenario: SQM basic events search
Meta:@devsmoke
When I send SQM search request - query:<query>, sourceTypes:<sourceTypes>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>, sortKey:<sortKey>
When SQM search completed
When I get search queue results:
| eventFeed | objectType | pageNumber | pageSize |
| SIGINT | event | 0 | 20 |
| OSINT | event | 0 | 20 |
| GOVINT | event | 0 | 20 |
| GOVINT2 | event | 0 | 20 |
Then TotalCount's in search results > 0

Examples:
| sourceTypes | objectType | query  | pageNumber | pageSize | sortKey |
| SIGINT, OSINT, GOVINT, GOVINT2 | event | eventTime:[$NOW-90d..$NOW] | 0 | 20 | eventTime |
| SIGINT, OSINT, GOVINT, GOVINT2 | event | eventTime:[$NOW-90d..$NOW] | 0 | 20 | relevance |


Scenario: SQM basic entities search
Meta:@devsmoke
When I send SQM search request - query:<query>, sourceTypes:<sourceTypes>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>, sortKey:<sortKey>
When SQM search completed
When I get search queue results:
| eventFeed | objectType | pageNumber | pageSize |
| SIGINT | entity | 0 | 20 |
| OSINT | entity | 0 | 20 |
| GOVINT | entity | 0 | 20 |
| GOVINT2 | entity | 0 | 20 |
| PROFILER | entity | 0 | 20 |
| INFORMATION_MANAGEMENT | entity | 0 | 20 |
Then TotalCount's in search results > 0

Examples:
| sourceTypes | objectType | query  | pageNumber | pageSize |
| SIGINT, OSINT, GOVINT, GOVINT2, PROFILER, INFORMATION_MANAGEMENT | entity | | 0 | 20 | relevance |


Scenario: Event. The maximum number of search results for a single query is limited to 1000 records.
Meta: @nightly
When I send SQM search request - query:<query>, sourceTypes:<sourceTypes>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>, sortKey:<sortKey>
When SQM search completed
When I get search queue results:
| eventFeed | objectType | pageNumber | pageSize |
| SIGINT | event | 0 | 1100 |
| OSINT | event | 0 | 1100 |
| GOVINT | event | 0 | 1100 |
| GOVINT2 | event | 0 | 1100 |
Then TotalCount's in search results > 0
And TotalCount's in search results < 1001

Examples:
| sourceTypes | objectType | query  | pageNumber | pageSize |
| SIGINT, OSINT, GOVINT, GOVINT2 | event | eventTime:[$NOW-90d..$NOW] | 0 | 1100 | eventTime |


Scenario: Entity. The maximum number of search results for a single query is limited to 1000 records
Meta: @nightly
When I send SQM search request - query:<query>, sourceTypes:<sourceTypes>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>, sortKey:<sortKey>
When SQM search completed
When I get search queue results:
| eventFeed | objectType | pageNumber | pageSize |
| SIGINT | entity | 0 | 1100 |
| OSINT | entity | 0 | 1100 |
| GOVINT | entity | 0 | 1100 |
| GOVINT2 | entity | 0 | 1100 |
| PROFILER | entity | 0 | 1100 |
| INFORMATION_MANAGEMENT | entity | 0 | 1100 |
Then TotalCount's in search results > 0
And TotalCount's in search results < 1001

Examples:
| sourceTypes | objectType | query  | pageNumber | pageSize |
| SIGINT, OSINT, GOVINT, GOVINT2, PROFILER, INFORMATION_MANAGEMENT | entity | | 0 | 1100 | eventTime |


Scenario: J2 SMS/CALL search
When I send basic SQM search request - query:<query>, metadata:<metadata>, sourceTypes:<sourceTypes>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>, sortKey:<sortKey>
When SQM search completed
When I get search queue results:
| eventFeed | objectType | pageNumber | pageSize |
| SIGINT | event | 0 | 20 |
Then TotalCount's in search results > 0
And CB search results matched to filters

Examples:
| sourceTypes | objectType | query | metadata | pageNumber | pageSize |
| SIGINT | event | dataSource:"J2" AND type:("SMS" OR "MMS" OR "VSMS" OR "SIP_VIDEO") AND eventTime:[$NOW-90d..$NOW] | {"filters":{"eventFeed":"SIGINT","dataSource":["J2"],"objectType":"event","type":["TEXTING"]}} | 0 | 20 | eventTime |
| SIGINT | event | dataSource:"J2" AND type:"CALL" AND eventTime:[$NOW-90d..$NOW] | {"filters":{"eventFeed":"SIGINT","dataSource":["J2"],"objectType":"event","type":["CALL"]}} | 0 | 20 | eventTime |


Scenario: FININT search
When I send basic SQM search request - query:<query>, metadata:<metadata>, sourceTypes:<sourceTypes>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>, sortKey:<sortKey>
When SQM search completed
When I get search queue results:
| eventFeed | objectType | pageNumber | pageSize |
| FININT | event | 0 | 20 |
Then TotalCount's in search results > 0
And CB search results matched to filters

Examples:
| sourceTypes | objectType | query | metadata | pageNumber | pageSize |
| FININT | event | eventTime:[$NOW-90d..$NOW]| {"query":"","filters":{"eventFeed":"FININT","objectType":"event"}}| 0 | 20 | eventTime |