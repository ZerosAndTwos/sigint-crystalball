Meta:
@UI
@component Information Management
@EditReport

Lifecycle:
Before:
Scope: SCENARIO
Given load story ../aux-main-stories/auxSignIn.story

Scenario: scenario description
Given I navigate to CB Finder
Given I start creation of new file in the CB Finder
Given I set Name (<FileName>) for new file in the CB Finder
Given I set Classification (<FileCalssif>) for new file in the CB Finder
Given I set Organization Units (|<FileOrgUnit>|) for new file in the CB Finder
Given I save new file created in the CB Finder

Given I navigate to Search
When I enter search criteria (<GOVINTEventName>) on the Search page
Given I setup Search Authorization

When I open Card View

When I select all GOVINT event cards having identifiers: |<GOVINTEventBookRef>|<GOVINTEventCarrier>|
When I create new report for selected items
When I set Classification (<ReportClassifInit>) in operator report
When I set Created For (<ReportCreatedForInit>) in operator report
When I set File Name/Case Name (|<FileName>|) in operator report
When I set Organization Units (|<ReportOrgUnitInit>|) in operator report
When I set Subject (<ReportSubjInit>) in operator report
When I set Description (|<ReportDescrInit>|) in operator report
When I set Considerations (|<ReportConsidInit>|) in operator report
When I set Recommendations (|<ReportRecommInit>|) in operator report
When I set Notes (|<ReportNotesInit>|) in operator report
When I save currently opened operator report as draft

Given I navigate to CB Finder
When I select file with Name (<FileName>) in the CB Finder
When I open operator report with Subject (<ReportSubjInit>) in file/case which is currently selected in the CB Finder

Given load story ../aux-report-and-request-stories/auxCheckSingleValueReportFields.story with example table:
|ExpectedClassification|ExpectedCreatedFor    |ExpectedSubject |ExpectedDescription|ExpectedConsiderations|ExpectedRecommendations|ExpectedNotes    |
|<ReportClassifInit>   |<ReportCreatedForInit>|<ReportSubjInit>|<ReportDescrInit>  |<ReportConsidInit>    |<ReportRecommInit>     |<ReportNotesInit>|
Then I should see Organization Units (|<ReportOrgUnitInit>|) in operator report
Then I should see File Name/Case Name(s) (|<FileName> |) in operator report

When I start edit of operator report which is currently opened
When I set Classification (<ReportClassifUpd>) in operator report
When I save currently opened operator report as draft
Given load story ../aux-report-and-request-stories/auxCheckSingleValueReportFields.story with example table:
|ExpectedClassification|ExpectedCreatedFor    |ExpectedSubject |ExpectedDescription|ExpectedConsiderations|ExpectedRecommendations|ExpectedNotes    |
|<ReportClassifUpd>    |<ReportCreatedForInit>|<ReportSubjInit>|<ReportDescrInit>  |<ReportConsidInit>    |<ReportRecommInit>     |<ReportNotesInit>|
Then I should see Organization Units (|<ReportOrgUnitInit>|) in operator report
Then I should see File Name/Case Name(s) (|<FileName> |) in operator report

When I start edit of operator report which is currently opened
When I set Created For (<ReportCreatedForUpd>) in operator report
When I save currently opened operator report as draft
Given load story ../aux-report-and-request-stories/auxCheckSingleValueReportFields.story with example table:
|ExpectedClassification|ExpectedCreatedFor   |ExpectedSubject |ExpectedDescription|ExpectedConsiderations|ExpectedRecommendations|ExpectedNotes    |
|<ReportClassifUpd>    |<ReportCreatedForUpd>|<ReportSubjInit>|<ReportDescrInit>  |<ReportConsidInit>    |<ReportRecommInit>     |<ReportNotesInit>|
Then I should see Organization Units (|<ReportOrgUnitInit>|) in operator report
Then I should see File Name/Case Name(s) (|<FileName> |) in operator report

When I start edit of operator report which is currently opened
When I set Organization Units (|<ReportOrgUnitUpd>|) in operator report
When I save currently opened operator report as draft
Given load story ../aux-report-and-request-stories/auxCheckSingleValueReportFields.story with example table:
|ExpectedClassification|ExpectedCreatedFor   |ExpectedSubject |ExpectedDescription|ExpectedConsiderations|ExpectedRecommendations|ExpectedNotes    |
|<ReportClassifUpd>    |<ReportCreatedForUpd>|<ReportSubjInit>|<ReportDescrInit>  |<ReportConsidInit>    |<ReportRecommInit>     |<ReportNotesInit>|
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
When I set Subject (<ReportSubjInit>) in operator report
When I set Description (|<ReportDescrInit>|) in operator report
When I set Considerations (|<ReportConsidInit>|) in operator report
When I set Recommendations (|<ReportRecommInit>|) in operator report
When I set Notes (|<ReportNotesInit>|) in operator report
When I save currently opened operator report as draft
Given load story ../aux-report-and-request-stories/auxCheckSingleValueReportFields.story with example table:
|ExpectedClassification|ExpectedCreatedFor    |ExpectedSubject |ExpectedDescription|ExpectedConsiderations|ExpectedRecommendations|ExpectedNotes    |
|<ReportClassifInit>   |<ReportCreatedForInit>|<ReportSubjInit>|<ReportDescrInit>  |<ReportConsidInit>    |<ReportRecommInit>     |<ReportNotesInit>|
Then I should see Organization Units (|<ReportOrgUnitInit>|<ReportOrgUnitUpd>|) in operator report
Then I should see File Name/Case Name(s) (|<FileName> |) in operator report

When I delete operator report which is currently opened
When I select file with Name (<FileName>) in the CB Finder
When I delete file which is currently selected in the CB Finder

Examples:
|FileName         |FileCalssif |FileOrgUnit  |ReportClassifInit|ReportClassifUpd|ReportCreatedForInit|ReportCreatedForUpd|ReportOrgUnitInit|ReportOrgUnitUpd|ReportSubjInit  |ReportSubjUpd|ReportDescrInit   |ReportDescrUpd              |ReportConsidInit      |ReportConsidUpd      |ReportRecommInit       |ReportRecommUpd             |ReportNotesInit   |ReportNotesUpd    |GOVINTEventName|GOVINTEventBookRef|GOVINTEventCarrier|
|ForEditReportTest|CONFIDENTIAL|Admins|SECRET           |TOP SECRET      |UI Test Team        |Team which tests UI|Admins           |Default Team    |For test purpose|For edit test|Tesing description|Updated description for test|Consideration for test|Updated consideration|Testing recommendations|Recommendations were updated|Initial test notes|Test Notes updated|Olivia QE Essa |ZV77RJ            |United Airlines   |