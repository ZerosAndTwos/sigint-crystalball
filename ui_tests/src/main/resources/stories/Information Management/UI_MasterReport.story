Meta:
@UI
@component Information Management
@masterreport
@wip

Lifecycle:

Before:
Scope: SCENARIO
Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_manager.table

Scenario: Report: user is able to create new master report from CB Search
Meta: @L0  @test  C83467
Given I navigate to Search
Given I open Search Filter on the Search page
Given I set Source Type to Documents on the Search Filter page
Given I set Record Type to (|<RecordType>|) on the Search Filter page
Given I set Status Type to (|<StatusType>|) on the Search Filter page
Given I set File or Case Type to (|<MasterReportFile>|) on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Given I select all cards on the Search page
When I create new master report for selected items
Then I should see Submit for Review button
Then I should see Originating Reports column header as (<ColReportHeaders>) in master report
Then I should see Originating Reports section heading as (<OrgRepHeading>) in master report
Then I should see Originating Reports values as (<ReportValues>) in master report
When I set Classification (<ReportClassifInit>) in master report
When I set Created For (<ReportCreatedForInit>) in master report
When I set File Name/Case Name (|<FileName_master>|) in master report
When I set Organization Units (|<ReportOrgUnitInit>|) in master report
When I set Subject (<ReportSubject>) in master report
When I set Overview (|<ReportOverview>|) in master report
When I set Result (|<ReportResult>|) in master report
When I save currently opened master report as draft
Given I navigate to CB Finder
When I select file with Name (<FileName_master>) in the CB Finder
When I open operator report with Subject (<ReportSubject>) in file/case which is currently selected in the CB Finder
Given I Collapse the CB Finder view
Then I should see Submit for Review button
Then I should see Edit button in master report
Then I should see Classification (<ReportClassifInit>) in master report
Then I should see Created For (<ReportCreatedForInit>) in master report
Then I should see Subject (<ReportSubject>) in master report
Then I should see Overview (<ReportOverview>) in master report
Then I should see Results (<ReportResult>) in master report
Then I should see Originating Reports section heading as (<OrgRepHeading>) in master report
Then I should see Originating Reports column header as (<ColReportHeaders>) in master report
Then I should see Originating Reports values as (<ReportValues>) in master report
Then I should see Organization Units (|<ReportOrgUnitInit>|) in master report
Then I should see File Name/Case Name(s) (|<FileName_master> |) in master report
When I delete master report which is currently opened

Examples:
data/MasterReport.table





Scenario: User is able to create master report from search page
Given I navigate to Search
Given I open Search Filter on the Search page
Given I set Source Type to Documents on the Search Filter page
Given I set Record Type to (|<RecordType>|) on the Search Filter page
Given I set Status Type to (|<StatusType>|) on the Search Filter page
Given I set File or Case Type to (|<MasterReportFile>|) on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Given I select all cards on the Search page
When I create new master report for selected items
Then I should see Submit for Review button
Then I should see Originating Reports column header as (<ColReportHeaders>) in master report
Then I should see Originating Reports section heading as (<OrgRepHeading>) in master report
Then I should see Originating Reports values as (<ReportValues>) in master report
When I set Classification (<ReportClassifInit>) in master report
When I set Created For (<ReportCreatedForInit>) in master report
When I set File Name/Case Name (|<FileName>|) in master report
When I set Organization Units (|<ReportOrgUnitInit>|) in master report
When I set Subject (<ReportSubject>) in master report
When I set Overview (|<ReportOverview>|) in master report
When I set Result (|<ReportResult>|) in master report
When I set Related Files/Cases (|<RelatedFileName>|) in master report
When I save currently opened master report as draft




Examples:
data/MasterReport.table



Scenario: User is able create delete and edit master report
Meta:@master


Given load story ../aux-before-stories/auxCreateFile.story with example table:
data/MasterReport.table
Given I navigate to Search
Given I open Search Filter on the Search page
Given I set Source Type to Documents on the Search Filter page
Given I set Record Type to (|<RecordType>|) on the Search Filter page
Given I set Status Type to (|<StatusType>|) on the Search Filter page
Given I set File or Case Type to (|<MasterReportFile>|) on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Given I select all cards on the Search page
When I create new master report for selected items
Then I should see Submit for Review button
Then I should see Originating Reports column header as (<ColReportHeaders>) in master report
Then I should see Originating Reports section heading as (<OrgRepHeading>) in master report
Then I should see Originating Reports values as (<ReportValues>) in master report
When I set Classification (<ReportClassifInit>) in master report
When I set Created For (<ReportCreatedForInit>) in master report
When I set File Name/Case Name (|<FileName>|) in master report
When I set Organization Units (|<ReportOrgUnitInit>|) in master report
When I set Subject (<ReportSubject>) in master report
When I set Overview (|<ReportOverview>|) in master report
When I set Result (|<ReportResult>|) in master report
When I set Related Files/Cases (|<RelatedFileName>|) in master report
When I save currently opened master report as draft
Given I navigate to CB Finder
When I select file with Name (<FileName>) in the CB Finder
When I open operator report with Subject (<ReportSubject>) in file/case which is currently selected in the CB Finder
Given I Collapse the CB Finder view
Then I should see Submit for Review button
Then I should see Edit button in master report
Then I should see Classification (<ReportClassifInit>) in master report
Then I should see Created For (<ReportCreatedForInit>) in master report
Then I should see Subject (<ReportSubject>) in master report
Then I should see Overview (<ReportOverview>) in master report
Then I should see Results (<ReportResult>) in master report
Then I should see Originating Reports section heading as (<OrgRepHeading>) in master report
Then I should see Originating Reports column header as (<ColReportHeaders>) in master report
Then I should see Originating Reports values as (<ReportValues>) in master report
Then I should see Organization Units (|<ReportOrgUnitInit>|) in master report
Then I should see File Name/Case Name(s) (|<FileName> |) in master report
Then I should see Related Files/Cases (|<RelatedFileName> |) in master report


When I start edit of master report which is currently opened
When I set Classification (<ReportClassifUpd>) in master report
When I set Created For (<ReportCreatedForUpd>) in master report
When I set Subject (<ReportSubjUpd>) in master report
When I set Overview (|<ReportOverviewUpd>|) in master report
When I set Result (|<ReportResultUpd>|) in master report
When I set Related Files/Cases (|<RelatedFileNameUpd>|) in master report
When I set File Name/Case Name (|<FileNameUpd>|) in master report
When I save currently opened master report as draft
Then I should see Submit for Review button
Then I should see Edit button in master report
Then I should see Classification (<ReportClassifUpd>) in master report
Then I should see Created For (<ReportCreatedForUpd>) in master report
Then I should see Subject (<ReportSubjUpd>) in master report
Then I should see Overview (<ReportOverviewUpd>) in master report
Then I should see Results (<ReportResultUpd>) in master report
Then I should see Originating Reports section heading as (<OrgRepHeading>) in master report
Then I should see Originating Reports column header as (<ColReportHeaders>) in master report
Then I should see Originating Reports values as (<ReportValues>) in master report
Then I should see File Name/Case Name(s) (|<FileName> |<FileNameUpd>|) in master report
Then I should see Related Files/Cases (|<RelatedFileName> |<RelatedFileNameUpd>|) in master report
When I delete operator report which is currently opened
Given I navigate to CB Finder
Given I Expand the CB Finder view

When I select file with Name (<FileName>) in the CB Finder
When I delete file which is currently selected in the CB Finder

Examples:
data/MasterReport.table







Scenario: Verify approval workflow for master report
Meta:@master123

Given I navigate to Search
Given I open Search Filter on the Search page
Given I set Source Type to Documents on the Search Filter page
Given I set Record Type to (|<RecordType>|) on the Search Filter page
Given I set Status Type to (|<StatusType>|) on the Search Filter page
Given I set File or Case Type to (|<MasterReportFile>|) on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Given I select all cards on the Search page
When I create new master report for selected items
Then I should see Submit for Review button
Then I should see Originating Reports column header as (<ColReportHeaders>) in master report
Then I should see Originating Reports section heading as (<OrgRepHeading>) in master report
Then I should see Originating Reports values as (<ReportValues>) in master report
When I set Classification (<ReportClassifInit>) in master report
When I set Created For (<ReportCreatedForInit>) in master report
When I set File Name/Case Name (|<FileName_master>|) in master report
When I set Organization Units (|<ReportOrgUnitInit>|) in master report
When I set Subject (<ReportSubject>) in master report
When I set Overview (|<ReportOverview>|) in master report
When I set Result (|<ReportResult>|) in master report
When I set Related Files/Cases (|<RelatedFileName>|) in master report
When I save currently opened master report as draft
Given I navigate to CB Finder
When I select file with Name (<FileName_master>) in the CB Finder
When I open operator report with Subject (<ReportSubject>) in file/case which is currently selected in the CB Finder
Given I Collapse the CB Finder view
Then I should see Submit for Review button
When I Submit For Review the master report which is currently opened
When I enter routing (Submitting the report ) for the master report
When I route the master report
Then I should see that currently opened master report has status (Awaiting Review)
Then I should see Cancel button in master report
Then I should see Edit button in master report
Then I should see Assign button in master report
When I Take Ownership of the master report which is currently opened


Examples:
data/MasterReport.table













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
When I create new report for selected items
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
