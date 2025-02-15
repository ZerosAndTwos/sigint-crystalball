Meta:
@UI
@component Finder
@stage
@cbfinder


Lifecycle:
Before:
Scope: SCENARIO
Given load story ../aux-main-stories/auxSignIn.story with example table:
data/QE_login.table

After:
Scope: SCENARIO
Outcome: FAILURE
Given load story ../aux-after-stories/auxDeleteFile.story with example table:
data/CB_finder.table

Outcome: ANY
Given I Sign Out

Scenario: Create a file
Meta:@devsmoke
Given I navigate to CB Finder
Given I start creation of new file in the CB Finder
Given I set Name (<FileName>) for new file in the CB Finder
Given I set Classification (<FileCalssif>) for new file in the CB Finder
Given I set Organization Units (|<FileOrgUnit>|) for new file in the CB Finder
Given I save new file created in the CB Finder

Examples:
data/CB_finder.table



Scenario: Create a Case
Given I navigate to CB Finder
When I select file with Name (<FileName>) in the CB Finder
Given I start creation of a case from file which is currently selected in the CB Finder
Given I set Name (<CaseName>) for new case in the CB Finder
Given I set Classification (<FileCalssif>) for new case in the CB Finder
Given I set Organization Units (|<FileOrgUnit>|) for new case in the CB Finder
Given I save new case created in the CB Finder


Examples:
data/CB_finder.table



Scenario: Delete an empty case
Given I navigate to CB Finder
When I select case with Name (<CaseName>) in the CB Finder
When I delete case which is currently selected in the CB Finder

Examples:
data/CB_finder.table

Scenario: Delete an empty file
Meta:@devsmoke
Given I navigate to CB Finder
When I select file with Name (<FileName>) in the CB Finder
When I delete file which is currently selected in the CB Finder

Examples:
data/CB_finder.table