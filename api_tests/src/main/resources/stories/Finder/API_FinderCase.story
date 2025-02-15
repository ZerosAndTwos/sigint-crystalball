Meta:
@API
@component Finder
@story FinderCase
@stage

Narrative:
CB-11288 Cases CRUD
1. User can create a case with the following attributes:
Case Name. Required.
Description. Optional.
File. Required.
Created on. Auto-populated.
Created by. Auto-populated.
Classification. Required.
OU. Required. [Hardcoded value for the scope of this story]
2. Navigation entry points as per https://confluence.pegasus.ae/display/INTEL/Crystal+Ball+Navigation+Components
3. User can edit a case.
4. User can delete an empty case.
5. A case can belong to only one file
6. A case must be the leaf node in a file hierarchy in the sense that a case cannot contain files or other cases.

Lifecycle:
Before:
Given I sign in as user with permissions FILE_CREATE, FILE_VIEW
When I send create finder file request
Then Request is successful
Then Created finder file is correct

Scenario: User could view case
Given I sign in as user with permissions FILE_VIEW, CASE_CREATE
When I send create finder case request
Then Request is successful
When View created finder case
Then Request is unsuccessful

Given I sign in as user with permissions FILE_VIEW, CASE_CREATE, CASE_VIEW
When I send create finder case request
Then Request is successful
When View created finder case
Then Request is successful

Scenario: User can create/delete a case
Given I sign in as user with permissions FILE_VIEW
When I send create finder case request
Then Request is unsuccessful

Given I sign in as user with permissions FILE_VIEW, CASE_CREATE, CASE_VIEW, CASE_DELETE
When I send create finder case request
Then Request is successful

Then Created finder case is correct
When I send delete finder case request
Then Request is successful

Scenario: User can edit a case
Given I sign in as user with permissions FILE_VIEW, CASE_CREATE, CASE_VIEW
When I send create finder case request
Then Request is successful
Then Created finder case is correct
When I send edit finder case request
Then Request is unsuccessful

Given I sign in as user with permissions FILE_VIEW, CASE_CREATE, CASE_VIEW, CASE_UPDATE
When I send create finder case request
Then Request is successful
Then Created finder case is correct
When I send edit finder case request
Then Request is successful
Then Created finder case is correct

Scenario: User can't create a case without name
Given I sign in as user with permissions FILE_VIEW, CASE_CREATE
When I send create finder case without name request
Then Request is unsuccessful

Scenario: User can't create a case without classification
Given I sign in as user with permissions FILE_VIEW, CASE_CREATE
When I send create finder case without classification request
Then Request is unsuccessful

Scenario: User can create a case without description
Given I sign in as user with permissions FILE_VIEW, CASE_CREATE
When I send create finder case without description request
Then Request is successful
Then Created finder case is correct


Scenario: User can't create case in case
Given I sign in as user with permissions FILE_VIEW, CASE_CREATE, CASE_VIEW
When I send create finder case request
Then Request is successful
Then Created finder case is correct
When I send create finder case in finder case request
Then Request is unsuccessful