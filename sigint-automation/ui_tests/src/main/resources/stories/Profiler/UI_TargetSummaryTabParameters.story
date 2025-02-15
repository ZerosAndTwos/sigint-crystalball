Meta:
@UI
@component Profiler
@targetsummary

Lifecycle:
Before:
Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login.table

After:
Scope: STORY
Outcome: ANY
Given load story ../aux-after-stories/auxDeleteTarget.story with example table:
|TargetName                |TargetCategory|
|Attach to Target auto test|POI           |

Given load story ../aux-after-stories/auxDeleteReportAndFile.story with example table:
|FileName     |ReportSubject     |
|ForTargetTest|For attach widgets|

Scenario: Verify all parameters are available on the Target Summary Tab

Given I navigate to CB Finder
Given I start creation of new file in the CB Finder
Given I set Name (<FileName>) for new file in the CB Finder
Given I set Classification (<FileCalssif>) for new file in the CB Finder
Given I set Organization Units (|<FileOrgUnit>|) for new file in the CB Finder
Given I save new file created in the CB Finder

Given I navigate to Search
When I enter search criteria (<SIGINTEventIMSI>) on the Search page
Given I open Search Filter on the Search page
Given load story ../aux-search-filter-stories/auxSearchFilterShowAllEvents.story
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization

When I open Card View

When I select all SIGINT event cards having identifiers: |<SIGINTEventPhone>|
When I create new target for selected items
Given I set Target's Name to <TargetName>
Given I set Target's Description to |<TargetDescrLine1>|<TargetDescrLine2>|<TargetDescrLine3>|
Given I set Target's Classification to <TargetClassif>
Given I set Target's Organization Units to |<TargetOU>|
Given I set Target's Category to <TargetCategory>
Given I set Target's Assignment Priority to <TargetAP>
Given I set Target's Status to <TargetStatus>
Given I set Target's Active Until to <TargetAU>
Given I set Target's Threat Score Impact to <TargetTSI>
Given I set Target's File to |<FileName>|
Given I create Target

Given I navigate to Search
When I enter search criteria (<EIDEventVisa>) on the Search page
When I start search on the Search page

When I open Card View

When I select all EID event cards having identifiers: |<EIDEventPassport>|<EIDEventUIDNumber>|
When I add selected items to the existing target (<TargetName>)

When I enter search criteria (<GOVINTEventName>) on the Search page
When I start search on the Search page
When I select all GOVINT event cards having identifiers: |<GOVINTEventUID>|
When I add selected items to the existing target (<TargetName>)

When I enter search criteria (<OSINTEventName>) on the Search page
When I start search on the Search page
When I select all OSINT event cards having identifiers: |<OSINTEventID>|
When I add selected items to the existing target (<TargetName>)

Given I reset search on the Search page

When I enter search criteria (<SIGINTEntityPhone>) on the Search page
When I start search on the Search page
When I select all SIGINT entity cards having identifiers: |<SIGINTEntityName>|<SIGINTEntityIMSI>|
When I add selected items to the existing target (<TargetName>)

When I enter search criteria (<EIDEntityUIDNumber>) on the Search page
When I start search on the Search page
When I select all EID entity cards having identifiers: |<EIDEntityVISANumber>|
When I add selected items to the existing target (<TargetName>)

When I enter search criteria (<GOVINTEntityUIDNumber>) on the Search page
When I start search on the Search page
When I select all GOVINT entity cards having identifiers: |<GOVINYEntityName>|<GOVINTEntityEIDNumber>|<GOVINTEntityGender>|
When I add selected items to the existing target (<TargetName>)

When I enter search criteria (<OSINTEntityName>) on the Search page
When I start search on the Search page
When I select all OSINT entity cards having identifiers: |<OSINTEntityID>|
When I add selected items to the existing target (<TargetName>)


When I enter search criteria (<TargetName>) on the Search page
When I start search on the Search page
Given I setup Search Authorization
Given I view 1-st displayed Profiler entity in Profiler
Then I should see Profiler page
Then I should see Summary tab selected on the Profiled page

Then I should see Photo on the Summary tab of Profiled page

Then I should see Target Name (<TargetName>) on the Summary tab of Profiled page
Then I should see Description (|<TargetDescrLine1>|<TargetDescrLine2>|<TargetDescrLine3>|) on the Summary tab of Profiled page
Then I should see Files (|<FileName>|) on the Summary tab of Profiled page
Then I should see Category (<TargetCategory>) on the Summary tab of Profiled page
Then I should see Voice ID (<TargetVID>) on the Summary tab of Profiled page
Then I should see Threat Score (<TargetTS>) on the Summary tab of Profiled page
Then I should see Target Status (<TargetStatus>) on the Summary tab of Profiled page
Then I should see Expires On (<TargetAU>) on the Summary tab of Profiled page
Then I should see Classification (<TargetClassif>) on the Summary tab of Profiled page
Then I should see Criminal Record (<TargetCR>) on the Summary tab of Profiled page

Then I should see Last Seen map on the Summary tab of Profiled page

Then I should see identifier having value (<SIGINTEventIMSI>), type (IMSI), hits (0) and mentions (0) on the Summary tab of Profiled page
Then I should see identifier having value (<SIGINTEventPhone>), type (Phone Number), hits (0) and mentions (0) on the Summary tab of Profiled page
Then I should see identifier having value (<EIDEventVisa>), type (Visa Number), hits (0) and mentions (0) on the Summary tab of Profiled page
Then I should see identifier having value (<EIDEventPassport>), type (Passport Number), hits (0) and mentions (0) on the Summary tab of Profiled page
Then I should see identifier having value (<EIDEventUIDNumber>), type (UID Number), hits (0) and mentions (0) on the Summary tab of Profiled page
Then I should see identifier having value (<GOVINTEventUID>), type (UDB Number), hits (0) and mentions (0) on the Summary tab of Profiled page
Then I should see identifier having value (<OSINTEventID>), type (G+ ID), hits (0) and mentions (0) on the Summary tab of Profiled page
Then I should see identifier having value (<SIGINTEntityIMSI>), type (IMSI), hits (0) and mentions (0) on the Summary tab of Profiled page
Then I should see identifier having value (<SIGINTEntityPhone>), type (Phone Number), hits (0) and mentions (0) on the Summary tab of Profiled page
Then I should see identifier having value (<EIDEntityUIDNumber>), type (UID Number), hits (0) and mentions (0) on the Summary tab of Profiled page
Then I should see identifier having value (<EIDEntityVISANumber>), type (Visa Number), hits (0) and mentions (0) on the Summary tab of Profiled page
Then I should see identifier having value (<GOVINTEntityUIDNumber>), type (UDB Number), hits (0) and mentions (0) on the Summary tab of Profiled page
Then I should see identifier having value (<GOVINTEntityEIDNumber>), type (EID Number), hits (0) and mentions (0) on the Summary tab of Profiled page
Then I should see identifier having value (<OSINTEntityID>), type (G+ ID), hits (0) and mentions (0) on the Summary tab of Profiled page


When I click on Profile Details tab on the Profiled page
Then I should see Images widget in Profile Details page with correct heading
Then I should see Voice ID widget in Profile Details page with correct heading
Then I should see Threat Score widget in Profile Details page with correct heading
Then I should see Key Attribute widget in Profile Details page with correct heading
Then I should see EID Information widget in Profile Details page with correct heading
Then I should see Threat Score (<TargetTS>) in the Threat Score widget in Profile Details tab
Then I should see key Attributes having key (Target Name) and value (<TargetName>) on the Profile Details tab of Profiled page
Then I should see key Attributes having key (Type) and value (Individual) on the Profile Details tab of Profiled page
Then I should see key Attributes having key (Category) and value (<TargetCategory>) on the Profile Details tab of Profiled page
Then I should see key Attributes having key (Files) and value (<FileName>) on the Profile Details tab of Profiled page
Then I should see correct EID name (Ivan Sun) in the EID Information widget in Profile Details tab
Then I should see correct EID number (<EIDEntityUIDNumber>) in the EID Information widget in Profile Details tab

When I click on Communication tab on the Profiled page

Then I should see Identifiers widget in Communication page with correct heading
Then I should see Event Types widget in Communication page with correct heading
Then I should see Activity Pattern widget in Communication page with correct heading
Then I should see Key Activity Stream in Communication page with correct heading
Then I should see Most Frequent Contacts widget in Communication page with correct heading
Then I should see Map widget in Communication page with correct heading



When I click on Travel and Movement tab on the Profiled page
Then I should see Activity Stream widget in Travel And Movement page with correct heading
Then I should see Map widget in Travel And Movement page with correct heading

When I click on Insights tab on the Profiled page
Then I should see Threat Score Prediction widget in Insights Beta page with correct heading
Then I should see Suggested Identifiers widget in Insights Beta page with correct heading
Then I should see Similar Targets widget in Insights Beta with correct heading


When I click on Summary tab on the Profiled page

Given I create report for Target Summary widget on the Profiled page
When I set Classification (<ReportClassif>) in operator report
When I set File Name/Case Name (|<FileName>|) in operator report
When I set Organization Units (|<ReportOrgUnit>|) in operator report
When I set Subject (<ReportSubj>) in operator report
When I save currently opened operator report as draft

Given I add Last Seen widget to existing report (<ReportSubj>) on the Profiled page
When I save currently opened operator report as draft

Given I add Identifiers widget to existing report (<ReportSubj>) on the Profiled page
When I save currently opened operator report as draft

Given I add RFIs widget to existing report (<ReportSubj>) on the Profiled page
When I save currently opened operator report as draft


Given I navigate to CB Finder
When I select file with Name (<FileName>) in the CB Finder
When I open operator report with Subject (<ReportSubj>) in file/case which is currently selected in the CB Finder
Then I should see (1) snapshot(s) of Target Summary widget in operator report
Then I should see (1) snapshot(s) of Last Seen widget in operator report
Then I should see (1) snapshot(s) of Identifiers widget in operator report
Then I should see (1) snapshot(s) of RFIs widget in operator report


Examples:
|FileName     |FileCalssif |FileOrgUnit   |TargetName                |TargetDescrLine1            |TargetDescrLine2  |TargetDescrLine3     |TargetClassif |TargetOU      |TargetCategory|TargetAP|TargetStatus|TargetAU                                      |TargetTSI|TargetTS|TargetCR|TargetVID|ReportClassif|ReportOrgUnit |ReportSubj |SIGINTEventIMSI|SIGINTEventPhone|FININTEventTo|FININTEventFrom|TRAFFICEventPlateNo|TRAFFICEventPhysicalId|EIDEventVisa    |EIDEventPassport|EIDEventUIDNumber|GOVINTEventName  |GOVINTEventUID|OSINTEventName |OSINTEventID         |SIGINTEntityPhone|SIGINTEntityName |SIGINTEntityIMSI|EIDEntityUIDNumber|EIDEntityVISANumber|EIDEntityGender|GOVINYEntityName|GOVINTEntityUIDNumber|GOVINTEntityEIDNumber|GOVINTEntityGender|OSINTEntityName  |OSINTEntityID|
|ForTargetTest|CONFIDENTIAL|QE_UIAuto_Team|Target automation test|1-st line of the description|Description line 2|Last description line|TOP SECRET-CIO|QE_UIAuto_Team|POI      |High    |Active      |{current_day}+1/{current_month}/{current_year}|Medium 30|50      |None    |No       |TOP SECRET   |QE_UIAuto_Team|For attach widgets|4534525345345  |245345435       |LU280019400644750000|SA0380000000608010167519|bLiNr|bBBmUUyLBi|5397127033150134|J6342425667     |36377983960994981|8232499|8232499       |Kristina Turner|103446745626515478415|14993646179      |Brandon Rodriguez|780968629676446 |08279772913445397|439370523040594813|female         |Jack QE Essa    |9987481              |CG3OTSPED3LV84G0D9YX                               |Female  |Southpark Seafood  |115558179973836572416|
