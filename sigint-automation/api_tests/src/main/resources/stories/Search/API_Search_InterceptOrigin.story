Meta:
@API
@component Search
@story search
@stage

Narrative:
CB-19135 Track Intercept Origin for all SIGINT records (events)

Lifecycle:
Before:
Given I sign in as admin user


Scenario: Sigint [Call] interceptRef
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search results contains interceptRef field

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event | EventVO | dataSource:"S" AND type:"CALL" AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |
| SIGINT | event | EventVO | dataSource:"T" AND type:"CALL" AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |
| SIGINT | event | EventVO | dataSource:"F" AND type:"CALL" AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |
| SIGINT | event | EventVO | dataSource:"DU" AND type:"CALL" AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |
| SIGINT | event | EventVO | dataSource:"E" AND type:"CALL" AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |
| SIGINT | event | EventVO | dataSource:"O" AND type:"CALL" AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |
| SIGINT | event | EventVO | dataSource:"J2" AND type:"CALL" AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |


Scenario: Sigint [Texting] interceptRef
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search results contains interceptRef field

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event | EventVO | dataSource:"S" AND type:("SMS" OR "MMS" OR "VSMS" OR "SIP_VIDEO") AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |
| SIGINT | event | EventVO | dataSource:"T" AND type:("SMS" OR "MMS" OR "VSMS" OR "SIP_VIDEO") AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |
| SIGINT | event | EventVO | dataSource:"F" AND type:("SMS" OR "MMS" OR "VSMS" OR "SIP_VIDEO") AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |
| SIGINT | event | EventVO | dataSource:"E" AND type:("SMS" OR "MMS" OR "VSMS" OR "SIP_VIDEO") AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |
| SIGINT | event | EventVO | dataSource:"O" AND type:("SMS" OR "MMS" OR "VSMS" OR "SIP_VIDEO") AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |
| SIGINT | event | EventVO | dataSource:"DU" AND type:("SMS" OR "MMS" OR "VSMS" OR "SIP_VIDEO") AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |
| SIGINT | event | EventVO | dataSource:"J2" AND type:("SMS" OR "MMS" OR "VSMS" OR "SIP_VIDEO") AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |


Scenario: Sigint [International Roaming] interceptRef
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search results contains interceptRef field

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event | EventVO | dataSource:"E" AND type:("VLR" OR "MMS_ROAMING") AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |
| SIGINT | event | EventVO | dataSource:"S" AND type:("VLR" OR "MMS_ROAMING") AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |
| SIGINT | event | EventVO | dataSource:"O" AND type:("VLR" OR "MMS_ROAMING") AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |
| SIGINT | event | EventVO | dataSource:"DU" AND type:("VLR" OR "MMS_ROAMING") AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |


Scenario: Sigint. [Domestic Movement] interceptRef
Meta:
@skip
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search results contains interceptRef field

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event | EventVO | dataSource:"E" AND type:"LOCATION" AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |
| SIGINT | event | EventVO | dataSource:"DU" AND type:"LOCATION" AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |
| SIGINT | event | EventVO | dataSource:"O" AND type:"LOCATION" AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |


Scenario: Sigint. [Radio Signal] interceptRef
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search results contains interceptRef field

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event | EventVO | dataSource:"J1" AND type:"RADIO_INTERCEPT" AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |


Scenario: Sigint. [VoIP] interceptRef
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search results contains interceptRef field

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event | EventVO | dataSource:"S" AND type:"VOIP" AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |
| SIGINT | event | EventVO | dataSource:"O" AND type:"VOIP" AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |


Scenario: Sigint. [FAX] interceptRef
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search results contains interceptRef field

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event | EventVO | dataSource:"S" AND type:"FAX" AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |
| SIGINT | event | EventVO | dataSource:"O" AND type:"FAX" AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |


Scenario: Sigint. [Email] interceptRef
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search results contains interceptRef field

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event | EventVO | dataSource:"S" AND type:"EMAIL" AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |
| SIGINT | event | EventVO | dataSource:"O" AND type:"EMAIL" AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |
| SIGINT | event | EventVO | dataSource:"F" AND type:"EMAIL" AND eventTime:[$NOW-7d..$NOW] | 0 | 20 |