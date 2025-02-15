Meta:

Scenario: Check values of single value fields in the master report
Then I should see Classification (<ReportClassifInit>) in master report
Then I should see Created For (<ReportCreatedForInit>) in master report
Then I should see Subject (<ReportSubject>) in master report
Then I should see Overview (<ReportOverview>) in master report
Then I should see Results (<ReportResult>) in master report
Then I should see Notes (|<ExpectedNotes>|) in operator report
