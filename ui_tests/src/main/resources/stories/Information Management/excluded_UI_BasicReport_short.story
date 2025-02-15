Meta:
@UI
@component Information Management

Lifecycle:
Before:
Scope: SCENARIO
Given load story ../aux-main-stories/auxSignIn.story

Scenario: Add to type of source to report
Given I navigate to CB Finder
Given I start creation of new file in the CB Finder
Given I set Name (<FileName>) for new file in the CB Finder
Given I set Classification (<FileCalssif>) for new file in the CB Finder
Given I set Organization Units (|<FileOrgUnit>|) for new file in the CB Finder
Given I save new file created in the CB Finder

Given I navigate to Search
When I enter search criteria (<SIGINTEventIMSI>) on the Search page
Given I setup Search Authorization

When I open Card View

When I select all SIGINT event cards having identifiers: |<SIGINTEventPhone>|
When I add selected items to the existing report (<ReportSubj>)
When I save currently opened operator report as draft
When I set Classification (<ReportClassif>) in operator report
When I set File Name/Case Name (|<FileName>|) in operator report
When I set Organization Units (|<ReportOrgUnit>|) in operator report
When I set Subject (<ReportSubj>) in operator report
When I save currently opened operator report as draft

When I enter search criteria (<EIDEventName>) on the Search page
When I select all EID event cards having identifiers: |<EIDEventUIDNumber>|<EIDEventVisa>|
When I add selected items to the existing report (<ReportSubj>)
When I save currently opened operator report as draft

When I enter search criteria (<GOVINTEventName>) on the Search page
When I select all GOVINT event cards having identifiers: |<GOVINTEventBookRef>|<GOVINTEventCarrier>|
When I add selected items to the existing report (<ReportSubj>)
When I save currently opened operator report as draft

When I enter search criteria (<OSINTEventName>) on the Search page
When I select all OSINT event cards having identifiers: |<OSINTEventID>|
When I add selected items to the existing report (<ReportSubj>)
When I save currently opened operator report as draft

When I enter search criteria (<SIGINTEntityPhone>) on the Search page
When I select all SIGINT entity cards having identifiers: |<SIGINTEntityName>|<SIGINTEntityIMSI>|
When I add selected items to the existing report (<ReportSubj>)
When I save currently opened operator report as draft

When I enter search criteria (<EIDEntityUIDNumber>) on the Search page
When I select all EID entity cards having identifiers: |<EIDEntityEIDNumber>|<EIDEntityGender>|
When I add selected items to the existing report (<ReportSubj>)
When I save currently opened operator report as draft

When I enter search criteria (<GOVINYEntityName>) on the Search page
When I select all GOVINT entity cards having identifiers: |<GOVINTEntityUIDNumber>|<GOVINTEntityEIDNumber>|<GOVINTEntityGender>|
When I add selected items to the existing report (<ReportSubj>)
When I save currently opened operator report as draft

When I enter search criteria (<OSINTEntityName>) on the Search page
When I select all OSINT entity cards having identifiers: |<OSINTEntityID>|
When I add selected items to the existing report (<ReportSubj>)
When I save currently opened operator report as draft

Given I navigate to CB Finder
When I select file with Name (<FileName>) in the CB Finder
When I open operator report with Subject (<ReportSubj>) in file/case which is currently selected in the CB Finder
Then I should see that currently opened operator report has status (Initial Draft)
Then I should see card attached to currently opened operator report with all following label/value pair(s): |From Number|<SIGINTEventPhone>|From IMSI|<SIGINTEventIMSI>|
Then I should see card attached to currently opened operator report with all following label/value pair(s): |Full Name|<EIDEventName>|UID Number|<EIDEventUIDNumber>|Visa Number|<EIDEventVisa>|
Then I should see card attached to currently opened operator report with all following label/value pair(s): |Traveller|<GOVINTEventName>|Booking Reference|<GOVINTEventBookRef>|Carrier Name|<GOVINTEventCarrier>|
Then I should see card attached to currently opened operator report with all following label/value pair(s): |G+ Account Display Name|<OSINTEventName>|G+ Account ID|<OSINTEventID>|
Then I should see card attached to currently opened operator report with all following label/value pair(s): |Subscriber Phone|<SIGINTEntityPhone>|Subscriber Full Name|<SIGINTEntityName>|Subscriber IMSI|<SIGINTEntityIMSI>|
Then I should see card attached to currently opened operator report with all following label/value pair(s): |UID Number|<EIDEntityUIDNumber>|EID Number|<EIDEntityEIDNumber>|Gender|<EIDEntityGender>|
Then I should see card attached to currently opened operator report with all following label/value pair(s): |Full Name|<GOVINYEntityName>|UID Number|<GOVINTEntityUIDNumber>|EID Number|<GOVINTEntityEIDNumber>|
Then I should see card attached to currently opened operator report with all following label/value pair(s): |G+ Account Display Name|<OSINTEntityName>|G+ Account ID|<OSINTEntityID>|

When I delete operator report which is currently opened
When I select file with Name (<FileName>) in the CB Finder
When I delete file which is currently selected in the CB Finder

Examples:
|FileName     |FileCalssif |FileOrgUnit  |ReportClassif|ReportOrgUnit|ReportSubj      |SIGINTEventIMSI|SIGINTEventPhone|EIDEventName|EIDEventUIDNumber|EIDEventVisa    |GOVINTEventName|GOVINTEventBookRef|GOVINTEventCarrier|OSINTEventName |OSINTEventID         |ProfilerEntityName  |ProfilerEntityClas|ProfilerEntityOU|ProfilerEntityCat|ProfilerEntityAP|ProfilerEntityTS|ProfilerEntityAU                              |ProfilerEntityTSI|SIGINTEntityPhone|SIGINTEntityName |SIGINTEntityIMSI|EIDEntityUIDNumber|EIDEntityEIDNumber|EIDEntityGender|GOVINYEntityName|GOVINTEntityUIDNumber|GOVINTEntityEIDNumber|GOVINTEntityGender|OSINTEntityName  |OSINTEntityID        |
|ForFullReportTest|CONFIDENTIAL|Admins|SECRET       |Admins       |For report test purpose|4534525345345  |245345435       |Bobby Case  |36377983960994981|5397127033150134|Olivia QE Essa |ZV77RJ            |United Airlines   |Kristina Turner|103446745626515478415|Target for auto test|TOP SECRET-CIO    |Admins          |POI              |High            |Active          |{current_month}/{current_day}+1/{current_year}|Medium 30        |14993646179      |Brandon Rodriguez|780968629676446 |30104409982340    |306727437333104   |601744527521   |Jack QE Essa    |9987481              |CG3OTSPED3LV84G0D9YX |Female            |Southpark Seafood|115558179973836572416|



