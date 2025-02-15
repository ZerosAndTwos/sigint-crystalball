Meta:
@UI
@component Saved Search
@savesearch

Lifecycle:
Before:

Scope: SCENARIO
Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login.table



After:
Scope: SCENARIO
Outcome: ANY
Given I Sign Out





Scenario: Saved Search - User can see saved search list
Meta: @L0  @test  C83433

Given I navigate to Saved Searches
Then I should see at least 1 save search search result(s) on the Saved Searches page


Scenario: Saved Search - User can quick create and delete a saved search
Meta: @L0  @test  C83464
Given I navigate to Search
Then I should see Search page
When I enter search criteria (<SearchCriteria>) on the Search page
Given I open Search Filter on the Search page
Given load story ../aux-search-filter-stories/auxSearchFilterShowAllEvents.story
Given I set Data Source to (|<DataSource>|) on the Search Filter page
Given I set Record Type to (|<RecordType1>|<RecordType2>|) on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least <ResultsNumber> search result(s) on the current view
Given I press Save Search button on the Search Page
Then I should see Create Saved Search page
Then I should see Query (<SearchCriteria>) as Searching Criteria on the Create Saved Search page
Then I should see Source Type (|<SourceType>|) as Searching Criteria on the Create Saved Search page
Then I should see Data Source (|<DataSource>|) as Searching Criteria on the Create Saved Search page
Then I should see Object Type (|<ObjectType>|) as Searching Criteria on the Create Saved Search page
Then I should see Record Type (|<RecordType1>|<RecordType2>|) as Searching Criteria on the Create Saved Search page
Given I set Name to (<SavedSearchName>) on the Create Saved Search page
Given I set Classification to (<SavedSearchClassif>) on the Create Saved Search page
Given I set Organization Unit to (|<SavedSearchOrgUnit>|) on the Create Saved Search page
Given I set Files to (|<FileName>|) on the Create Saved Search page
Given I setup Scheduled Search to start from (<SchedFrom>) every (<SchedInterval>) till (<SchedTo>) on the Create Saved Search page
Given I save search on the Create Saved Search page

Given I navigate to CB Finder
When I select file with Name (<FileName>) in the CB Finder
Then I check if save search by name (<SavedSearchName>) is present under case/file which is currently selected in the CB Finder
When I open save search by name (<SavedSearchName>) is present under case/file which is currently selected in the CB Finder

Given I navigate to Saved Searches
When I enter search criteria (<SavedSearchName>) on the Saved Searches page
When I start search on the the Saved Searches page
Then I should see 1 card(s) on the Saved Searches page
Given I delete all cards displayed on the Saved Searches page



Examples:
data/SavedSearchesL0.data









Scenario: Verify that a user can save the search criteria as Saved Search in the system
Given load story ../aux-before-stories/auxCreateFile.story with example table:
data/SavedSearchesSmoke.data
Given load story ../aux-before-stories/auxCreateCase.story with example table:
data/SavedSearchesSmoke.data
Given I navigate to Search
Then I should see Search page
When I enter search criteria (<SearchCriteria>) on the Search page
Given I open Search Filter on the Search page
Given load story ../aux-search-filter-stories/auxSearchFilterShowAllEvents.story
Given I set Data Source to (|<DataSource>|) on the Search Filter page
Given I set Record Type to (|<RecordType1>|<RecordType2>|) on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least <ResultsNumber> search result(s) on the current view
Given I press Save Search button on the Search Page
Then I should see Create Saved Search page
Then I should see Query (<SearchCriteria>) as Searching Criteria on the Create Saved Search page
Then I should see Source Type (|<SourceType>|) as Searching Criteria on the Create Saved Search page
Then I should see Data Source (|<DataSource>|) as Searching Criteria on the Create Saved Search page
Then I should see Object Type (|<ObjectType>|) as Searching Criteria on the Create Saved Search page
Then I should see Record Type (|<RecordType1>|<RecordType2>|) as Searching Criteria on the Create Saved Search page
Given I set Name to (<SavedSearchName>) on the Create Saved Search page
Given I set Classification to (<SavedSearchClassif>) on the Create Saved Search page
Given I set Organization Unit to (|<SavedSearchOrgUnit>|) on the Create Saved Search page
Given I set Files to (|<FileName>|) on the Create Saved Search page
Given I set Files to (|<CaseName>|) on the Create Saved Search page
Given I setup Scheduled Search to start from (<SchedFrom>) every (<SchedInterval>) till (<SchedTo>) on the Create Saved Search page
Given I save search on the Create Saved Search page

Given I navigate to CB Finder
When I select case with Name (<CaseName>) in the CB Finder
Then I check if save search by name (<SavedSearchName>) is present under case/file which is currently selected in the CB Finder
When I open save search by name (<SavedSearchName>) is present under case/file which is currently selected in the CB Finder




Examples:
data/SavedSearchesSmoke.data


Scenario: Verify that a user can rerun and update Saved Search

Given I navigate to Search
Then I should see search criteria () on the Search page

Given I navigate to Saved Searches
When I enter search criteria (<SavedSearchName>) on the Saved Searches page
When I start search on the the Saved Searches page
Then I should see 1 card(s) on the Saved Searches page
Given I open for edit 1-st card displayed on the Saved Searches page
Then I should see Edit Saved Search page
Then I should see Query (<SearchCriteria>) as Searching Criteria on the Edit Saved Search page
Then I should see Source Type (|<SourceType>|) as Searching Criteria on the Edit Saved Search page
Then I should see Data Source (|<DataSource>|) as Searching Criteria on the Edit Saved Search page
Then I should see Object Type (|<ObjectType>|) as Searching Criteria on the Edit Saved Search page
Then I should see Record Type (|<RecordType1>|<RecordType2>|) as Searching Criteria on the Edit Saved Search page
Given I close Edit Saved Search page

Given I perform run of 1-st card displayed on the Saved Searches page

Given I setup Search Authorization
Then I should see search criteria (<SearchCriteria>) on the Search page
Then I should see at least <ResultsNumber> search result(s) on the current view

When I enter search criteria (<SearchCriteriaUpd>) on the Search page
Given I open Search Filter on the Search page
Given I set Object Type to Event on the Search Filter page
Given I set Data Source to (|<DataSourceUpd>|) on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least <ResultsNumberUpd> search result(s) on the current view
Given I press Update Saved Search button on the Search Page
Then I should see Edit Saved Search page
Then I should see Query (<SearchCriteriaUpd>) as Searching Criteria on the Edit Saved Search page
Then I should see Source Type (|<SourceType>|) as Searching Criteria on the Edit Saved Search page
Then I should see Data Source (|<DataSourceUpd>|) as Searching Criteria on the Edit Saved Search page
Then I should see Object Type (|<ObjectTypeUpd>|) as Searching Criteria on the Edit Saved Search page
Given I save search on the Edit Saved Search page

Given I navigate to Saved Searches
When I enter search criteria (<SavedSearchName>) on the Saved Searches page
When I start search on the the Saved Searches page
Then I should see 1 card(s) on the Saved Searches page
Given I open for edit 1-st card displayed on the Saved Searches page
Then I should see Edit Saved Search page
Then I should see Query (<SearchCriteriaUpd>) as Searching Criteria on the Edit Saved Search page
Then I should see Source Type (|<SourceType>|) as Searching Criteria on the Edit Saved Search page
Then I should see Data Source (|<DataSourceUpd>|) as Searching Criteria on the Edit Saved Search page
Then I should see Object Type (|<ObjectType>|) as Searching Criteria on the Edit Saved Search page
Given I close Edit Saved Search page



Examples:
data/SavedSearchesSmoke.data


Scenario: Verify that a user can delete Saved Search from the system

Given I navigate to Saved Searches
When I enter search criteria (<SavedSearchName>) on the Saved Searches page
When I start search on the the Saved Searches page
Given I delete all cards displayed on the Saved Searches page
Given I navigate to Saved Searches
When I enter search criteria (<SavedSearchName>) on the Saved Searches page
When I start search on the the Saved Searches page
Given I delete all cards displayed on the Saved Searches page

Given load story ../aux-after-stories/auxDeleteCase.story with example table:
data/SavedSearchesSmoke.data

Given load story ../aux-after-stories/auxDeleteFile.story with example table:
data/SavedSearchesSmoke.data

Examples:
data/SavedSearchesSmoke.data


