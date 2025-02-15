Meta:
@UI
@component Records workflow
@skip

Lifecycle:

After:
Scope: STORY
Outcome: ANY

Given I Sign Out

Given load story ../aux-main-stories/auxSignIn.story with example table:
|login             |password      |
|QE_UIAuto_Operator|2020@Pegasus!!|

Given I navigate to My Records
When I open Card View
When I select 1-st displayed SIGINT event card
When I unassign selected items

Given load story ../aux-after-stories/auxDeleteReport.story with example table:
|FileName     |ReportSubject   |
|ForReportTest|For test purpose|

Given I Sign Out

Given load story ../aux-main-stories/auxSignIn.story with example table:
|login            |password      |
|QE_UIAuto_Manager|2020@Pegasus!!|

Given load story ../aux-after-stories/auxDeleteFile.story with example table:
|FileName     |
|ForReportTest|


Scenario: Verify that a User can assess records by creating a report from them

Given load story ../aux-main-stories/auxSignIn.story with example table:
|login         |password         |
|<ManagerLogin>|<ManagerPassword>|

Given I navigate to CB Finder
Given I start creation of new file in the CB Finder
Given I set Name (<FileName>) for new file in the CB Finder
Given I set Classification (<FileCalssif>) for new file in the CB Finder
Given I set Organization Units (|<FileOrgUnit>|) for new file in the CB Finder
Given I save new file created in the CB Finder

Given I navigate to OrgUnit Records
Given I setup Search Authorization
When I enter search criteria (<SIGNINTEventText>) on the Team Records page
Given I open Search Filter on the Team Records page
Given I set date (01/01/2016) as Earliest Event Time on the Search Filter page
Given I open Search Filter on the Team Records page
Given I open Search Filter on the Team Records page
Given I Apply Search using Search Filter on the Team Records page

When I open Card View
When I select all SIGINT event cards having identifiers: |<SIGNINTEventPhoneFrom>|<SIGNINTEventPhoneTo>|
When I assign selected items to (<OperatorLogin>)

Given I Sign Out

Given load story ../aux-main-stories/auxSignIn.story with example table:
|login          |password          |
|<OperatorLogin>|<OperatorPassword>|

Given I navigate to My Records
When I open Card View
Then I should see 1 SIGINT event(s) on current view
When I select all SIGINT event cards having identifiers: |<SIGNINTEventPhoneFrom>|<SIGNINTEventPhoneTo>|<SIGNINTEventText>|

When I create new report for selected items
When I set Classification (<ReportClassif>) in operator report
When I set File Name/Case Name (|<FileName>|) in operator report
When I set Organization Units (|<ReportOrgUnit>|) in operator report
When I set Subject (<ReportSubj>) in operator report
When I save currently opened operator report as draft

Given load story ../aux-record-workflow/auxCheckReportWithSIGNINTEventAttached.story

Given I Sign Out

Given load story ../aux-main-stories/auxSignIn.story with example table:
|login         |password         |
|<ManagerLogin>|<ManagerPassword>|

Given load story ../aux-record-workflow/auxCheckReportWithSIGNINTEventAttached.story


Examples:
|ManagerLogin     |ManagerPassword|OperatorLogin     |OperatorPassword|FileName     |FileCalssif |FileOrgUnit   |SIGNINTEventPhoneFrom|SIGNINTEventPhoneTo|SIGNINTEventText            |ReportClassif|ReportOrgUnit|ReportSubj      |
|QE_UIAuto_Manager|2020@Pegasus!! |QE_UIAuto_Operator|2020@Pegasus!!  |ForReportTest|CONFIDENTIAL|QE_UIAuto_Team|+1 213 313 2016      |+44 302 150 3787   |above the call one down than|TOP SECRET   |<FileOrgUnit>|For test purpose|
