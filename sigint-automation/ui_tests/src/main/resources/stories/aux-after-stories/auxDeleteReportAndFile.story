Meta:

Scenario: Delete File
Given I navigate to CB Finder
When I select file with Name (<FileName>) in the CB Finder
When I open operator report with Subject (<ReportSubject>) in file/case which is currently selected in the CB Finder
Given I Collapse the CB Finder view
When I delete operator report which is currently opened
Given I Expand the CB Finder view
When I select file with Name (<FileName>) in the CB Finder
When I delete file which is currently selected in the CB Finder