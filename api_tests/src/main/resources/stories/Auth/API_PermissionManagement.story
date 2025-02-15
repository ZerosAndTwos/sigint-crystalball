Meta:
@API
@component Auth
@story permissionManagement
@stage


Lifecycle:
Before:
Given I sign in as admin user


Scenario: API.Getting list of permissions
When I send get list of permission request
Then Request is successful
And Permission list size more than 0


Scenario: API.Getting list of responsibilities
When I send get list of responsibilities request
Then Request is successful
And Responsibilities list size more than 0


Scenario: API.Getting list of titles
When I send get list of titles request
Then Request is successful
And Titles list size more than 0


Scenario: API.Creation of new responsibility
When I send get list of permission request
Then Request is successful
And Permission list size more than 0

When I send create a new responsibility request
Then Request is successful
And Responsibility is correct

When I send delete responsibility request
Then Request is successful


Scenario: API.Deleting of responsibility
When I send get list of permission request
Then Request is successful
And Permission list size more than 0

When I send create a new responsibility request
Then Request is successful
And Responsibility is correct

When I send delete responsibility request
Then Request is successful

When I send get list of responsibilities request
Then Request is successful
And Responsibility is not in the list


Scenario: API.Updating of new responsibility
When I send get list of permission request
Then Request is successful
And Permission list size more than 0

When I send create a new responsibility request
Then Request is successful
And Responsibility is correct

When I send update responsibility request
Then Request is successful
And Responsibility is correct

When I send delete responsibility request
Then Request is successful


Scenario: API.Getting a responsibility by id
When I send get list of permission request
Then Request is successful
And Permission list size more than 0

When I send create a new responsibility request
Then Request is successful

When I send get responsibility details request
Then Request is successful
And Responsibility is correct

When I send delete responsibility request
Then Request is successful


Scenario: API.Creation of new title
When I send get list of responsibilities request
Then Request is successful
And Responsibilities list size more than 0

When I send create a new title request
Then Request is successful
And Title is correct

When I send delete title request
Then Request is successful


Scenario: API.Deleting of title
When I send get list of responsibilities request
Then Request is successful
And Responsibilities list size more than 0

When I send create a new title request
Then Request is successful
And Title is correct

When I send delete title request
Then Request is successful

When I send get list of titles request
Then Request is successful
And Title is not in the list


Scenario: API.Getting title by id
When I send get list of responsibilities request
Then Request is successful
And Responsibilities list size more than 0

When I send create a new title request
Then Request is successful

When I send get title details request
Then Request is successful
And Title is correct

When I send delete title request
Then Request is successful


Scenario: API.Updating of new title
When I send get list of responsibilities request
Then Request is successful
And Responsibilities list size more than 0

When I send create a new title request
Then Request is successful

When I send update title request
Then Request is successful
And Title is correct

When I send delete title request
Then Request is successful