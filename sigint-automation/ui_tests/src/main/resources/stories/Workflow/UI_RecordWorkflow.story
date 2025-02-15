Meta:
@UI
@component Records workflow
@recordworkflow



Scenario: Verify that a manager can assign records to another user using ad-hoc assignment
Meta: @L0  @test  C83424
Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_manager.table

Given I navigate to Search

Given I navigate to Search
When I enter search criteria (<SIGINTEventIMSI>) on the Search page
Given I open Search Filter on the Search page
Given load story ../aux-search-filter-stories/auxSearchFilterShowAllEvents.story
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization

When I open Card View
When I select all SIGINT event cards having identifiers: |<SIGINTEventPhone>|
When I assign selected items to (<AnalystLogin>)

Given I Sign Out

Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_analyst.table

!-- Given I open Alerting History page

!-- Then I should see assign of (1) record(s) by (<ManagerLogin>) at row (1) of (<AssignDate>) To Do List entry on the Alerting History page

Given I navigate to My Records

When I open Card View

Then I should see at least 1 of SIGINT events on current view

When I select all SIGINT event cards having identifiers: |<SIGINTEventPhone>|

When I unassign selected items

Examples:
|ManagerLogin     |ManagerPassword|AnalystLogin     |AnalystPassword  |SIGINTEventIMSI|SIGINTEventPhone|GOVINTEventCarrier|AssignDate                                  |
|QE_UIAuto_Manager|2020@Pegasus!! |QE_UIAuto_Analyst|<ManagerPassword>|085328191954452  |249775789478            |Amer



Scenario: Record - Record is searchable within CB Search
Meta: @L0  @test  C83425
Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login.table



Given I navigate to OrgUnit Records
Given I setup Search Authorization
Then I should see OrgUnit Records page

Given I navigate to Workloads
Then The page heading should be (Workloads)

Given I navigate to My Records
Then I should see My Records page

When I enter search criteria (<SIGNTIMSI>) on the My Records page
Given I open Search Filter on the My Records page
Given I set date (01/01/2014) as Earliest Event Time on the Search Filter page
Given I open Search Filter on the My Records page
Given I open Search Filter on the My Records page
Given I Apply Search using Search Filter on the My Records page
Given I setup Search Authorization
Then I should see 1 SIGINT event(s) on current view



Examples:
|SIGNTIMSI     |ManagerPassword|AnalystLogin     |AnalystPassword  |SIGINTEventIMSI|SIGINTEventPhone|GOVINTEventCarrier|AssignDate                                  |
|5345346546557|2020@Pegasus!! |QE_UIAuto_Analyst|<ManagerPassword>|085328191954452  |249775789478            |Amer






Scenario: Record - Under My/OrgUnit Records, team manager can reassign records to another user
Meta: @L0  @test  C83427
Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_manager.table

Given I navigate to OrgUnit Records
Given I setup Search Authorization
Then I should see OrgUnit Records page
When I enter search criteria (<SIGINTEventIMSI>) on the My Records page
Given I open Search Filter on the My Records page
Given I set date (01/01/2014) as Earliest Event Time on the Search Filter page
Given I open Search Filter on the My Records page
Given I open Search Filter on the My Records page
Given I Apply Search using Search Filter on the My Records page
Given I setup Search Authorization
Then I should see 1 SIGINT event(s) on current view
When I open Card View
When I select all SIGINT event cards having identifiers: |<SIGINTEventPhone>|
When I assign selected items to (<AnalystLogin>)


Given I Sign Out

Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_analyst.table


Given I navigate to My Records
When I select all SIGINT event cards having identifiers: |<SIGINTEventPhone>|

Given I Sign Out
Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_manager.table

Given I navigate to OrgUnit Records
Given I setup Search Authorization
Then I should see OrgUnit Records page
Then I sleep for 5 second
When I enter search criteria (<SIGINTEventIMSI>) on the My Records page
Given I open Search Filter on the My Records page
Given I set date (01/01/2014) as Earliest Event Time on the Search Filter page
Given I open Search Filter on the My Records page
Given I open Search Filter on the My Records page
Given I Apply Search using Search Filter on the My Records page
Given I setup Search Authorization
Then I should see 1 SIGINT event(s) on current view
When I open Card View
When I select all SIGINT event cards having identifiers: |<SIGINTEventPhone>|
When I reassign selected items to (<OperatorLogin>)

Given I Sign Out
Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_operator.table


Given I navigate to My Records

When I select all SIGINT event cards having identifiers: |<SIGINTEventPhone>|
When I unassign selected items

Examples:
|OperatorLogin     |ManagerPassword|AnalystLogin     |AnalystPassword  |SIGINTEventIMSI|SIGINTEventPhone|GOVINTEventCarrier|AssignDate                                  |
|QE_UIAuto_Operator|2020@Pegasus!! |QE_UIAuto_Analyst|<ManagerPassword>|681553005047588  |971187899244            |Amer


Scenario: Record - Under My/OrgUnit Records, team manager can un-assign records
Meta: @L0  @test  C83471
Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_manager.table

Given I navigate to OrgUnit Records
Given I setup Search Authorization
Then I should see OrgUnit Records page
Then I sleep for 5 second
When I enter search criteria (<SIGINTEventIMSI>) on the My Records page
Given I open Search Filter on the My Records page
Given I set date (01/01/2014) as Earliest Event Time on the Search Filter page
Given I open Search Filter on the My Records page
Given I open Search Filter on the My Records page
Given I Apply Search using Search Filter on the My Records page
Given I setup Search Authorization
Then I should see 1 SIGINT event(s) on current view
When I open Card View
When I select all SIGINT event cards having identifiers: |<SIGINTEventPhone>|

When I assign selected items to (<AnalystLogin>)
When I select all SIGINT event cards having identifiers: |<SIGINTEventPhone>|
When I unassign selected items on OrgUnit Records page


Examples:
|ManagerLogin     |ManagerPassword|AnalystLogin     |AnalystPassword  |SIGINTEventIMSI|SIGINTEventPhone|GOVINTEventCarrier|AssignDate                                  |
|QE_UIAuto_Manager|2020@Pegasus!! |QE_UIAuto_Analyst|<ManagerPassword>|41232588077346  |59853598447            |Amer




Scenario: Record - Under My/OrgUnit Records, team manager can mark records as reviewed
Meta: @L10  @test  C83472
Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_manager.table

Given I navigate to OrgUnit Records
Given I setup Search Authorization
Then I should see OrgUnit Records page
Then I sleep for 5 second
When I enter search criteria (<SIGINTEventIMSI>) on the My Records page
Given I open Search Filter on the My Records page
Given I set date (01/01/2014) as Earliest Event Time on the Search Filter page
Given I open Search Filter on the My Records page
Given I open Search Filter on the My Records page

Given I Apply Search using Search Filter on the My Records page
Given I setup Search Authorization
Then I should see 1 SIGINT event(s) on current view
When I open Card View
When I select all SIGINT event cards having identifiers: |<SIGINTEventPhone>|

When I mark selected items on OrgUnit Records page as reviewed
Given I navigate to OrgUnit Records
When I select all SIGINT event cards having identifiers: |<SIGINTEventPhone>|
When I mark selected items on OrgUnit Records page as unreviewed


Examples:
|ManagerLogin     |ManagerPassword|AnalystLogin     |AnalystPassword  |SIGINTEventIMSI|SIGINTEventPhone|GOVINTEventCarrier|AssignDate                                  |
|QE_UIAuto_Manager|2020@Pegasus!! |QE_UIAuto_Analyst|<ManagerPassword>|741168899929654  |8502987346253            |Amer






Scenario: Verify that a manager can assign records to another user using ad-hoc assignment

Given load story ../aux-main-stories/auxSignIn.story with example table:
|login         |password         |
|<ManagerLogin>|<ManagerPassword>|

Given I navigate to Search

Given I navigate to Search
When I enter search criteria (<SIGINTEventIMSI>) on the Search page
Given I open Search Filter on the Search page
Given load story ../aux-search-filter-stories/auxSearchFilterShowAllEvents.story
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization

When I open Card View
When I select all SIGINT event cards having identifiers: |<SIGINTEventPhone>|
When I assign selected items to (<AnalystLogin>)

Given I Sign Out

Given load story ../aux-main-stories/auxSignIn.story with example table:
|login         |password         |
|<AnalystLogin>|<AnalystPassword>|

!-- Given I open Alerting History page

!-- Then I should see assign of (1) record(s) by (<ManagerLogin>) at row (1) of (<AssignDate>) To Do List entry on the Alerting History page

Given I navigate to My Records

When I open Card View

Then I should see at least 1 of SIGINT events on current view

When I select all SIGINT event cards having identifiers: |<SIGINTEventPhone>|

When I unassign selected items

Examples:
|ManagerLogin     |ManagerPassword|AnalystLogin     |AnalystPassword  |SIGINTEventIMSI|SIGINTEventPhone|GOVINTEventCarrier|AssignDate                                  |
|QE_UIAuto_Manager|2020@Pegasus!! |QE_UIAuto_Analyst|<ManagerPassword>|983528369265302  |9812098534            |American Airlines |{current_day}/{current_month}/{current_year}|



Scenario: Verify that a User can select records from team Record screen and take ownership on them
Given load story ../aux-main-stories/auxSignIn.story with example table:
|login         |password         |
|<ManagerLogin>|<ManagerPassword>|

Given I navigate to OrgUnit Records
Given I setup Search Authorization
Given I open Search Filter on the Team Records page
Given I set date (01/01/2016) as Earliest Event Time on the Search Filter page
Given I Apply Search using Search Filter on the Team Records page

When I open Card View
When I open details of 1-st displayed SIGINT Event
Then I should not see Owner field
Given I close search result Details page
When I select 1-st displayed SIGINT event card
When I take ownership on selected items


Given I navigate to My Records
When I enter search criteria (<SIGINTEventPhone>) on the My Records page
When I open Card View
Then I should see at least 1 of SIGINT events on current view
When I open details of 1-st displayed SIGINT Event
Then I should see Owner field's value (<ManagerLogin>) on the search result Details page
Given I close search result Details page
When I select 1-st displayed SIGINT event card
When I unassign selected items

Examples:
|ManagerLogin     |ManagerPassword|SIGINTEventPhone|
|QE_UIAuto_Manager|2020@Pegasus!! |9812098534|
