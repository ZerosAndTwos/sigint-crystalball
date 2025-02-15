Meta:
@UI
@devsmoke
@stage


Lifecycle:
Before:
Scope: SCENARIO
Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login.table

Scenario: Search menu item is working
Given I navigate to Search
Then I should see Search page
Then The page heading should be (Search)


Scenario: Geo Search menu item is working
Given I navigate to Geo Search
Then I should see Geo Search page

Scenario: Advanced Search menu item is working
Given I navigate to Advanced Search
Then I should see Advanced Search page

Scenario: Saved Search menu item is working
Given I navigate to Saved Searches
Then I should see Saved Searches page

Scenario: CB Finder menu item is working
Given I navigate to CB Finder
Then I should see CB Finder page

Scenario: Create Target menu item is working
Given I navigate to Create Target
Then I should see Create Target page


Scenario: Funnel menu item is working
Given I navigate to Funnel
Then The page heading should be (Funnel Search)

Scenario: Sandbox menu item is working
Given I navigate to Sandbox
Then The page heading should be (Sandbox)



Scenario: My Records menu item is working
Given I navigate to My Records
Then I should see My Records page

Scenario: OrgUnit Records menu item is working
Given I navigate to OrgUnit Records
Given I setup Search Authorization
Then I should see OrgUnit Records page

Scenario: Workloads menu item is working
Given I navigate to Workloads
Then The page heading should be (Workloads)

Scenario: Profile Dashboard menu item is working
Given I navigate to Profile Dashboard
Then The page heading should be (Profile Dashboard)

Scenario: Performance Dashboard menu item is working
Given I navigate to User Performance Dashboard
Then The page heading should be (User Performance Dashboard)

Scenario: IM Dashboard menu item is working
Given I navigate to IM Dashboard
Then The page heading should be (IM Dashboard)

Scenario: Custom Dashboards menu item is working
Given I navigate to Custom Dashboards
Then The page heading should be (Custom Dashboards)


Scenario: Teams And Users menu item is working
Given I navigate to Org Units And Users
Then The page heading should be (Org Units And Users)

Scenario: Title Management menu item is working
Given I navigate to Title Management
Then The page heading should be (Title Management)

Scenario: Responsibilities menu item is working
Given I navigate to Responsibilities
Then The page heading should be (Responsibilities)

Scenario: Privilege Auditing menu item is working
Given I navigate to User Privilege Auditing
Then The page heading should be (User Privilege Auditing)

Scenario: Upload Data menu item is working
Given I navigate to Upload Data
Then The page heading should be (Upload Data)

Scenario: Search Data Files menu item is working
Given I navigate to Search Data Files
Then The page heading should be (Search Data Files)

Scenario: Data Sub-Source Management menu item is working
Given I navigate to Data Sub-Source Management
Then The page heading should be (Data Sub-Source Management)

Scenario: Designations List menu item is working
Given I navigate to Designations List
Then The page heading should be (Designations List)

Scenario: Designations Mapping menu item is working
Given I navigate to Designations Mapping
Then The page heading should be (Designations Mapping)

Scenario: Whitelist menu item is working
Given I navigate to Whitelist
Then The page heading should be (Whitelist)

Scenario: Tag Managemen menu item is working
Given I navigate to Tag Management
Then The page heading should be (Tag Management)
