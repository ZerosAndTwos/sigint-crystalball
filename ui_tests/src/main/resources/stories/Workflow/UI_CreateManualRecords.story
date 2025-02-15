Meta:
@UI
@component Records workflow
@manualrecord
@stage
Lifecycle:
After:
Scope: SCENARIO
Outcome: ANY
Given I Sign Out



Scenario: Record - User is able to create a manual record
Meta: @L0 @test  C83429


Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_operator.table

Given I navigate to My Records
Given I start creation of new record identified as (<RecordID>) on the My Records page
Given I set Record Type to (SMS) in the Manual Event Record dialog
Given I set Date and Time to (<RecordDate>) in the Manual Event Record dialog
Given I set Language to (Esperanto) in the Manual Event Record dialog
Given I generate and set random From Number in the Manual Event Record dialog
Given I generate and set random To Number in the Manual Event Record dialog
Given I set From Country to (Antarctica) in the Manual Event Record dialog
Given I set To Country to (Finland) in the Manual Event Record dialog
Given I generate and set random TMSI in the Manual Event Record dialog
Given I generate and set random IMSI in the Manual Event Record dialog
Given I generate and set random Record ID in the Manual Event Record dialog
Given I generate and set random SMS Text in the Manual Event Record dialog
Given I save record created in the Manual Event Record dialog

Given I navigate to My Records
When I use IMSI from manually created record identified as (<RecordID>) as search criteria on the Record page
Given I open Search Filter on the Team Records page
Given I Apply Search using Search Filter on the Team Records page
Given I setup Search Authorization

Then I should see 1 SIGINT event(s) on current view

When I open details of 1-st displayed SIGINT Event
Then I should see fields from manually created record identified as (<RecordID>) on the search result Details page
Given I close search result Details page

When I select 1-st displayed SIGINT event card
When I unassign selected items

Given I navigate to Search
When I use IMSI from manually created record identified as (<RecordID>) as search criteria on the Search page
Given I open Search Filter on the Search page
Given load story ../aux-manual-records-stories/auxSetupSearchFilterForManualRecord.story
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization

When I open Card View
Then I should see 1 search result(s) on the current view

When I open details of 1-st displayed card
Then I should see fields from manually created record identified as (<RecordID>) on the search result Details page
Given I close search result Details page

Examples:
|RecordID |RecordDate                                    |
|RecordSMS|{current_day}-2/{current_month}/{current_year}|




Scenario: Verify that a User can create a manual record with "Voice"-type
Meta: @devsmoke
Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_manager.table


Given I navigate to My Records
Given I start creation of new record identified as (<RecordID>) on the My Records page
Given I set Record Type to (Voice) in the Manual Event Record dialog
Given I set Language to (Bambara) in the Manual Event Record dialog
Given I generate and set random From Number in the Manual Event Record dialog
Given I generate and set random To Number in the Manual Event Record dialog
Given I set From Country to (British Indian Ocean Territory) in the Manual Event Record dialog
Given I set To Country to (British Virgin Islands) in the Manual Event Record dialog
Given I generate and set random TMSI in the Manual Event Record dialog
Given I generate and set random IMSI in the Manual Event Record dialog
Given I generate and set random Record ID in the Manual Event Record dialog
Given I set Audio Duration to (852) in the Manual Event Record dialog
Given I set Date and Time to (<RecordDate>) in the Manual Event Record dialog
Given I save record created in the Manual Event Record dialog

Given I navigate to My Records

When I use IMSI from manually created record identified as (<RecordID>) as search criteria on the Record page
Given I open Search Filter on the Team Records page
Given I Apply Search using Search Filter on the Team Records page
Given I setup Search Authorization

Then I should see 1 SIGINT event(s) on current view
When I open details of 1-st displayed SIGINT Event
Then I should see fields from manually created record identified as (<RecordID>) on the search result Details page
Given I close search result Details page

When I select 1-st displayed SIGINT event card
When I unassign selected items

Given I navigate to Search
When I use IMSI from manually created record identified as (<RecordID>) as search criteria on the Search page
Given I open Search Filter on the Search page
Given load story ../aux-manual-records-stories/auxSetupSearchFilterForManualRecord.story
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization

When I open Card View
Then I should see 1 search result(s) on the current view

When I open details of 1-st displayed card
Then I should see fields from manually created record identified as (<RecordID>) on the search result Details page
Given I close search result Details page

Examples:
|RecordID   |RecordDate                                    |
|RecordVoice|{current_day}-1/{current_month}/{current_year}|

Scenario: Verify that a User can create a manual record with "SMS"-type

Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_operator.table

Given I navigate to My Records
Given I start creation of new record identified as (<RecordID>) on the My Records page
Given I set Record Type to (SMS) in the Manual Event Record dialog
Given I set Date and Time to (<RecordDate>) in the Manual Event Record dialog
Given I set Language to (Esperanto) in the Manual Event Record dialog
Given I generate and set random From Number in the Manual Event Record dialog
Given I generate and set random To Number in the Manual Event Record dialog
Given I set From Country to (Antarctica) in the Manual Event Record dialog
Given I set To Country to (Finland) in the Manual Event Record dialog
Given I generate and set random TMSI in the Manual Event Record dialog
Given I generate and set random IMSI in the Manual Event Record dialog
Given I generate and set random Record ID in the Manual Event Record dialog
Given I generate and set random SMS Text in the Manual Event Record dialog
Given I save record created in the Manual Event Record dialog

Given I navigate to My Records
When I use IMSI from manually created record identified as (<RecordID>) as search criteria on the Record page
Given I open Search Filter on the Team Records page
Given I Apply Search using Search Filter on the Team Records page
Given I setup Search Authorization

Then I should see 1 SIGINT event(s) on current view

When I open details of 1-st displayed SIGINT Event
Then I should see fields from manually created record identified as (<RecordID>) on the search result Details page
Given I close search result Details page

When I select 1-st displayed SIGINT event card
When I unassign selected items

Given I navigate to Search
When I use IMSI from manually created record identified as (<RecordID>) as search criteria on the Search page
Given I open Search Filter on the Search page
Given load story ../aux-manual-records-stories/auxSetupSearchFilterForManualRecord.story
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization

When I open Card View
Then I should see 1 search result(s) on the current view

When I open details of 1-st displayed card
Then I should see fields from manually created record identified as (<RecordID>) on the search result Details page
Given I close search result Details page

Examples:
|RecordID |RecordDate                                    |
|RecordSMS|{current_day}-2/{current_month}/{current_year}|

Scenario: Verify that a User can create a manual record with "Fax"-type

Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_operator.table

Given I navigate to My Records
Given I start creation of new record identified as (<RecordID>) on the My Records page
Given I set Record Type to (Fax) in the Manual Event Record dialog
Given I set Date and Time to (<RecordDate>) in the Manual Event Record dialog
Given I generate and set random Record ID in the Manual Event Record dialog
Given I generate and set random From Number in the Manual Event Record dialog
Given I generate and set random To Number in the Manual Event Record dialog
Given I set From Country to (Caribbean Netherlands) in the Manual Event Record dialog
Given I set To Country to (Christmas Island) in the Manual Event Record dialog
Given I generate and set random Attachment Details in the Manual Event Record dialog
Given I save record created in the Manual Event Record dialog




Given I navigate to My Records
When I use From Number from manually created record identified as (<RecordID>) as search criteria on the Record page
Given I open Search Filter on the Team Records page
Given I Apply Search using Search Filter on the Team Records page
Given I setup Search Authorization
Then I should see 1 SIGINT event(s) on current view
When I open details of 1-st displayed SIGINT Event
Then I should see fields from manually created record identified as (<RecordID>) on the search result Details page
Given I close search result Details page

When I select 1-st displayed SIGINT event card
When I unassign selected items

Given I navigate to Search
When I use From Number from manually created record identified as (<RecordID>) as search criteria on the Search page
Given I open Search Filter on the Search page
Given load story ../aux-manual-records-stories/auxSetupSearchFilterForManualRecord.story
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization

When I open Card View

When I open details of 1-st displayed card
Then I should see fields from manually created record identified as (<RecordID>) on the search result Details page
Given I close search result Details page

Examples:
|RecordID |RecordDate                                    |
|RecordFax|{current_day}-3/{current_month}/{current_year}|

Scenario: Verify that a User can create a manual record with "Email"-type
Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_operator.table

Given I navigate to My Records
Given I start creation of new record identified as (<RecordID>) on the My Records page
Given I set Record Type to (Email) in the Manual Event Record dialog
Given I set Language to (Inuktitut) in the Manual Event Record dialog
Given I set Date and Time to (<RecordDate>) in the Manual Event Record dialog
Given I generate and set random Record ID in the Manual Event Record dialog
Given I generate and set random From E-mail in the Manual Event Record dialog
Given I generate and set random To E-mail in the Manual Event Record dialog
Given I generate and set random Attachment Details in the Manual Event Record dialog
Given I save record created in the Manual Event Record dialog



Given I navigate to My Records
When I use To E-mail from manually created record identified as (<RecordID>) as search criteria on the Record page
Given I open Search Filter on the Team Records page
Given I Apply Search using Search Filter on the Team Records page
Given I setup Search Authorization

Then I should see 1 SIGINT event(s) on current view
When I open details of 1-st displayed card
Then I should see fields from manually created record identified as (<RecordID>) on the search result Details page
Given I close search result Details page
When I select 1-st displayed SIGINT event card
When I unassign selected items

Given I navigate to Search
When I use To E-mail from manually created record identified as (<RecordID>) as search criteria on the Search page
Given I open Search Filter on the Search page
Given load story ../aux-manual-records-stories/auxSetupSearchFilterForManualRecord.story
Given I Apply Search using Search Filter on the Search page
Given I setup Search Authorization

When I open Card View
Then I should see 1 search result(s) on the current view

When I open details of 1-st displayed card
Then I should see fields from manually created record identified as (<RecordID>) on the search result Details page
Given I close search result Details page

Examples:
|RecordID   |RecordDate                                    |
|RecordEmail|{current_day}-4/{current_month}/{current_year}|
