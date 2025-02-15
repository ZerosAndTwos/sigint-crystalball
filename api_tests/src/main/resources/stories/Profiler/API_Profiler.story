Meta:
@API
@component Profiler
@story profiler


Lifecycle:
Before:
Given I sign in as admin user

Scenario: Find in system or create test target from json
Given Find or create test target from json:<target>

Examples:
| target | image |
| profiles/Darkwing_Duck.json | profiles/Darkwing_Duck.jpg |


Scenario: Upload image to profiles
Given Find or create test target from json:<target>
When upload new target image:<image> to target
Then Request is successful
When I send get profile details request
Then Profile contain uploaded image

Examples:
| target | image |
| profiles/Darkwing_Duck.json | profiles/Darkwing_Duck.jpg |


Scenario: API.Create profile
Meta:@devsmoke11
When I send create finder file request
Then Request is successful
When I send create profile request
Then Request is successful

When I send get profile details request
Then Request is successful
And Profile is correct

When I send delete profile request
Then Request is successful
When I send delete finder file request
Then Request is successful


Scenario: API.Deleting of profile
Meta:@devsmoke11
When I send create finder file request
Then Request is successful
When I send create profile request
Then Request is successful

When I send get profile details request
Then Request is successful

When I send delete profile request
Then Request is successful
When I send delete finder file request
Then Request is successful

Scenario: API.Merge two profiles into one
When I send create finder file request
Then Request is successful
When I send create profile request
Then Request is successful
When I send create profile request
Then Request is successful

When I send merge two profile into one request
Then Request is successful
And Merged profile is correct

When I send get first merged profile details request
Then Request is successful
When I send get second merged profile details request
Then Request is successful

When I send delete profile request
Then Request is successful
When I send delete finder file request
Then Request is successful


Scenario: Add hit(s) for existing targets
Meta: @skip @nightly
Given I clean up ingestion directory
!-- find first profiler
When I search finder file members by query:<target>
Then Request is successful
When Get profiles from CBFinder search results
Then Profile list size > 0
When get random profile from profile list
And I send get profile details request
And send get profile identifierAggregations request
!-- add targets identifiers to injection file
And add <recordsCount> <identifierType> from profile:<target> to injection file
!-- data ingestion
Given Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested event records in CB == <recordsCount>
!-- verification
Then identifierAggregations hit counts for:<identifierType> of profile:<target> should incremented

Examples:
| target | identifierType | sourceType | recordType | recordsCount |
| Darkwing Duck, Drake Mallard | PHONE_NUMBER  | S | SMS | 1 |
| Darkwing Duck, Drake Mallard | IMEI | T | SMS | 1 |
| Darkwing Duck, Drake Mallard | IMSI | F | SMS | 1 |


Scenario: Get profile summary
When I send create finder file request
Then Request is successful
When I send create profile request
Then Request is successful

When I send get profile summary request
Then Request is successful
And Profile is correct

When I send delete profile request
Then Request is successful
When I send delete finder file request
Then Request is successful


Scenario: VoicePrint from manual audio
Meta: @skip @nightly
When I send create finder file request
Then Request is successful
When I send create profile request
Then Request is successful

When I send CB search request - query:type:"CALL" AND eventTime:[$NOW-90d..$NOW] AND HAS_VPRINT:"true", eventFeed:SIGINT, objectType:event, pageNumber:0, pageSize:1
Then CB search result list size > 0
When I download audioFile of call event from search results

When upload audio file to profile
Then uploaded audio file is processed
When create voiceID for profile
Then Request is successful
When I send get profile details request
Then profile contain created voiceID


Scenario: VoicePrint from call record
When I send create finder file request
Then Request is successful
When I send create profile request
Then Request is successful

When I send CB search request - query:type:"CALL" AND eventTime:[$NOW-90d..$NOW] AND HAS_VPRINT:"true", eventFeed:SIGINT, objectType:event, pageNumber:0, pageSize:1
Then CB search result list size > 0

When Get participants from random record in search results
And Add PHONE_NUMBER identifier to profile request
And Get random voice event from profile
And create voiceID for profile
Then Request is successful
When I send get profile details request
Then profile contain created voiceID

When Remove Voice ID request
Then Request is successful