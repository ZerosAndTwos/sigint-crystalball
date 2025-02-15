Meta:
@UI
@component Information Management
@reports

Lifecycle:
Before:
Scope: SCENARIO
Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_analyst.table


Scenario: Report: user is able to create and delete new operator report from CB Search
Meta: @L0  @test  C83415
Given I navigate to Search
When I enter search criteria (<SIGINTEventIMSI>) on the Search page
Given I open Search Filter on the Search page
Given I set Source Type to SIGINT on the Search Filter page
Given load story ../aux-search-filter-stories/auxSearchFilterShowAllEvents.story
Given I set Data Source to (|Manual|) on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
When I open Card View

Given I select all cards on the Search page
When I create new report for selected items
Given I save Report number for the operator report in the context
When I set Classification (<ReportClassifInit>) in operator report
When I set Created For (<ReportCreatedForInit>) in operator report
When I set File Name/Case Name (|<FileNameDefault>|) in operator report
When I set Organization Units (|<ReportOrgUnitInit>|) in operator report
When I set Subject (<ReportSubject>) in operator report
When I set Description (|<ReportDescrInit>|) in operator report
When I set Considerations (|<ReportConsidInit>|) in operator report
When I set Recommendations (|<ReportRecommInit>|) in operator report
When I set Notes (|<ReportNotesInit>|) in operator report
When I save currently opened operator report as draft

Given I navigate to CB Finder
When I select file with Name (<FileNameDefault>) in the CB Finder
When I open operator report with Subject (<ReportSubject>) in file/case which is currently selected in the CB Finder
Given I Collapse the CB Finder view
Then I should see that currently opened operator report has status (Initial Draft)
Then I should see Edit button in operator report
Then I should see Submit for Review button
Given load story ../aux-report-and-request-stories/auxCheckSingleValueReportFields.story with example table:
|ExpectedClassification|ExpectedCreatedFor    |ExpectedSubject |ExpectedDescription|ExpectedConsiderations|ExpectedRecommendations|ExpectedNotes    |
|<ReportClassifInit>   |<ReportCreatedForInit>|<ReportSubject>|<ReportDescrInit>  |<ReportConsidInit>    |<ReportRecommInit>     |<ReportNotesInit>|

Then I should see card attached to currently opened operator report with all following label/value pair(s): |From Phone Number|<SIGINTEventPhone>|From IMSI|<SIGINTEventIMSI>|

When I delete operator report which is currently opened

Given I Sign Out


Examples:
data/ReportL0.table



Scenario: Report - User is able to attach record to existing report
Meta: @L0  @test  C83410
Given I navigate to Search
When I enter search criteria (<SIGINTEventIMSI>) on the Search page
Given I open Search Filter on the Search page
Given I set Source Type to SIGINT on the Search Filter page
Given load story ../aux-search-filter-stories/auxSearchFilterShowAllEvents.story
Given I set Data Source to (|Manual|) on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
When I open Card View

Given I select all cards on the Search page

When I add selected items to the existing report (<ExtReportSubj>)
When I save currently opened operator report as draft




Given I navigate to CB Finder
When I select file with Name (<FileNameDefault>) in the CB Finder
When I open operator report with Subject (<ExtReportSubj>) in file/case which is currently selected in the CB Finder
Given I Collapse the CB Finder view
Then I should see that currently opened operator report has status (Initial Draft)
Then I should see Edit button in operator report
Then I should see Submit for Review button
Given load story ../aux-report-and-request-stories/auxCheckSingleValueReportFields.story with example table:
|ExpectedClassification|ExpectedCreatedFor    |ExpectedSubject |ExpectedDescription|ExpectedConsiderations|ExpectedRecommendations|ExpectedNotes    |
|<ReportClassifInit>   |<ReportCreatedForInit>|<ReportSubject>|<ReportDescrInit>  |<ReportConsidInit>    |<ReportRecommInit>     |<ReportNotesInit>|

Then I should see card attached to currently opened operator report with all following label/value pair(s): |From Phone Number|<SIGINTEventPhone>|From IMSI|<SIGINTEventIMSI>|


Given I Sign Out


Examples:
data/ReportL0.table


Scenario:Report - User is able to export report
Meta: @L100  @test  C83470
Given I navigate to Search
When I enter search criteria (<ReportSubjectSearch>) on the Search page
Given I open Search Filter on the Search page
Given I set Source Type to Documents on the Search Filter page
Given I set Record Type to (|<RecordType>|) on the Search Filter page
Given I set Status Type to (|<StatusType>|) on the Search Filter page
Given I set File or Case Type to (|<MasterReportFile>|) on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Given I select all cards on the Search page
When I export selected Document
Then I verify the downloaded operator report for (<PDFValues>)








Examples:
data/MasterReport.table

Scenario: User is able to create and edit a report
Given I navigate to Search
When I enter search criteria (<GOVINTEventBookRef>) on the Search page
Given I open Search Filter on the Search page
Given I set period range Last 90 days as Event Time Period on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
When I open Card View

When I select all GOVINT event cards having identifiers: |<GOVINTEventName>|<GOVINTEventCarrier>|
When I create new report for selected items
Then I should see Submit for Review button
When I set Classification (<ReportClassifInit>) in operator report
When I set Created For (<ReportCreatedForInit>) in operator report
When I set File Name/Case Name (|<FileName>|) in operator report
When I set Organization Units (|<ReportOrgUnitInit>|) in operator report
When I set Subject (<ReportSubject>) in operator report
When I set Description (|<ReportDescrInit>|) in operator report
When I set Considerations (|<ReportConsidInit>|) in operator report
When I set Recommendations (|<ReportRecommInit>|) in operator report
When I set Notes (|<ReportNotesInit>|) in operator report
When I save currently opened operator report as draft

Given I navigate to CB Finder
When I select file with Name (<FileName>) in the CB Finder
When I open operator report with Subject (<ReportSubject>) in file/case which is currently selected in the CB Finder
Given I Collapse the CB Finder view
Given load story ../aux-report-and-request-stories/auxCheckSingleValueReportFields.story with example table:
|ExpectedClassification|ExpectedCreatedFor    |ExpectedSubject |ExpectedDescription|ExpectedConsiderations|ExpectedRecommendations|ExpectedNotes    |
|<ReportClassifInit>   |<ReportCreatedForInit>|<ReportSubject>|<ReportDescrInit>  |<ReportConsidInit>    |<ReportRecommInit>     |<ReportNotesInit>|
Then I should see Organization Units (|<ReportOrgUnitInit>|) in operator report
Then I should see File Name/Case Name(s) (|<FileName> |) in operator report

When I start edit of operator report which is currently opened
When I set Classification (<ReportClassifUpd>) in operator report
When I save currently opened operator report as draft
Given load story ../aux-report-and-request-stories/auxCheckSingleValueReportFields.story with example table:
|ExpectedClassification|ExpectedCreatedFor    |ExpectedSubject |ExpectedDescription|ExpectedConsiderations|ExpectedRecommendations|ExpectedNotes    |
|<ReportClassifUpd>    |<ReportCreatedForInit>|<ReportSubject>|<ReportDescrInit>  |<ReportConsidInit>    |<ReportRecommInit>     |<ReportNotesInit>|
Then I should see Organization Units (|<ReportOrgUnitInit>|) in operator report
Then I should see File Name/Case Name(s) (|<FileName> |) in operator report

When I start edit of operator report which is currently opened
When I set Created For (<ReportCreatedForUpd>) in operator report
When I save currently opened operator report as draft
Given load story ../aux-report-and-request-stories/auxCheckSingleValueReportFields.story with example table:
|ExpectedClassification|ExpectedCreatedFor   |ExpectedSubject |ExpectedDescription|ExpectedConsiderations|ExpectedRecommendations|ExpectedNotes    |
|<ReportClassifUpd>    |<ReportCreatedForUpd>|<ReportSubject>|<ReportDescrInit>  |<ReportConsidInit>    |<ReportRecommInit>     |<ReportNotesInit>|
Then I should see Organization Units (|<ReportOrgUnitInit>|) in operator report
Then I should see File Name/Case Name(s) (|<FileName> |) in operator report

When I start edit of operator report which is currently opened
When I set Organization Units (|<ReportOrgUnitUpd>|) in operator report
When I save currently opened operator report as draft
Given load story ../aux-report-and-request-stories/auxCheckSingleValueReportFields.story with example table:
|ExpectedClassification|ExpectedCreatedFor   |ExpectedSubject |ExpectedDescription|ExpectedConsiderations|ExpectedRecommendations|ExpectedNotes    |
|<ReportClassifUpd>    |<ReportCreatedForUpd>|<ReportSubject>|<ReportDescrInit>  |<ReportConsidInit>    |<ReportRecommInit>     |<ReportNotesInit>|
Then I should see Organization Units (|<ReportOrgUnitInit>|<ReportOrgUnitUpd>|) in operator report
Then I should see File Name/Case Name(s) (|<FileName> |) in operator report

When I start edit of operator report which is currently opened
When I set Subject (<ReportSubjUpd>) in operator report
When I save currently opened operator report as draft
Given load story ../aux-report-and-request-stories/auxCheckSingleValueReportFields.story with example table:
|ExpectedClassification|ExpectedCreatedFor   |ExpectedSubject|ExpectedDescription|ExpectedConsiderations|ExpectedRecommendations|ExpectedNotes    |
|<ReportClassifUpd>    |<ReportCreatedForUpd>|<ReportSubjUpd>|<ReportDescrInit>  |<ReportConsidInit>    |<ReportRecommInit>     |<ReportNotesInit>|
Then I should see Organization Units (|<ReportOrgUnitInit>|<ReportOrgUnitUpd>|) in operator report
Then I should see File Name/Case Name(s) (|<FileName> |) in operator report

When I start edit of operator report which is currently opened
When I set Description (|<ReportDescrUpd>|) in operator report
When I save currently opened operator report as draft
Given load story ../aux-report-and-request-stories/auxCheckSingleValueReportFields.story with example table:
|ExpectedClassification|ExpectedCreatedFor   |ExpectedSubject|ExpectedDescription|ExpectedConsiderations|ExpectedRecommendations|ExpectedNotes    |
|<ReportClassifUpd>    |<ReportCreatedForUpd>|<ReportSubjUpd>|<ReportDescrUpd>   |<ReportConsidInit>    |<ReportRecommInit>     |<ReportNotesInit>|
Then I should see Organization Units (|<ReportOrgUnitInit>|<ReportOrgUnitUpd>|) in operator report
Then I should see File Name/Case Name(s) (|<FileName> |) in operator report

When I start edit of operator report which is currently opened
When I set Considerations (|<ReportConsidUpd>|) in operator report
When I save currently opened operator report as draft
Given load story ../aux-report-and-request-stories/auxCheckSingleValueReportFields.story with example table:
|ExpectedClassification|ExpectedCreatedFor   |ExpectedSubject|ExpectedDescription|ExpectedConsiderations|ExpectedRecommendations|ExpectedNotes    |
|<ReportClassifUpd>    |<ReportCreatedForUpd>|<ReportSubjUpd>|<ReportDescrUpd>   |<ReportConsidUpd>     |<ReportRecommInit>     |<ReportNotesInit>|
Then I should see Organization Units (|<ReportOrgUnitInit>|<ReportOrgUnitUpd>|) in operator report
Then I should see File Name/Case Name(s) (|<FileName> |) in operator report

When I start edit of operator report which is currently opened
When I set Recommendations (|<ReportRecommUpd>|) in operator report
When I save currently opened operator report as draft
Given load story ../aux-report-and-request-stories/auxCheckSingleValueReportFields.story with example table:
|ExpectedClassification|ExpectedCreatedFor   |ExpectedSubject|ExpectedDescription|ExpectedConsiderations|ExpectedRecommendations|ExpectedNotes    |
|<ReportClassifUpd>    |<ReportCreatedForUpd>|<ReportSubjUpd>|<ReportDescrUpd>   |<ReportConsidUpd>     |<ReportRecommUpd>      |<ReportNotesInit>|
Then I should see Organization Units (|<ReportOrgUnitInit>|<ReportOrgUnitUpd>|) in operator report
Then I should see File Name/Case Name(s) (|<FileName> |) in operator report

When I start edit of operator report which is currently opened
When I set Notes (|<ReportNotesUpd>|) in operator report
When I save currently opened operator report as draft
Given load story ../aux-report-and-request-stories/auxCheckSingleValueReportFields.story with example table:
|ExpectedClassification|ExpectedCreatedFor   |ExpectedSubject|ExpectedDescription|ExpectedConsiderations|ExpectedRecommendations|ExpectedNotes   |
|<ReportClassifUpd>    |<ReportCreatedForUpd>|<ReportSubjUpd>|<ReportDescrUpd>   |<ReportConsidUpd>     |<ReportRecommUpd>      |<ReportNotesUpd>|
Then I should see Organization Units (|<ReportOrgUnitInit>|<ReportOrgUnitUpd>|) in operator report
Then I should see File Name/Case Name(s) (|<FileName> |) in operator report

When I start edit of operator report which is currently opened
When I set Classification (<ReportClassifInit>) in operator report
When I set Created For (<ReportCreatedForInit>) in operator report
When I set Subject (<ReportSubject>) in operator report
When I set Description (|<ReportDescrInit>|) in operator report
When I set Considerations (|<ReportConsidInit>|) in operator report
When I set Recommendations (|<ReportRecommInit>|) in operator report
When I set Notes (|<ReportNotesInit>|) in operator report
When I save currently opened operator report as draft
Given load story ../aux-report-and-request-stories/auxCheckSingleValueReportFields.story with example table:
|ExpectedClassification|ExpectedCreatedFor    |ExpectedSubject |ExpectedDescription|ExpectedConsiderations|ExpectedRecommendations|ExpectedNotes    |
|<ReportClassifInit>   |<ReportCreatedForInit>|<ReportSubject>|<ReportDescrInit>  |<ReportConsidInit>    |<ReportRecommInit>     |<ReportNotesInit>|
Then I should see Organization Units (|<ReportOrgUnitInit>|<ReportOrgUnitUpd>|) in operator report
Then I should see File Name/Case Name(s) (|<FileName>|) in operator report
Given I Expand the CB Finder view
Given load story ../aux-after-stories/auxDeleteReport.story with example table:
data/Report.table
Given load story ../aux-after-stories/auxDeleteReport.story with example table:
data/Report.table


Examples:
data/Report.table



Scenario: Report approval and Unassign workflow

Given I navigate to Search
When I enter search criteria (<SIGINTEventIMSI>) on the Search page
Given I open Search Filter on the Search page
Given I set period range Last 90 days as Event Time Period on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
When I open Card View

When I select all SIGINT event cards having identifiers: |<SIGINTEventPhone>|
When I create new report for selected items
Given I save Report number for the operator report in the context
When I set Classification (<ReportClassifInit>) in operator report
When I set Created For (<ReportCreatedForInit>) in operator report
When I set File Name/Case Name (|<FileNameDefault>|) in operator report
When I set Organization Units (|<ReportOrgUnitInit>|) in operator report
When I set Subject (<ReportSubject>) in operator report
When I set Description (|<ReportDescrInit>|) in operator report
When I set Considerations (|<ReportConsidInit>|) in operator report
When I set Recommendations (|<ReportRecommInit>|) in operator report
When I set Notes (|<ReportNotesInit>|) in operator report
When I save currently opened operator report as draft

Given I navigate to CB Finder
When I select file with Name (<FileNameDefault>) in the CB Finder
When I open report with Report number from the context in file which is currently selected in the CB Finder

Given I Collapse the CB Finder view

Then I should see Edit button in operator report
When I Submit For Review the operator report which is currently opened
When I enter routing (Submitting the report ) for the operator report
When I route the operator report
Then I should see that currently opened operator report has status (Awaiting Review)
Then I should see Cancel button in operator report

Given I Sign Out

Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_manager.table

Given I navigate to CB Finder
Given I Expand the CB Finder view
When I select file with Name (<FileNameDefault>) in the CB Finder
When I open report with Report number from the context in file which is currently selected in the CB Finder
Given I Collapse the CB Finder view
Then I should see that currently opened operator report has status (Awaiting Review)
When I Take Ownership of the operator report which is currently opened
Then I should see that currently opened operator report has status (Under Review)
Then I should see Reject button in operator report
Then I should see Return to Author button in operator report
Then I should see Approve button in operator report
Then I should see Unassign button in operator report
Then I should see Edit button in operator report

When I Unassign the operator report which is currently opened
When I enter routing (<RoutingCommnets>) for the operator report
When I route the operator report
Then I should see that currently opened operator report has status (Awaiting Review)

When I Take Ownership of the operator report which is currently opened

Then I should see that currently opened operator report has status (Under Review)
Then I should see Reject button in operator report
Then I should see Return to Author button in operator report
Then I should see Approve button in operator report
Then I should see Unassign button in operator report
Then I should see Edit button in operator report

When I Return to Author the operator report which is currently opened
When I enter routing (<RoutingCommnets>) for the operator report
When I route the operator report
Then I should see that currently opened operator report has status (Returned for Revision)

Given I Sign Out
Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_analyst.table

Given I navigate to CB Finder
Given I Expand the CB Finder view
When I select file with Name (<FileNameDefault>) in the CB Finder
When I open report with Report number from the context in file which is currently selected in the CB Finder
Given I Collapse the CB Finder view
Then I should see that currently opened operator report has status (Returned for Revision)

When I Submit For Review the operator report which is currently opened
When I enter routing (<RoutingCommnets>) for the operator report
When I route the operator report
Then I should see that currently opened operator report has status (Awaiting Review)
Then I should see Cancel button in operator report

Given I Sign Out
Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_manager.table

Given I navigate to CB Finder
Given I Expand the CB Finder view
When I select file with Name (<FileNameDefault>) in the CB Finder
When I open report with Report number from the context in file which is currently selected in the CB Finder
Given I Collapse the CB Finder view
Then I should see that currently opened operator report has status (Awaiting Review)
When I Take Ownership of the operator report which is currently opened
Then I should see that currently opened operator report has status (Under Review)

Then I should see Reject button in operator report
Then I should see Return to Author button in operator report
Then I should see Approve button in operator report
Then I should see Unassign button in operator report
Then I should see Edit button in operator report
When I Approve the operator report which is currently opened
Then I should see that currently opened operator report has status (Approved)

Given I Sign Out
Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_analyst.table


Given I navigate to CB Finder
Given I Expand the CB Finder view
When I select file with Name (<FileNameDefault>) in the CB Finder
When I open report with Report number from the context in file which is currently selected in the CB Finder
Given I Collapse the CB Finder view
Then I should see that currently opened operator report has status (Approved)

Examples:
data/Report.table



Scenario: Report approval workflow
Meta:@devsmoke
@issue CB2-545
Given I navigate to Search
When I enter search criteria (<SIGINTEventIMSI>) on the Search page
Given I open Search Filter on the Search page
Given load story ../aux-search-filter-stories/auxSearchFilterShowAllEvents.story
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
When I open Card View

When I select all SIGINT event cards having identifiers: |<SIGINTEventPhone>|
When I create new report for selected items
Given I save Report number for the operator report in the context
When I set Classification (<ReportClassifInit>) in operator report
When I set Created For (<ReportCreatedForInit>) in operator report
When I set File Name/Case Name (|<FileNameDefault>|) in operator report
When I set Subject (<ReportSubject>) in operator report
When I set Organization Units (|<ReportOrgUnitInit>|) in operator report

When I set Description (|<ReportDescrInit>|) in operator report
When I set Considerations (|<ReportConsidInit>|) in operator report
When I set Recommendations (|<ReportRecommInit>|) in operator report
When I set Notes (|<ReportNotesInit>|) in operator report
When I save currently opened operator report as draft

Given I navigate to CB Finder
When I select file with Name (<FileNameDefault>) in the CB Finder
When I open report with Report number from the context in file which is currently selected in the CB Finder

Given I Collapse the CB Finder view

Then I should see Edit button in operator report
When I Submit For Review the operator report which is currently opened
When I enter routing (Submitting the report ) for the operator report
When I route the operator report

Given I Sign Out

Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_manager.table

Given I navigate to CB Finder
Given I Expand the CB Finder view
When I select file with Name (<FileNameDefault>) in the CB Finder
When I open report with Report number from the context in file which is currently selected in the CB Finder
Given I Collapse the CB Finder view
Then I should see that currently opened operator report has status (Awaiting Review)
When I Take Ownership of the operator report which is currently opened
Given I navigate to CB Finder
Given I Expand the CB Finder view
When I select file with Name (<FileNameDefault>) in the CB Finder
When I open report with Report number from the context in file which is currently selected in the CB Finder
Then I should see that currently opened operator report has status (Under Review)
Then I should see Reject button in operator report
Then I should see Return to Author button in operator report
Then I should see Approve button in operator report
Then I should see Unassign button in operator report
Then I should see Edit button in operator report

When I Approve the operator report which is currently opened
When I enter routing (Submitting the report for approval ) for the operator report
When I route the operator report
Then I should see that currently opened operator report has status (Approved)

Given I Sign Out
Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_analyst.table


Given I navigate to CB Finder
Given I Expand the CB Finder view
When I select file with Name (<FileNameDefault>) in the CB Finder
When I open report with Report number from the context in file which is currently selected in the CB Finder
Given I Collapse the CB Finder view
Then I should see that currently opened operator report has status (Approved)

Examples:
data/Report.table




Scenario: Report reject workflow

Given I navigate to Search
When I enter search criteria (<GOVINTEventBookRef>) on the Search page
Given I open Search Filter on the Search page
Given I set period range Last 90 days as Event Time Period on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
When I open Card View


When I select all GOVINT event cards having identifiers: |<GOVINTEventName>|<GOVINTEventCarrier>|
When I create new report for selected items
Given I save Report number for the operator report in the context
When I set Classification (<ReportClassifInit>) in operator report
When I set Created For (<ReportCreatedForInit>) in operator report
When I set File Name/Case Name (|<FileNameDefault>|) in operator report
When I set Organization Units (|<ReportOrgUnitInit>|) in operator report
When I set Subject (<ReportSubject>) in operator report
When I set Description (|<ReportDescrInit>|) in operator report
When I set Considerations (|<ReportConsidInit>|) in operator report
When I set Recommendations (|<ReportRecommInit>|) in operator report
When I set Notes (|<ReportNotesInit>|) in operator report
When I Submit For Review the operator report which is currently opened
When I enter routing (This is for auto report submit) for the operator report

Given I Sign Out

Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_manager.table

Given I navigate to CB Finder
When I select file with Name (<CaseName>) in the CB Finder
When I open report with Report number from the context in file which is currently selected in the CB Finder
Given I Collapse the CB Finder view
Then I should see that currently opened operator report has status (Awaiting Review)
When I Take Ownership of the operator report which is currently opened
Then I should see that currently opened operator report has status (Under Review)
Then I should see Reject button in operator report
Then I should see Return to Author button in operator report
Then I should see Approve button in operator report
Then I should see Unassign button in operator report
Then I should see Edit button in operator report

When I Reject the operator report which is currently opened

When I enter routing (<RoutingCommnets>) for the operator report
When I route the operator report
Then I should see that currently opened operator report has status (Rejected)

Given I Sign Out
Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_analyst.table


Given I navigate to CB Finder
When I select file with Name (<CaseName>) in the CB Finder
When I open report with Report number from the context in file which is currently selected in the CB Finder
Given I Collapse the CB Finder view
Then I should see that currently opened operator report has status (Rejected)


Examples:
data/Report.table



Scenario: Report cancel workflow
Meta:@shareports
Given I navigate to Search
When I enter search criteria (<GOVINTEventBookRef>) on the Search page
Given I open Search Filter on the Search page
Given load story ../aux-search-filter-stories/auxSearchFilterShowAllEvents.story
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
When I open Card View

When I select all GOVINT event cards having identifiers: |<GOVINTEventName>|<GOVINTEventCarrier>|
When I create new report for selected items
Given I save Report number for the operator report in the context
When I set Classification (<ReportClassifInit>) in operator report
When I set Created For (<ReportCreatedForInit>) in operator report
When I set File Name/Case Name (|<FileNameDefault>|) in operator report
When I set Organization Units (|<ReportOrgUnitInit>|) in operator report
When I set Subject (<ReportSubject>) in operator report
When I set Description (|<ReportDescrInit>|) in operator report
When I set Considerations (|<ReportConsidInit>|) in operator report
When I set Recommendations (|<ReportRecommInit>|) in operator report
When I set Notes (|<ReportNotesInit>|) in operator report
When I Submit For Review the operator report which is currently opened
When I enter routing (Submitting Report) for the operator report
When I Cancel the operator report which is currently opened
Then I should see that currently opened operator report has status (Cancelled)
When I enter routing (canceled for test) for the operator report

Examples:
data/Report.table


Scenario: Report test
Given I navigate to Search
When I enter search criteria (<GOVINTEventBookRef>) on the Search page
Given I open Search Filter on the Search page
Given I set period range Last 90 days as Event Time Period on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
When I open Card View

When I select all GOVINT event cards having identifiers: |<GOVINTEventName>|<GOVINTEventCarrier>|
When I create new report for selected items
Given I save Report number for the operator report in the context
When I set Classification (<ReportClassifInit>) in operator report
When I set Created For (<ReportCreatedForInit>) in operator report
When I set File Name/Case Name (|<FileNameDefault>|) in operator report
When I set Organization Units (|<ReportOrgUnitInit>|) in operator report
When I set Subject (<ReportSubject>) in operator report
When I set Description (|<ReportDescrInit>|) in operator report
When I set Considerations (|<ReportConsidInit>|) in operator report
When I set Recommendations (|<ReportRecommInit>|) in operator report
When I set Notes (|<ReportNotesInit>|) in operator report
When I save currently opened operator report as draft

Then I close the operator report

Examples:
data/Report.table