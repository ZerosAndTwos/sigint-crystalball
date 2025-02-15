Meta:
@story phonebook


Scenario: API.Create PhoneBook with all fields
Meta:
@deprecated
Given I sign in as admin user
When I send create Phonebook Entry request with all fields
Then Request is successful
And Phonebook Entry is correct


Scenario: API.Get details of Phonebook Entry
Meta:
@deprecated
Given I sign in as admin user
When I send create Phonebook Entry request with all fields
Then Request is successful
When I get details of created Phonebook Entry
Then Phonebook Entry is correct


Scenario: API.Update existing Phonebook
Meta:
@deprecated
Given I sign in as admin user
When I send create Phonebook Entry request with all fields
Then Request is successful
When I send update request for created Phonebook Entry
Then Request is successful
And Phonebook Entry is correct


Scenario: API.Deleting existing Phonebook
Meta:
@deprecated
Given I sign in as admin user
When I send create Phonebook Entry request with all fields
Then Request is successful
When I send delete request for created Phonebook Entry
Then Request is successful
And Phonebook Entry was deleted


Scenario: API.User can find Phonebook Entry using search filters
Meta:
@deprecated
Given I sign in as admin user
When I send create Phonebook Entry request with all fields
Then Request is successful
When I search Phonebook Entry by <criteria> and value <value>
Then Search Phonebook results are correct
And Searched Phonebook Entry in list

Examples:
| criteria    | value  |
| queryString | random |
| address     | random |
| name        | random |
| phoneNumber | random |
| country     | random |
| countryCode | random |


Scenario: API.Upload multiple phonebooks
Meta:
@deprecated
Given I sign in as admin user
When I send upload phonebook request with CSV file containing 3 phonebooks
Then Request is successful
And Upload result of 3 'Phonebook' entries is successful


Scenario: API.Upload DuSubscriber Entry with all fields
Meta:
@deprecated

Given I sign in as admin user
When I send upload DuSubscriberEntry request with all fields
Then Request is successful
And Upload result of 1 'Phonebook' entries is successful
When I send search DuSubscribers by phoneNumber and value random
Then DuSubscriber search result are correct
And Searched DuSubscriber Entry in list


Scenario: API.Search DuSubscriber entries using search filters
Meta:
@deprecated
Given I sign in as admin user
When I send upload DuSubscriberEntry request with all fields
Then Request is successful
And Upload result of 1 'Phonebook' entries is successful
When I send search DuSubscribers by <criteria> and value <value>
Then DuSubscriber search result are correct
And Searched DuSubscriber Entry in list

Examples:
| criteria    | value  |
| queryString | random |
| address     | random |
| name        | random |
| phoneNumber | random |


Scenario: API.Get details of DuSubscriber entry
Meta:
@deprecated

Given I sign in as admin user
When I send upload DuSubscriberEntry request with all fields
Then Request is successful
And Upload result of 1 'Phonebook' entries is successful
When I send search DuSubscribers by phoneNumber and value random
Then DuSubscriber search result are correct
When I send get DuSubscriber Entry request
Then Request is successful
And DuSubscriber Entry is correct


Scenario: API.Upload EtisalatSubscriberData entry with all fields
Meta:
@deprecated
Given I sign in as admin user
When I send upload EtisalatSubscriberData entry request with all fields
Then Request is successful
And Upload result of 1 'Phonebook' entries is successful
When I send search EtisalatSubscriberData by phoneNumber and value random
Then EtisalatSubscriberData search result are correct
And Searched EtisalatSubscriberData Entry in list


Scenario: API.Upload multiple EtisalatSubscriber entries
Meta:
@deprecated
Given I sign in as admin user
When I send upload 5 EtisalatSubscriber entries request
Then Request is successful
And Upload result of 5 'Phonebook' entries is successful


Scenario: API.Search EtisalatSubscriberData entry using search filters
Meta:
@deprecated
Given I sign in as admin user
When I send upload EtisalatSubscriberData entry request with all fields
Then Request is successful
And Upload result of 1 'Phonebook' entries is successful
When I send search EtisalatSubscriberData by <criteria> and value <value>
Then EtisalatSubscriberData search result are correct
And Searched EtisalatSubscriberData Entry in list


Examples:
| criteria      | value  |
| queryString | random |
| address       | random |
| name          | random |
| phoneNumber   | random |
| useridOrName  | random |
| accountNameArabic | random |
| imsi          | random |
| firstAddressLine | random |
| secondAddressLine | random |
| cityName | random |


Scenario: API.Get details of EtisalatSubscriberData entry
Meta:
@deprecated
Given I sign in as admin user
When I send upload EtisalatSubscriberData entry request with all fields
Then Request is successful
And Upload result of 1 'Phonebook' entries is successful
When I send search EtisalatSubscriberData by phoneNumber and value random
Then EtisalatSubscriberData search result are correct
When I send get EtisalatSubscriberData Entry request
Then Request is successful
And EtisalatSubscriberData Entry is correct
