Meta:
@API
@component Admin
@story designation-mappings

Lifecycle:
Before:
Given I sign in as admin user
When I send create new random designation-mapping request
Then Request is successful
And Designation-mapping is correct
After:
Given I sign in as admin user
When I send delete designation-mapping request
Then Request is successful

Scenario: API.Create designation-mapping
When I send get list of designation-mappings request
Then Request is successful
Then Designation-mappings list size is more than 0
Then Designation-mapping is in list

Scenario: API.Get designation-mapping by id
When I send view designation-mapping request
Then Request is successful
And Designation-mapping is correct

Scenario: API.Update designation-mapping
When I send update designation-mapping request
Then Request is successful
And Designation-mapping is correct

Scenario: API.Delete designation-mapping
When I send view designation-mapping request
Then Request is successful

Scenario: API.Get designation-mappings list
When I send get list of designation-mappings request
Then Request is successful
And Designation-mappings list size is more than 0

Scenario: API.Search designation-mappings by filters
Meta:
@issue CB-22787
When I send search designation-mappings by <criteria> with <value> request
Then Request is successful
And Designation-mappings list size  is more than 0
And Designation-mappings search result is correct

Examples:
| criteria      | value  |
| type          | random |
| identifier    | random |
| spam          | random |
| designation   | random |
| updatedAfter  | random |
| empty         |        |

Scenario: API. Import designation-mappings
Given I generate 5 random designation-mappings
And I write designation-mappings to CSV with header
When I send import designation-mappings request
Then Request is successful
And Imported 5 designation-mappings, modified 5
And I delete designation-mappings

Scenario: Import non .csv file for designation-mapping records
Meta: @notstage @docker
Given S - Fax files with 1 records are generated
And I pick random file from ingestion files list
When I send import designation-mappings request
Then I got response code 400
And Message contains "Unable to import non csv files"

Scenario: Import designation-mappings with duplicate identifiers but different designations
Given I generate 1 random designation-mappings
And I add the same designation-mapping with "Another designation" designation to the list
And I write designation-mappings to CSV with header
When I send import designation-mappings request
Then Request is successful
And Imported 2 designation-mappings, modified 1
And I delete designation-mappings

Scenario: API. Import designation-mappings with duplicate values
Given I generate 1 random designation-mappings
And I write designation-mappings to CSV with header
When I send import designation-mappings request
Then Request is successful
And Imported 1 designation-mappings, modified 1
When I send import designation-mappings request
Then Request is successful
And Imported 0 designation-mappings, modified 0
And Message contains "already exists"

Scenario: API. Import designation-mappings with errors in file
Given I generate 1 random designation-mappings
And I add broken designation-mapping to the list
And I write designation-mappings to CSV with header
When I send import designation-mappings request
Then Request is successful
And Message contains "Designations are not set"
And Imported 1 designation-mappings, modified 1
And I delete designation-mappings

Scenario: API. Create not existing designation during designation-mappings import
Given I generate 1 random designation-mappings
And I add random designation-mapping to the list
And I write designation-mappings to CSV with header
When I send import designation-mappings request
Then Request is successful
And Imported 2 designation-mappings, modified 2
And I delete designation-mappings

When I send search designations by name with random request
Then Request is successful
And Designations list size is more than 0
And Designations search result is correct

Scenario: CB Search filters designated spam records by default (SIGINT, S-Source)
Meta: @nightly @dm_enrichment @docker
Given I clean up ingestion directory
When I send search designation-mappings by type with PHONE_NUMBER request, with spam flag: true
Then Request is successful
And Designation-mappings list size is more than 10

Given I add 10 designation-mappings to injections file
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed, exclude: .tif
And Number of ingested event records in CB == <recordsCount>
And Number of ingested event records in CB < <recordsCount>, additional query string: AND includeSpam:false
And Designated events have correct designations

Examples:
| sourceType | recordType | recordsCount |
| S          | Voice      | 10           |
| S          | Fax        | 25           |
| S          | CELL       | 25           |
| S          | CDR        | 25           |
| S          | VLR        | 25           |
| S          | SMS        | 25           |

Scenario: CB Search filters designated spam records by default (SIGINT, T-Source)
Meta: @nightly @dm_enrichment @docker
Given I clean up ingestion directory
When I send search designation-mappings by type with PHONE_NUMBER request, with spam flag: true
Then Request is successful
And Designation-mappings list size is more than 10

Given I add 10 designation-mappings to injections file
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Number of ingested event records in CB == <recordsCount>
And Number of ingested event records in CB < <recordsCount>, additional query string: AND includeSpam:false
And Designated events have correct designations

Examples:
| sourceType | recordType | recordsCount |
| T          | Voice      | 10           |
| T          | SMS        | 25           |

Scenario: CB Search filters designated spam records by default (SIGINT, F-Source)
Meta: @nightly @dm_enrichment @docker
Given I clean up ingestion directory
When I send search designation-mappings by type with PHONE_NUMBER request, with spam flag: true
Then Request is successful
And Designation-mappings list size is more than 10

Given I add 10 designation-mappings to injections file
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Number of ingested event records in CB == <recordsCount>
And Number of ingested event records in CB < <recordsCount>, additional query string: AND includeSpam:false
And Designated events have correct designations

Examples:
| sourceType | recordType | recordsCount |
| F          | SMS        | 25           |

Scenario: CB Search filters designated spam records by default (SIGINT, E-Source)
Meta: @nightly @dm_enrichment @docker
Given I clean up ingestion directory
When I send search designation-mappings by type with PHONE_NUMBER request, with spam flag: true
Then Request is successful
And Designation-mappings list size is more than 10

Given I add 10 designation-mappings to injections file
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Number of ingested event records in CB == <recordsCount>
And Number of ingested event records in CB < <recordsCount>, additional query string: AND includeSpam:false
And Designated events have correct designations

Examples:
| sourceType | recordType | recordsCount |
| E          | CDR        | 25           |
| E          | SMS2       | 25           |
| E          | SMS3       | 25           |
| E          | SMS4       | 25           |


Scenario: CB Search doesn't filter designated not-spam records (SIGINT, S-Source)
Meta: @nightly @dm_enrichment @docker
Given I clean up ingestion directory
When I send search designation-mappings by type with PHONE_NUMBER request, with spam flag: false
Then Request is successful
And Designation-mappings list size is more than 10

Given I add 10 designation-mappings to injections file
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed, exclude: .tif
And Number of ingested event records in CB == <recordsCount>, additional query string: AND includeSpam:false
And Designated events have correct designations

Examples:
| sourceType | recordType | recordsCount |
| S          | Voice      | 10           |
| S          | Fax        | 25           |
| S          | CELL       | 25           |
| S          | CDR        | 25           |
| S          | VLR        | 25           |
| S          | SMS        | 25           |

Scenario: CB Search doesn't filter designated not-spam records (SIGINT, T-Source)
Meta: @nightly @dm_enrichment @docker
Given I clean up ingestion directory
When I send search designation-mappings by type with PHONE_NUMBER request, with spam flag: false
Then Request is successful
And Designation-mappings list size is more than 10

Given I add 10 designation-mappings to injections file
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Number of ingested event records in CB == <recordsCount>, additional query string: AND includeSpam:false
And Designated events have correct designations

Examples:
| sourceType | recordType | recordsCount |
| T          | Voice      | 10           |
| T          | SMS        | 25           |

Scenario: CB Search doesn't filter designated not-spam records (SIGINT, F-Source)
Meta: @nightly @dm_enrichment @docker
Given I clean up ingestion directory
When I send search designation-mappings by type with PHONE_NUMBER request, with spam flag: false
Then Request is successful
And Designation-mappings list size is more than 10

Given I add 10 designation-mappings to injections file
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Number of ingested event records in CB == <recordsCount>, additional query string: AND includeSpam:false
And Designated events have correct designations

Examples:
| sourceType | recordType | recordsCount |
| F          | SMS        | 25           |

Scenario: CB Search doesn't filter designated not-spam records (SIGINT, E-Source)
Meta: @nightly @dm_enrichment @docker
Given I clean up ingestion directory
When I send search designation-mappings by type with PHONE_NUMBER request, with spam flag: false
Then Request is successful
And Designation-mappings list size is more than 10

Given I add 10 designation-mappings to injections file
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Number of ingested event records in CB == <recordsCount>, additional query string: AND includeSpam:false
And Designated events have correct designations

Examples:
| sourceType | recordType | recordsCount |
| E          | CDR        | 25           |
| E          | SMS2       | 25           |
| E          | SMS3       | 25           |
| E          | SMS4       | 25           |


Scenario: Undesignated records are marked with default designation 'Undesignated'
Meta: @nightly @dm_enrichment @docker
Given Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested event records in CB == <recordsCount>, additional query string: AND includeSpam:false
And All events have default designation

Examples:
| sourceType | recordType | recordsCount |
| F          | SMS        | 25           |
| S          | SMS        | 25           |
| T          | SMS        | 25           |
| E          | SMS2       | 25           |