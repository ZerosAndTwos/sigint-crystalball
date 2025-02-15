Meta:
@API
@component Admin
@story whitelist

Lifecycle:
Before:
Given I sign in as admin user
When I send create new whitelist entry request
Then Request is successful
And Whitelist entry is correct
After:
Given I sign in as admin user
When I send delete whitelist entry request
Then Request is successful


Scenario: API. Create new whitelist entry
When I send get list of whitelist entries request
Then Request is successful
Then Whitelists list size is more than 0
Then Whitelist entry is in list

Scenario: API.Get whitelist entry details by id
When I send view whitelist entry request
Then Request is successful
And Whitelist entry is correct

Scenario: API. Update whitelist entry
When I send update whitelist entry request
Then Request is successful
And Whitelist entry is correct

Scenario: API. Delete whitelist entry
When I send view whitelist entry request
Then Request is successful

Scenario: API. Get all whitelist entries
When I send get list of whitelist entries request
Then Request is successful
And Whitelists list size is more than 0

Scenario: API.Search whitelists by filters
When I send search whitelists by <criteria> with <value> request
Then Request is successful
And Whitelists list size is more than 0
And Whitelists search result is correct

Examples:
| criteria      | value  |
| identifier    | random |
| description   | random |
| type          | random |
| updatedAfter  | random |
| empty         |        |

Scenario: API. Import whitelists
Given I generate 5 random whitelists
And I write whitelists to CSV without header
When I send import whitelists request
Then Request is successful
And Imported 5 whitelists, modified 5
And I delete whitelists

Scenario: API. Import whitelists with errors in file
Meta: @skip
Given I generate 1 random whitelists
And I write whitelists to CSV with header
When I send import whitelists request
Then Request is successful
And Message contains "Type is not set"
And Imported 1 whitelists, modified 1
And I delete whitelists

Scenario: API. Import whitelists with duplicate values
Given I generate 1 random whitelists
And I write whitelists to CSV without header
When I send import whitelists request
Then Request is successful
And Imported 1 whitelists, modified 1
When I send import whitelists request
Then Request is successful
And Imported 0 whitelists, modified 0
And Message contains "already exists"


Scenario: Import non .csv file for whitelist records
Meta: @nightly @notstage @docker
Given S - Fax files with 1 records are generated
And I pick random file from ingestion files list
When I send import whitelists request
Then I got response code 400
And Message contains "Unable to import non csv files"


Scenario: Filtering off whitelisted data during ingestion (SIGINT, T-Source)
Meta: @nightly @wl_enrichment @whitelist-t @docker
Given I clean up ingestion directory
When I send search whitelists by type with PHONE_NUMBER request
Then Request is successful
And Whitelists list size is more than 10

Given I add 10 whitelists to injections file
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed, exclude: .wav
And Number of ingested event records in CB < <recordsCount>
And Whitelisted identifiers are not searchable

Examples:
| sourceType | recordType | recordsCount |
| T          | Voice      | 15           |
| T          | SMS        | 25           |

Scenario: Filtering off whitelisted data during ingestion (SIGINT, Phonebook)
Meta: @nightly @wl_enrichment @whitelist-phonebook @docker
Given I clean up ingestion directory
When I send search whitelists by type with PHONE_NUMBER request
Then Request is successful
And Whitelists list size is more than 10

Given I add 10 whitelists to injections file
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Number of ingested event records in CB == 0
And Whitelisted identifiers are not searchable

Examples:
| sourceType | recordType | recordsCount |
| PHONEBOOK  | PHONEBOOK  | 25           |

Scenario: Filtering off whitelisted data during ingestion (SIGINT, F-Source)
Meta: @nightly @wl_enrichment @whitelist-f @docker
Given I clean up ingestion directory
When I send search whitelists by type with PHONE_NUMBER request
Then Request is successful
And Whitelists list size is more than 10

Given I add 10 whitelists to injections file
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Number of ingested event records in CB < <recordsCount>
And Whitelisted identifiers are not searchable

Examples:
| sourceType | recordType | recordsCount |
| F          | SMS        | 25           |

Scenario: Filtering off whitelisted data during ingestion (SIGINT, S-Source)
Meta: @nightly @wl_enrichment @whitelist-s @docker
Given I clean up ingestion directory
When I send search whitelists by type with PHONE_NUMBER request
Then Request is successful
And Whitelists list size is more than 10

Given I add 10 whitelists to injections file
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed, exclude: .wav, .tif
And Number of ingested event records in CB < <recordsCount>
And Whitelisted identifiers are not searchable

Examples:
| sourceType | recordType | recordsCount |
| S          | Voice      | 15           |
| S          | CELL       | 25           |
| S          | CDR        | 25           |
| S          | VLR        | 25           |
| S          | Fax        | 25           |
| S          | SMS        | 25           |

Scenario: Filtering off whitelisted data during ingestion (SIGINT, Etisalat-Source)
Meta: @nightly @wl_enrichment @whitelist-e @docker
Given I clean up ingestion directory
When I send search whitelists by type with PHONE_NUMBER request
Then Request is successful
And Whitelists list size is more than 10

Given I add 10 whitelists to injections file
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Number of ingested event records in CB < <recordsCount>
And Whitelisted identifiers are not searchable

Examples:
| sourceType | recordType  | recordsCount |
| E          | CDR         | 25           |
| E          | SMS2        | 25           |
| E          | SMS3        | 25           |
| E          | SMS4        | 25           |
| E          | Subscriber1 | 25           |
| E          | Subscriber2 | 25           |
