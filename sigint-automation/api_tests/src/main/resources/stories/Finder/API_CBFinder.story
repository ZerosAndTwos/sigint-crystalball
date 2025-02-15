Meta:
@API
@component Finder
@story CBFinder
@stage


Scenario: CB Finder classification permissions
Given I sign in as user with classifications: <classifications>
When I send get root list of CBFinder request
Then CB Finder entities list size > 0
Then CBFinder return items only for classifications: <classifications>

Examples:
| classifications |
| S |
| TS |


Scenario: CB Finder orgUnits permissions
Given I sign in as user with orgUnits: <orgunits>
When I send get root list of CBFinder request
Then CB Finder entities list size > 0
Then CBFinder should return items regarding current user orgUnits

Examples:
| orgunits |
| QA auto team |