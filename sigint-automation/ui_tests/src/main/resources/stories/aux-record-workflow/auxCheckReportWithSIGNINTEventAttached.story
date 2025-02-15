Meta:

Scenario: Open draft report and check SIGNINT Event attached
Given I navigate to CB Finder
When I select file with Name (<FileName>) in the CB Finder
When I open operator report with Subject (<ReportSubj>) in file/case which is currently selected in the CB Finder
Then I should see that currently opened operator report has status (Initial Draft)
Then I should see card attached to currently opened operator report with Text Message (<SIGNINTEventText>) and all following label/value pair(s): |From Number|<SIGNINTEventPhoneFrom>|To Number|<SIGNINTEventPhoneTo>|
