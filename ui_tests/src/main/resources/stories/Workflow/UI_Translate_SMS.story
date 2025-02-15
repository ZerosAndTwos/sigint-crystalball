Meta:
@UI
@component Records workflow
@translate
@skip

Lifecycle:
Before:
Scope: SCENARIO
Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login.table

After:
Scope: SCENARIO
Outcome: ANY
Given I Sign Out

Scenario: Record - User is able to translate SMS events (Arabic to English)
Meta: @L0  @test  C83431
Given I navigate to My Records
When I enter search criteria (<SIGNTIMSI>) on the My Records page
Given I open Search Filter on the My Records page
Given I set date (01/01/2014) as Earliest Event Time on the Search Filter page
Given I open Search Filter on the My Records page
Given I open Search Filter on the My Records page
Given I Apply Search using Search Filter on the My Records page
Given I setup Search Authorization

When I open details of 1-st displayed SIGINT Event
Given I remove unofficial translation on the search result Details page
When I perform translation to English on the search result Details page
Then I should see unofficial translation in English on the search result Details page
Given I close search result Details page



Examples:
|SIGNTIMSI|translation|
|5345346546557|This is the test|


Scenario: Record - User is able to translate EMAIL events (Arabic to English)
Meta: @L0  @test  C83473
Given I navigate to My Records
When I enter search criteria (<SIGINTEMAIL>) on the My Records page
Given I open Search Filter on the My Records page
Given I set date (01/01/2014) as Earliest Event Time on the Search Filter page
Given I open Search Filter on the My Records page
Given I open Search Filter on the My Records page
Given I Apply Search using Search Filter on the My Records page
Given I setup Search Authorization

When I open details of 1-st displayed SIGINT Event
Given I remove unofficial translation on the search result Details page
When I perform translation to English on the search result Details page
Then I should see unofficial translation in English on the search result Details page
Given I close search result Details page



Examples:
|SIGINTEMAIL|translation|
|QETest@pegasus.ae|This is the test|



Scenario:Save Translated text- Arabic to english from My record page

Given I navigate to My Records
Given I open Search Filter on the My Records page
Given load story ../aux-search-filter-stories/auxSearchFilterForTextEventsOnArabic.story
Given I set date (01/01/2016) as Earliest Event Time on the Search Filter page
Given I Apply Search using Search Filter on the My Records page
Given I setup Search Authorization

When I open details of 1-st displayed SIGINT Event
Given I remove unofficial translation on the search result Details page
When I perform translation to English on the search result Details page
Then I should see unofficial translation in English on the search result Details page
When I save unofficial translation on the search result Details page
Given I close search result Details page
When I open details of 1-st displayed SIGINT Event
Then I should see unofficial translation in English on the search result Details page
Given I remove unofficial translation on the search result Details page
Given I close search result Details page


Scenario: Edit Translated text- Arabic to english from My record page

Given I navigate to My Records
Given I open Search Filter on the My Records page
Given load story ../aux-search-filter-stories/auxSearchFilterForTextEventsOnArabic.story
Given I set date (01/01/2016) as Earliest Event Time on the Search Filter page
Given I Apply Search using Search Filter on the My Records page
Given I setup Search Authorization
When I open details of 1-st displayed SIGINT Event
Given I remove unofficial translation on the search result Details page
When I perform translation to English on the search result Details page
Then I should see unofficial translation in English on the search result Details page
When I save unofficial translation on the search result Details page
Given I close search result Details page
When I open details of 1-st displayed SIGINT Event
Then I should see unofficial translation in English on the search result Details page
When I set unofficial translation on the search result Details page to (<translation>)
Given I close search result Details page
When I open details of 1-st displayed SIGINT Event
Then I should see (<translation>) as unofficial translation on the search result Details page
Given I remove unofficial translation on the search result Details page
Given I close search result Details page
Examples:
|translation|
|This is for autoamtion|

Scenario:Search using Translated text- Arabic to english
Given I navigate to My Records
Given I open Search Filter on the My Records page
Given load story ../aux-search-filter-stories/auxSearchFilterForTextEventsOnArabic.story
Given I set date (01/01/2016) as Earliest Event Time on the Search Filter page
Given I Apply Search using Search Filter on the My Records page
Given I setup Search Authorization
When I open details of 1-st displayed SIGINT Event
Given I remove unofficial translation on the search result Details page
When I perform translation to English on the search result Details page
When I set unofficial translation on the search result Details page to (<translation>)
Given I wait until search index is updated
Given I close search result Details page


Given I navigate to Search
When I enter search criteria (<translation>) on the Search page
Given I open Search Filter on the Search page
Given load story ../aux-search-filter-stories/auxSearchFilterForTextEventsOnArabic.story
Given load story ../aux-search-filter-stories/auxSearchFilterShowAllEvents.story
Given I Apply Search using Search Filter on the Search page

Then I should see at least 1 of SIGINT events on current view
When I open details of 1-st displayed SIGINT Event
Then I should see (<translation>) as unofficial translation on the search result Details page
Given I close search result Details page

Given I navigate to My Records
When I enter search criteria (<translation>) on the My Records page
When I start search on My Records page
Then I should see at least 1 of SIGINT events on current view
When I open details of 1-st displayed SIGINT Event
Then I should see (<translation>) as unofficial translation on the search result Details page
Given I remove unofficial translation on the search result Details page
Given I close search result Details page

Examples:
|translation|
|This is for autoamtion|


Scenario:Translate SMS text- Arabic to english from Search page
Given I navigate to Search
Given I open Search Filter on the Search page
Given load story ../aux-search-filter-stories/auxSearchFilterForTextEventsOnArabic.story
Given load story ../aux-search-filter-stories/auxSearchFilterShowAllEvents.story
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 of SIGINT events on current view
When I open details of 1-st displayed SIGINT Event
When I perform translation to English on the search result Details page
Then I should see unofficial translation in English on the search result Details page
Given I close search result Details page

