Meta:
@UI
@stage
@advancesearch


Lifecycle:
Before:
Scope: SCENARIO
Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login_analyst.table
After:
Scope: SCENARIO
Outcome: ANY
Given I Sign Out





Scenario: Advance Search - User can do simple advance search
Meta: @L0  @test C83440
Given I navigate to Advanced Search
Then I should see Advanced Search page
When I enter search criteria (and) on the Advance Search Page
Then I build search query on the Advance Search page
When I start search on the Advance Search page
Given I setup Search Authorization
When I open Card View
Then I should see at least 1 of SIGINT events on current view



