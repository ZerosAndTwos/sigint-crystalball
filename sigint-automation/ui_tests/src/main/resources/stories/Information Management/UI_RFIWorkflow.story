Meta:
@UI
@component Information Management
@RFI
@wip

Scenario: Create an internal RFI

Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_manager.table

Given load story ../aux-before-stories/auxCreateFile.story
Given load story ../aux-before-stories/auxCreateCase.story

Given I Sign Out

Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_operator.table

Given I navigate to CB Finder
When I select case with Name (<CaseName>) in the CB Finder
Given I start creation of a RFI from file/case which is currently selected in the CB Finder
When I set Classification (<RFIClasif>) in request for information
When I set Organization Units (|<RFIOrgUnit>|) in request for information
When I set Subject (<RFISubject>) in request for information
When I set Required (|<Required1>|<Required2>|<Required3>|) in request for information
When I start sending of currently opened request for information
When I enter Comments (|Send test|) for the sending request for information
When I complete sending of request for information

Given load story ../aux-report-and-request-stories/auxCheckSingleValueRequestFields.story with example table:
|ExpectedClassification|ExpectedPriority|ExpectedManualRFINumber|ExpectedRFIType|ExpectedSubject|
|<RFIClasif>           |<RFIPriority>   |                       |<RFIType>      |<RFISubject>   |

Then I should see Required (|<Required1>|<Required2>|<Required3>|) in request for information
Then I should see Organization Units (|<RFIOrgUnit>|) in request for information
Then I should see File Name/Case Name(s) (|<CaseName>|) in request for information

When I recall currently opened request for information

Given I navigate to CB Finder
When I select case with Name (<CaseName>) in the CB Finder
When I open request for information with Subject (<RFISubject>) in file/case which is currently selected in the CB Finder
When I delete request for information which is currently opened with comment (|Delete RFI|)
Given I Sign Out

Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_manager.table

Given load story ../aux-after-stories/auxDeleteCase.story
Given load story ../aux-after-stories/auxDeleteFile.story

Examples:
|FileName        |CaseName        |FileCalssif |FileOrgUnit   |RFIClasif|RFIPriority|RFIOrgUnit   |RFIType |RFISubject  |Required1            |Required2         |Required3        |
|UIFileForRequest|UICaseForRequest|CONFIDENTIAL|QE_UIAuto_Team|SECRET   |Normal     |<FileOrgUnit>|Internal|For test RFI|1-st line of required|Next required line|This is last line|