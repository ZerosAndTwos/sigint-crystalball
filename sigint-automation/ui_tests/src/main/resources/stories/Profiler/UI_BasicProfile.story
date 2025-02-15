Meta:
@UI
@component Profiler

Lifecycle:
Before:
Scope: SCENARIO
Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login.table


After:
Scope: STORY
Outcome: ANY





Given load story ../aux-after-stories/auxDeleteCase.story with example table:
data/BasicProfile.data

Given load story ../aux-after-stories/auxDeleteFile.story with example table:
data/BasicProfile.data

Scenario: Create and delete a profile
Meta:@stage
Given I navigate to CB Finder
Given I start creation of new file in the CB Finder
Given I set Name (<FileName>) for new file in the CB Finder
Given I set Classification (<FileCalssif>) for new file in the CB Finder
Given I set Organization Units (|<FileOrgUnit>|) for new file in the CB Finder
Given I save new file created in the CB Finder

Given load story ../aux-before-stories/auxCreateCase.story with example table:
|FileName|CaseName|
|<FileName>|<CaseName>|

Given I navigate to Create Target
Given I set Target's Name to <TargetName>
Given I set Target's Classification to <ProfilerEntityClas>
Given I set Target's Organization Units to |<ProfilerEntityOU>|
Given I set Target's Category to <TargetCategory>
Given I set Target's Assignment Priority to <ProfilerEntityAP>
Given I set Target's Status to <ProfilerEntityTS>
Given I set Target's Active Until to <ProfilerEntityAU>
Given I set Target's Threat Score Impact to <ProfilerEntityTSI>
Given I set Target's File to |<FileName>|
Given I set Target's File to |<CaseName>|
Given I create Target

Given I navigate to Search
When I enter search criteria (<TargetName>) on the Search page
When I start search on the Search page
Given I setup Search Authorization
Given I view 1-st displayed Profiler entity in Profiler
Then I should see Profiler page
Then I should see Profile Details tab on the Profiled page
Then I should see Communication tab on the Profiled page
Then I should see Travel and Movement tab on the Profiled page
Then I should see Mentions tab on the Profiled page
Then I should see Network tab on the Profiled page
Then I should see Attachments tab on the Profiled page
Then I should see Target Activity tab on the Profiled page
Then I should see Insights tab on the Profiled page


Given I navigate to CB Finder
When I select case with Name (<CaseName>) in the CB Finder
Then I check if target by name (<TargetName>) is present under case/file which is currently selected in the CB Finder
When I open target by name (<TargetName>) is present under case/file which is currently selected in the CB Finder

Given load story ../aux-after-stories/auxDeleteTarget.story with example table:
data/BasicProfile.data
Examples:
data/BasicProfile.data


Scenario: Create a report from profile from CB finder

Given I navigate to CB Finder
When I select case with Name (<FileName>) in the CB Finder
Then I check if target by name (<TargetName>) is present under case/file which is currently selected in the CB Finder
When I open target by name (<TargetName>) is present under case/file which is currently selected in the CB Finder
Given I create a report for target which is currently selected in the CB Finder
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
Then I should see card attached to currently opened operator report with all following label/value pair(s): |Name|<TargetName>|Category|<TargetCategory>|Status|<ProfilerEntityTS>|
Given load story ../aux-after-stories/auxDeleteReport.story with example table:
data/BasicProfile.data

Examples:
data/BasicProfile.data