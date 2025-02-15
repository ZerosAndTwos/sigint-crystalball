Meta:
@API
@component Ingestion
@story ingestion
@skip

Scenario: API.Ingestion with the wrong source type
Meta: @nightly @Wrong-source-ingestion
Given I sign in as admin user
And Data source with F and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Request is successful
When I wait for 10 seconds
When I send get file meta request
Then Message contains "Too many errors during parsing file"

Examples:
| sourceType | recordType | recordsCount |
| S          | SMS        | 10           |


Scenario: Upload several files with the same name into one source
Meta: @nightly @Same-file-upload
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files

When I upload files
Then I got response code 409
And Message contains "FileAlreadyExistsException"

Examples:
| sourceType | recordType | recordsCount |
| F          | SMS        | 10           |



Scenario: API.F-SMS ingestion
Meta: @nightly @F-SMS
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested event records in CB == <recordsCount>
And Number of ingested entity records in CB == 0

Examples:
| sourceType | recordType | recordsCount |
| F          | SMS        | 100          |



Scenario: API.Phonebook ingestion
Meta: @nightly @Phonebook
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested entity records in CB == <recordsCount>
And Number of ingested event records in CB == 0

Examples:
| sourceType | recordType | recordsCount |
| PHONEBOOK  | PHONEBOOK  | 100          |



Scenario: API.E-CellTower format1 ingestion
Meta: @nightly @E-CellTower1
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
When I wait for 60 seconds
Then Number of ingested entity records in CB == 0
And Number of ingested event records in CB == 0

Examples:
| sourceType | recordType  | recordsCount |
| E          | CellTower1  | 100          |


Scenario: API.E-CellTower format2 ingestion
Meta: @nightly @E-CellTower2
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
When I wait for 60 seconds
Then Number of ingested entity records in CB == 0
And Number of ingested event records in CB == 0

Examples:
| sourceType | recordType  | recordsCount |
| E          | CellTower2  | 100          |


Scenario: API.E-Subscriber format1 ingestion
Meta: @nightly @E-Subscriber1
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested entity records in CB == <recordsCount>
And Number of ingested event records in CB == 0

Examples:
| sourceType | recordType  | recordsCount |
| E          | Subscriber1 | 100          |


Scenario: API.E-Subscriber format2 ingestion
Meta: @nightly @E-Subscriber2
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested entity records in CB == <recordsCount>
And Number of ingested event records in CB == 0

Examples:
| sourceType | recordType  | recordsCount |
| E          | Subscriber2 | 100          |


Scenario: API.E-SMS format2 ingestion
Meta: @nightly @E-SMS2
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested event records in CB == <recordsCount>
And Number of ingested entity records in CB == 0

Examples:
| sourceType | recordType | recordsCount |
| E          | SMS2       | 100          |


Scenario: API.E-SMS format3 ingestion
Meta: @nightly @E-SMS3
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested event records in CB == <recordsCount>
And Number of ingested entity records in CB == 0

Examples:
| sourceType | recordType | recordsCount |
| E          | SMS3       | 100          |


Scenario: API.E-SMS format4 ingestion
Meta: @nightly @E-SMS4
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested event records in CB == <recordsCount>
And Number of ingested entity records in CB == 0

Examples:
| sourceType | recordType | recordsCount |
| E          | SMS4       | 100          |


Scenario: API.E-CDR ingestion
Meta: @nightly @E-CDR
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested event records in CB == <recordsCount>
And Number of ingested entity records in CB == 0

Examples:
| sourceType | recordType | recordsCount |
| E          | CDR        | 100          |


Scenario: API.E-NLD_AIF ingestion
Meta: @nightly @E-NLD_AIF
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested event records in CB == <recordsCount>
And Number of ingested entity records in CB == 0

Examples:
| sourceType | recordType | recordsCount     |
| E          | NLD_AIF    | 100              |


Scenario: API.E-NLD_IUCS ingestion
Meta: @nightly @E-NLD_IUCS
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested event records in CB == <recordsCount>
And Number of ingested entity records in CB == 0

Examples:
| sourceType | recordType | recordsCount     |
| E          | NLD_IUCS   | 100              |


Scenario: API.E-NLD_IUPS ingestion
Meta: @nightly @E-NLD_IUPS
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested event records in CB == <recordsCount>
And Number of ingested entity records in CB == 0

Examples:
| sourceType | recordType | recordsCount     |
| E          | NLD_IUPS   | 100              |



Scenario: API.S-Voice ingestion
Meta: @nightly @S-Voice
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested event records in CB == <recordsCount>
And Number of ingested entity records in CB == 0

Examples:
| sourceType | recordType | recordsCount |
| S          | Voice      | 10           |


Scenario: API.S-Fax ingestion
Meta: @nightly @S-Fax
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed, exclude: .tif
And Original data file is searchable within the system
And Number of ingested event records in CB == <recordsCount>
And Number of ingested entity records in CB == 0

Examples:
| sourceType | recordType | recordsCount |
| S          | Fax        | 100          |


Scenario: API.S-SMS ingestion
Meta: @nightly @S-SMS
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested event records in CB == <recordsCount>
And Number of ingested entity records in CB == 0

Examples:
| sourceType | recordType | recordsCount |
| S          | SMS        | 100          |


Scenario: API.S-VLR ingestion
Meta: @nightly @S-VLR
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested event records in CB == <recordsCount>
And Number of ingested entity records in CB == 0

Examples:
| sourceType | recordType | recordsCount |
| S          | VLR        | 100          |


Scenario: API.S-CDR ingestion
Meta: @nightly @S-CDR
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested event records in CB == <recordsCount>
And Number of ingested entity records in CB == 0

Examples:
| sourceType | recordType | recordsCount |
| S          | CDR        | 100          |


Scenario: API.S-CELL ingestion
Meta: @nightly @S-CELL
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested event records in CB == <recordsCount>
And Number of ingested entity records in CB == 0

Examples:
| sourceType | recordType | recordsCount |
| S          | CELL       | 100          |


Scenario: API.S-Email ingestion
Meta: @nightly @S-Email
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested event records in CB == <recordsCount>
And Number of ingested entity records in CB == 0

Examples:
| sourceType | recordType | recordsCount |
| S          | Email      | 10           |


Scenario: API.S-VoIP ingestion
Meta: @nightly @S-VOIP
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested event records in CB == <recordsCount>
And Number of ingested entity records in CB == 0

Examples:
| sourceType | recordType | recordsCount |
| S          | VOIP       | 10           |



Scenario: API.T-SMS ingestion
Meta: @nightly @T-SMS
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested event records in CB == <recordsCount>
And Number of ingested entity records in CB == 0

Examples:
| sourceType | recordType | recordsCount |
| T          | SMS        | 100          |


Scenario: API.T-Voice ingestion
Meta: @nightly @T-Voice
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested event records in CB == <recordsCount>
And Number of ingested entity records in CB == 0

Examples:
| sourceType | recordType | recordsCount |
| T          | Voice      | 10           |



Scenario: API.O-SMS ingestion
Meta: @nightly @O-SMS @skip
Given I sign in as admin user
!-- And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
!-- And I create remote path for ingestion
!-- When I upload files
!-- Then Uploaded files are processed
!-- And Original data file is searchable within the system
!-- Then Number of ingested event records in CB == <recordsCount>
!-- And Number of ingested entity records in CB == 0

Examples:
| sourceType | recordType | recordsCount |
| O          | SMS        | 100          |



Scenario: API.DU-SMS ingestion
Meta: @nightly @DU-SMS
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested event records in CB == <recordsCount>

Examples:
| sourceType | recordType | recordsCount |
| DU         | SMS        | 100          |


Scenario: API.DU-VLR ingestion
Meta: @nightly @DU-VLR
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested event records in CB == <recordsCount>

Examples:
| sourceType | recordType | recordsCount |
| DU         | VLR        | 100          |

Scenario: API.DU-IMSISubConsumer ingestion
Meta: @nightly @DU-IMSISubConsumer
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested entity records in CB == <recordsCount>

Examples:
| sourceType | recordType | recordsCount |
| DU         | IMSIToSubConsumer        | 100          |

Scenario: API.DU-IMSISubMobile ingestion
Meta: @nightly @DU-IMSISubMobile
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested entity records in CB == <recordsCount>

Examples:
| sourceType | recordType | recordsCount |
| DU         | IMSIToSubMobile        | 100          |

Scenario: API.DU-IMSISubEnterprise ingestion
Meta: @nightly @DU-IMSISubEnterprise
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested entity records in CB == <recordsCount>

Examples:
| sourceType | recordType | recordsCount |
| DU         | IMSIToSubEnterPrise        | 100          |

Scenario: API.DU-NLD_VLR ingestion
Meta: @nightly @DU-NLD_VLR
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested entity records in CB == <recordsCount>

Examples:
| sourceType | recordType | recordsCount |
| DU         | NLDVLR        | 100

Scenario: API.DU-NLD_HO ingestion
Meta: @nightly @DU-NLD_HO
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested entity records in CB == <recordsCount>

Examples:
| sourceType | recordType | recordsCount |
| DU         | NLDHO        | 100

Scenario: API.DU-NLD_4glur ingestion
Meta: @nightly @DU-NLD_4glur
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
And <sourceType> - <recordType> files with <recordsCount> records are generated
And I create remote path for ingestion
When I upload files
Then Uploaded files are processed
And Original data file is searchable within the system
And Number of ingested entity records in CB == <recordsCount>

Examples:
| sourceType | recordType | recordsCount |
| DU         | NLD4glur        | 100

Scenario: API.Search and count data folders by path
Meta: @nightly @Search-folders-by-path
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
When I search data folders by source with date pattern "yyyy/MM"
Then Found folders list size > 0
When I send count found folders request
Then Folders count result is equal to appropriate list size

Examples:
| sourceType | recordType |
| F          | SMS        |
| S          | SMS        |
| E          | SMS        |


Scenario: API.Search and count data folders by wildcard
Meta: @nightly @Search-folders-by-wildcard
Given I sign in as admin user
When I search data folders with date pattern "*/yyyy"
Then Found folders list size > 0
When I send count found folders request
Then Folders count result is equal to appropriate list size


Scenario: API.Search and count data files by path
Meta: @nightly @Search-files-by-path
Given I sign in as admin user
And Data source with <sourceType> and <recordType> exists
When I search data folders by source with date pattern "yyyy/MM/dd"
Then Found folders list size > 0
When I search data files by path
Then Found files list size > 0
When I send count found files request
Then Files count result is equal to appropriate list size

Examples:
| sourceType | recordType |
| F          | SMS        |
| S          | SMS        |
| E          | SMS        |


Scenario: API.Search and count data files by wildcard
Meta: @nightly @Search-files-by-wildcard
Given I sign in as admin user
When I search data files with date pattern "*/yyyy/MM/dd/*"
Then Found files list size > 0
When I send count found files request
Then Files count result is equal to appropriate list size