
Scenario: Create new empty case
When I select file with Name (<FileName>) in the CB Finder
Given I start creation of a case from file which is currently selected in the CB Finder
Given I set Name (<CaseName>) for new case in the CB Finder
Given I set Classification (<FileCalssif>) for new case in the CB Finder
Given I set Organization Units (|<FileOrgUnit>|) for new case in the CB Finder
Given I save new case created in the CB Finder

