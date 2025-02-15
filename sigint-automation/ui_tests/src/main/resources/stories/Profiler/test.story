Meta:
@UI
@component Profiler
@test123

Lifecycle:
Before:
Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login.table



Scenario: Verify all parameters are available on the Target Summary Tab
Given I navigate to Search
When I enter search criteria (<SIGINTEventIMSI>) on the Search page
Given I open Search Filter on the Search page
Given load story ../aux-search-filter-stories/auxSearchFilterShowAllEvents.story
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization

When I open Card View

When I select all FININT event cards having identifiers: |<SIGINTEventPhone>|





Examples:
|FileName     |FileCalssif |FileOrgUnit   |TargetName                |TargetDescrLine1            |TargetDescrLine2  |TargetDescrLine3     |TargetClassif |TargetOU      |TargetCat|TargetAP|TargetStatus|TargetAU                                      |TargetTSI|TargetTS|TargetCR|TargetVID|ReportClassif|ReportOrgUnit |ReportSubj        |SIGINTEventIMSI|From|EIDEventVisa    |FININTEvent|EIDEventPassport|EIDEventUIDNumber|GOVINTEventName  |GOVINTEventUID|OSINTEventName |OSINTEventID         |SIGINTEntityPhone|SIGINTEntityName |SIGINTEntityIMSI|EIDEntityUIDNumber|EIDEntityVISANumber|EIDEntityGender|GOVINYEntityName|GOVINTEntityUIDNumber|GOVINTEntityEIDNumber|GOVINTEntityGender|OSINTEntityName  |OSINTEntityID        |
|ForTargetTest|CONFIDENTIAL|QE_UIAuto_Team|Attach to Target auto test|1-st line of the description|Description line 2|Last description line|TOP SECRET-CIO|QE_UIAuto_Team|POI      |High    |Active      |{current_day}+1/{current_month}/{current_year}|Medium 30|50      |None    |No       |TOP SECRET   |QE_UIAuto_Team|For attach widgets|LU280019400644750000  |Cameron Schink       |5397127033150134|LU280019400644750000|J6342425667     |36377983960994981|Mubarak QE Haddad|8232499       |Kristina Turner|103446745626515478415|14993646179      |Brandon Rodriguez|780968629676446 |08279772913445397|439370523040594813|female         |Jack QE Essa    |9987481              |CG3OTSPED3LV84G0D9YX |Female            |Southpark Seafood|115558179973836572416|
