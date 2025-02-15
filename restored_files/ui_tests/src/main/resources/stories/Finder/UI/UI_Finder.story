
Lifecycle:
Before:
Given load story ../../aux-main-stories/auxSignIn.story with example table:
data/QE_login.table



Scenario: Verfiy search page
Given I navigate to Search

Given I open Search Filter on the Search page
Given I set Search Tab to General on the Search Filter page
Given I set Source Type to Profiler on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view


Given I open Search Filter on the Search page
Given I set Source Type to Documents on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view


Given I open Search Filter on the Search page
Given I set Source Type to SIGINT on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view


Given I open Search Filter on the Search page
Given I set Source Type to EID on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view

Given I open Search Filter on the Search page
Given I set Source Type to GOVINT on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view


Given I open Search Filter on the Search page
Given I set Source Type to FININT on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view

Given I open Search Filter on the Search page
Given I set Source Type to Traffic on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view

Given I open Search Filter on the Search page
Given I set Source Type to OSINT on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view

Given I open Search Filter on the Search page
Given I set Source Type to CIO on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view



Scenario: Verfiy search page
Given I navigate to Search
Given I open Search Filter on the Search page
Given I set Source Type to Documents on the Search Filter page
Given I set Record Type to (|<RecordType>|) on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view

Examples:
|RecordType|
|Operator Report|
|Request For Approval|


Scenario: Verfiy search page
Given I navigate to Search
Given I open Search Filter on the Search page
Given I set Source Type to SIGINT on the Search Filter page
Given I set Data Source to (|<DataSource>|) on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view

Examples:
|DataSource|
|du|
|Etisalat|
|F|
|J1|
|J2|
|Manual|
|O|
|Phonebook|
|S|
|T|



Scenario: Verfiy search page
Given I navigate to Search
Given I open Search Filter on the Search page
Given I set Source Type to SIGINT on the Search Filter page
Given I set Data Source to (|<DataSource>|) on the Search Filter page
Given I set Object Type to Event on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view

Examples:
|DataSource|
|du|
|Etisalat|
|F|
|J1|
|J2|
|Manual|
|O|
|Phonebook|
|S|
|T|



Scenario: Verfiy search page
Meta:@fix1
Given I navigate to Search
Given I open Search Filter on the Search page
Given I set Source Type to SIGINT on the Search Filter page
Given I set Data Source to (|<DataSource>|) on the Search Filter page
Given I set Object Type to Entity on the Search Filter page
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization
Then I should see at least 1 search result(s) on the current view

Examples:
|DataSource|
|du|
|Etisalat|
|F|
|J1|
|J2|
|Manual|
|O|
|Phonebook|
|S|
|T|

Scenario: Verfiy search pageq
Given I set Search Tab to Files on the Search Filter page
Given I set Search Tab to Record Assessment on the Search Filter page



Given I set Object Type to All on the Search Filter page
Given I set Object Type to Entity on the Search Filter page
Given I set Object Type to Event on the Search Filter page

Scenario: Verfiy search page

Given I navigate to Search
