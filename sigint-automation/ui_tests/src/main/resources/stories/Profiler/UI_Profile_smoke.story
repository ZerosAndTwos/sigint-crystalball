Meta:
@UI
@component Profiler

Lifecycle:
Before:
Scope: SCENARIO
Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_manager.table


Scenario: Profiler - Create Target - add SIGINT event
Meta: @L0  @test  C83380
Given I navigate to Search
When I enter search criteria (<SIGINTEventIMSI>) on the Search page
Given I open Search Filter on the Search page
Given I set Source Type to SIGINT on the Search Filter page
Given load story ../aux-search-filter-stories/auxSearchFilterShowAllEvents.story
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization

When I open Card View
Given I select all cards on the Search page
When I create new target for selected items
Given I set Target's Name to <TargetName>
Given I set Target's Description to |<TargetDescr>|
Given I set Target's Classification to <ProfilerEntityClas>
Given I set Target's Organization Units to |<ProfilerEntityOU>|
Given I set Target's Category to <TargetCategory>
Given I set Target's Assignment Priority to <ProfilerEntityAP>
Given I set Target's Status to <ProfilerEntityTS>
Given I set Target's Active Until to <ProfilerEntityAU>
Given I set Target's Threat Score Impact to <ProfilerEntityTSI>
Given I set Target's File to |<FileName>|


Given I create Target

Given I navigate to Search
Given I reset search on the Search page

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
Then I should see Audit Trail tab on the Profiled page
Then I should see Insights tab on the Profiled page
Then I should see identifier having value (<SIGINTEventIMSI>), type (IMSI), hits (0) and mentions (0) on the Summary tab of Profiled page
Then I should see identifier having value (<SIGINTEventToPhone>), type (Phone Number), hits (0) and mentions (0) on the Summary tab of Profiled page
Then I should see identifier having value (<SIGINTEventFromPhone>), type (Phone Number), hits (0) and mentions (0) on the Summary tab of Profiled page

Given I navigate to CB Finder
When I select file with Name (<FileName>) in the CB Finder
Then I check if target by name (<TargetName>) is present under case/file which is currently selected in the CB Finder
When I open target by name (<TargetName>) is present under case/file which is currently selected in the CB Finder
Given I navigate to Search
Given I reset search on the Search page
When I enter search criteria (<TargetName>) on the Search page
When I start search on the Search page
Given I delete all Profiler entity cards on the Search page having identifiers: |<TargetName>|<TargetCategory>|

Examples:
|FileName   |TargetName				|ProfilerEntityClas|ProfilerEntityOU|TargetCategory|ProfilerEntityAP|ProfilerEntityTS|ProfilerEntityAU                              |ProfilerEntityTSI|ReportClassifInit|ReportCreatedForInit|ReportOrgUnitInit|ReportSubject   |ReportDescrInit  |ReportConsidInit |ReportRecommInit |ReportNotesInit   |SIGINTEventIMSI|SIGINTEventToPhone|SIGINTEventFromPhone|TargetDescr|
|Default	|Target for LO profile test|TOP SECRET    |QE_UIAuto_Team  |POI              |High            |Active          |{current_day}+1/{current_month}/{current_year}|Medium 30 |TOP SECRET|UI Test Team  |QE_UIAuto_Team |For test purpose|Testing description|Consideration for test|Testing recommendations|Initial test notes|063769296482132|23156403410|+994 26 929 25 66|for L0 sigint|




Scenario: Profiler - Create Target - add GOVINT Entity
Meta: @L0  @test  C83479

Given I navigate to Search
When I enter search criteria (<GOVINTEIDPerson>) on the Search page
When I start search on the Search page
Given I setup Search Authorization
When I start search on the Search page

When I select all GOVINT entity cards having identifiers: |<GOVINTEIDPerson>|<GOVINTUIDNumber>|

When I create new target for selected items
Given I set Target's Name to <TargetName>
Given I set Target's Description to |<TargetDescr>|
Given I set Target's Classification to <ProfilerEntityClas>
Given I set Target's Organization Units to |<ProfilerEntityOU>|
Given I set Target's Category to <TargetCategory>
Given I set Target's Assignment Priority to <ProfilerEntityAP>
Given I set Target's Status to <ProfilerEntityTS>
Given I set Target's Active Until to <ProfilerEntityAU>
Given I set Target's Threat Score Impact to <ProfilerEntityTSI>
Given I set Target's File to |<FileName>|
Given I create Target

Given I navigate to Search
Given I reset search on the Search page

When I enter search criteria (<TargetName>) on the Search page
When I start search on the Search page
Given I setup Search Authorization
Given I view 1-st displayed Profiler entity in Profiler
Then I should see Profiler page

Then I should see Photo on the Summary tab of Profiled page

Then I should see Target Name (<TargetName>) on the Summary tab of Profiled page
Then I should see Description (|<TargetDescr>|) on the Summary tab of Profiled page
Then I should see Files (|<FileName>|) on the Summary tab of Profiled page
Then I should see Category (<TargetCategory>) on the Summary tab of Profiled page
Then I should see Voice ID (<TargetVID>) on the Summary tab of Profiled page
Then I should see Threat Score (<ProfilerEntityTSD>) on the Summary tab of Profiled page
Then I should see Target Status (<ProfilerEntityTS>) on the Summary tab of Profiled page
Then I should see Expires On (<ProfilerEntityAU>) on the Summary tab of Profiled page
Then I should see Classification (<ProfilerEntityClas>) on the Summary tab of Profiled page
Then I should see Criminal Record (<TargetCR>) on the Summary tab of Profiled page

Then I should see Last Seen map on the Summary tab of Profiled page


Then I should see Insights tab on the Profiled page
Then I should see identifier having value (<GOVINTUIDNumber>), type (UID Number), hits (0) and mentions (0) on the Summary tab of Profiled page
Then I should see identifier having value (<GOVINTEIDPerson>), type (EID Number), hits (0) and mentions (0) on the Summary tab of Profiled page

Given I navigate to CB Finder
When I select file with Name (<FileName>) in the CB Finder
Then I check if target by name (<TargetName>) is present under case/file which is currently selected in the CB Finder
When I open target by name (<TargetName>) is present under case/file which is currently selected in the CB Finder

Examples:
|FileName   |TargetName				|ProfilerEntityClas|ProfilerEntityOU|TargetCategory|ProfilerEntityAP|ProfilerEntityTS|ProfilerEntityAU                              |ProfilerEntityTSI|ReportClassifInit|ReportCreatedForInit|ReportOrgUnitInit|ReportSubject   |ReportDescrInit  |ReportConsidInit |ReportRecommInit |ReportNotesInit   |GOVINTEIDPerson|GOVINTUIDNumber|SIGINTEventFromPhone|TargetDescr|TargetVID|TargetCR|ProfilerEntityTSD|
|Default	|Target for LO GOVINT profile test|TOP SECRET    |QE_UIAuto_Team  |POI              |High            |Active          |{current_day}+1/{current_month}/{current_year}|Medium 30 |TOP SECRET|UI Test Team  |QE_UIAuto_Team |For test purpose|Testing description|Consideration for test|Testing recommendations|Initial test notes|6766WGD6ANXCZORU1K7M|8237695|+994 26 929 25 66|This for L0 test|No|None|50|





Scenario: Profiler - Basic info on Profile page
Meta: @L0  @test  C83379


Given I navigate to Search

When I enter search criteria (<TargetName>) on the Search page
When I start search on the Search page
Given I setup Search Authorization
Given I view 1-st displayed Profiler entity in Profiler
Then I should see Profiler page

Then I should see Photo on the Summary tab of Profiled page

Then I should see Target Name (<TargetName>) on the Summary tab of Profiled page
Then I should see Description (|<TargetDescr>|) on the Summary tab of Profiled page
Then I should see Files (|<FileName>|) on the Summary tab of Profiled page
Then I should see Category (<TargetCategory>) on the Summary tab of Profiled page
Then I should see Voice ID (<TargetVID>) on the Summary tab of Profiled page
Then I should see Threat Score (<ProfilerEntityTSD>) on the Summary tab of Profiled page
Then I should see Target Status (<ProfilerEntityTS>) on the Summary tab of Profiled page
Then I should see Expires On (<ProfilerEntityAU>) on the Summary tab of Profiled page
Then I should see Classification (<ProfilerEntityClas>) on the Summary tab of Profiled page
Then I should see Criminal Record (<TargetCR>) on the Summary tab of Profiled page
Then I should see Last Seen map on the Summary tab of Profiled page

Given I navigate to Search
Given I reset search on the Search page
When I enter search criteria (<TargetName>) on the Search page
When I start search on the Search page
Given I delete all Profiler entity cards on the Search page having identifiers: |<TargetName>|<TargetCategory>|

Examples:
|FileName   |TargetName				|ProfilerEntityClas|ProfilerEntityOU|TargetCategory|ProfilerEntityAP|ProfilerEntityTS|ProfilerEntityAU                              |ProfilerEntityTSI|ReportClassifInit|ReportCreatedForInit|ReportOrgUnitInit|ReportSubject   |ReportDescrInit  |ReportConsidInit |ReportRecommInit |ReportNotesInit   |GOVINTEIDPerson|GOVINTUIDNumber|SIGINTEventFromPhone|TargetDescr|TargetVID|TargetCR|ProfilerEntityTSD|
|Default	|Target for LO GOVINT profile test|TOP SECRET    |QE_UIAuto_Team  |POI              |High            |Active          |{current_day}+1/{current_month}/{current_year}|Medium 30 |TOP SECRET|UI Test Team  |QE_UIAuto_Team |For test purpose|Testing description|Consideration for test|Testing recommendations|Initial test notes|6766WGD6ANXCZORU1K7M|8237695|+994 26 929 25 66|This for L0 test|No|None|50|

Scenario: Create and delete a profile smoke test
Meta: @devsmoke
Given I navigate to Search

When I enter search criteria (<EIDEntityUIDNumber>) on the Search page
When I start search on the Search page
Given I setup Search Authorization

When I open Card View
When I start search on the Search page
When I select all EID entity cards having identifiers: |<EIDEntityVISANumber>|
When I create new target for selected items
Given I set Target's Name to <TargetName>
Given I set Target's Classification to <ProfilerEntityClas>
Given I set Target's Organization Units to |<ProfilerEntityOU>|
Given I set Target's Category to <TargetCategory>
Given I set Target's Assignment Priority to <ProfilerEntityAP>
Given I set Target's Status to <ProfilerEntityTS>
Given I set Target's Active Until to <ProfilerEntityAU>
Given I set Target's Threat Score Impact to <ProfilerEntityTSI>
Given I set Target's File to |<FileName>|

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
Then I should see Audit Trail tab on the Profiled page
Then I should see Insights tab on the Profiled page


Given I navigate to CB Finder
When I select file with Name (<FileName>) in the CB Finder
Then I check if target by name (<TargetName>) is present under case/file which is currently selected in the CB Finder
When I open target by name (<TargetName>) is present under case/file which is currently selected in the CB Finder
Given load story ../aux-after-stories/auxDeleteTarget.story with example table:
data/BasicProfile.data

Examples:
|FileName   |TargetName				|ProfilerEntityClas|ProfilerEntityOU|TargetCategory|ProfilerEntityAP|ProfilerEntityTS|ProfilerEntityAU                              |ProfilerEntityTSI|ReportClassifInit|ReportCreatedForInit|ReportOrgUnitInit|ReportSubject   |ReportDescrInit  |ReportConsidInit |ReportRecommInit |ReportNotesInit   |EIDEntityUIDNumber|EIDEntityVISANumber|
|Default	|Target for profile test|TOP SECRET    |QE_UIAuto_Team  |POI              |High            |Active          |{current_day}+1/{current_month}/{current_year}|Medium 30 |TOP SECRET|UI Test Team  |QE_UIAuto_Team |For test purpose|Testing description|Consideration for test|Testing recommendations|Initial test notes|3642367978578790690|ad3kNtL6cIfSVUQn58g7|
