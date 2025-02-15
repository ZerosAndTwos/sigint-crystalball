Meta:
@API
@component Information Management
@story rfa
@nightly

Lifecycle:
Before:
Scope: SCENARIO
Given I sign in as user with all permissions except: DATA_AUDIO_CONTENT_ACCESS
When I send create finder file request
Then Request is successful
Given Clear context

Scenario: Initial Draft: Send for Approval
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate RFA number request
Then Request is successful

When I get allowed RFA actions
Then Request is successful

When I send get owner a RFA in Send for Approval request
Then Request is successful

When I send Send for Approval a RFA request
Then Request is successful
And RFA is Awaiting Approval and INITIAL

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event |  |   type:"CALL" AND eventTime:[$NOW-90d..$NOW] AND senderCountry:"AE" AND receiverCountry:"AE" AND HAS_VPRINT:"true"   | 0 | 1000 |

Scenario: Initial Draft: Save as Draft a RFA
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate RFA number request
Then Request is successful

When I get allowed RFA actions
Then Request is successful

When I send Save as Draft a RFA request
Then Request is successful
And RFA is Initial Draft and INITIAL

When I send View a RFA request
Then Request is successful

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event |  |   type:"CALL" AND eventTime:[$NOW-90d..$NOW] AND senderCountry:"AE" AND receiverCountry:"AE" AND HAS_VPRINT:"true"   | 0 | 1000 |

Scenario: Initial Draft: Delete a RFA
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate RFA number request
Then Request is successful

When I get allowed RFA actions
Then Request is successful

When I send Save as Draft a RFA request
Then Request is successful
And RFA is Initial Draft and INITIAL

When I send View a RFA request
Then Request is successful

When I send Delete a RFA request
Then Request is successful

When I send View a RFA request
Then Request is unsuccessful

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event |  |   type:"CALL" AND eventTime:[$NOW-90d..$NOW] AND senderCountry:"AE" AND receiverCountry:"AE" AND HAS_VPRINT:"true"   | 0 | 1000 |

Scenario: Awaiting Approval: Take ownership
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate RFA number request
Then Request is successful

When I get allowed RFA actions
Then Request is successful

When I send Save as Draft a RFA request
Then Request is successful
And RFA is Initial Draft and INITIAL

When I get allowed RFA actions
Then Request is successful

When I send get owner a RFA in Send for Approval request
Then Request is successful

When I send Send for Approval a RFA request
Then Request is successful
And RFA is Awaiting Approval and INITIAL

Given I sign in as admin user

When I get allowed RFA actions
Then Request is successful

When I send get owner a RFA in Take Ownership request
Then Request is successful

When I send Take Ownership a RFA request
Then Request is successful
And RFA is Under Approval and IN_PROGRESS

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event |  |   type:"CALL" AND eventTime:[$NOW-90d..$NOW] AND senderCountry:"AE" AND receiverCountry:"AE" AND HAS_VPRINT:"true"   | 0 | 1000 |

Scenario: Awaiting Approval: Edit
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate RFA number request
Then Request is successful

When I get allowed RFA actions
Then Request is successful

When I send Save as Draft a RFA request
Then Request is successful
And RFA is Initial Draft and INITIAL

When I get allowed RFA actions
Then Request is successful

When I send get owner a RFA in Send for Approval request
Then Request is successful

When I send Send for Approval a RFA request
Then Request is successful
And RFA is Awaiting Approval and INITIAL

When I get allowed RFA actions
Then Request is successful

When I send Save a RFA request
Then Request is successful

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event |  |   type:"CALL" AND eventTime:[$NOW-90d..$NOW] AND senderCountry:"AE" AND receiverCountry:"AE" AND HAS_VPRINT:"true"   | 0 | 1000 |

Scenario: Awaiting Approval: Assign
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate RFA number request
Then Request is successful

When I get allowed RFA actions
Then Request is successful

When I send Save as Draft a RFA request
Then Request is successful
And RFA is Initial Draft and INITIAL

When I get allowed RFA actions
Then Request is successful

When I send get owner a RFA in Send for Approval request
Then Request is successful

When I send Send for Approval a RFA request
Then Request is successful
And RFA is Awaiting Approval and INITIAL

Given I sign in as admin user

When I get allowed RFA actions
Then Request is successful

When I send get owner a RFA in Assign request
Then Request is successful

When I send Assign a RFA request
Then Request is successful
And RFA is Under Approval and IN_PROGRESS

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event |  |   type:"CALL" AND eventTime:[$NOW-90d..$NOW] AND senderCountry:"AE" AND receiverCountry:"AE" AND HAS_VPRINT:"true"   | 0 | 1000 |

Scenario: Awaiting Approval: Approve
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate RFA number request
Then Request is successful

When I get allowed RFA actions
Then Request is successful

When I send Save as Draft a RFA request
Then Request is successful
And RFA is Initial Draft and INITIAL

When I get allowed RFA actions
Then Request is successful

When I send get owner a RFA in Send for Approval request
Then Request is successful

When I send Send for Approval a RFA request
Then Request is successful
And RFA is Awaiting Approval and INITIAL

Given I sign in as admin user

When I get allowed RFA actions
Then Request is successful

When I send get owner a RFA in Take Ownership request
Then Request is successful

When I send Take Ownership a RFA request
Then Request is successful
And RFA is Under Approval and IN_PROGRESS

When I get allowed RFA actions
Then Request is successful

When I send get owner a RFA in Approve request
Then Request is successful

When I send Approve a RFA request
Then Request is successful
And RFA is Approved and FINAL

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event |  |   type:"CALL" AND eventTime:[$NOW-90d..$NOW] AND senderCountry:"AE" AND receiverCountry:"AE" AND HAS_VPRINT:"true"   | 0 | 1000 |

Scenario: Awaiting Approval: Re-assign
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate RFA number request
Then Request is successful

When I get allowed RFA actions
Then Request is successful

When I send Save as Draft a RFA request
Then Request is successful
And RFA is Initial Draft and INITIAL

When I get allowed RFA actions
Then Request is successful

When I send get owner a RFA in Send for Approval request
Then Request is successful

When I send Send for Approval a RFA request
Then Request is successful
And RFA is Awaiting Approval and INITIAL

Given I sign in as admin user

When I get allowed RFA actions
Then Request is successful

When I send get owner a RFA in Take Ownership request
Then Request is successful

When I send Take Ownership a RFA request
Then Request is successful
And RFA is Under Approval and IN_PROGRESS

When I get allowed RFA actions
Then Request is successful

When I send get owner a RFA in Re-assign request
Then Request is successful

When I send Re-assign a RFA request
Then Request is successful
And RFA is Under Approval and IN_PROGRESS

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event |  |   type:"CALL" AND eventTime:[$NOW-90d..$NOW] AND senderCountry:"AE" AND receiverCountry:"AE" AND HAS_VPRINT:"true"   | 0 | 1000 |

Scenario: Awaiting Approval: Reject
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate RFA number request
Then Request is successful

When I get allowed RFA actions
Then Request is successful

When I send Save as Draft a RFA request
Then Request is successful
And RFA is Initial Draft and INITIAL

When I get allowed RFA actions
Then Request is successful

When I send get owner a RFA in Send for Approval request
Then Request is successful

When I send Send for Approval a RFA request
Then Request is successful
And RFA is Awaiting Approval and INITIAL

Given I sign in as admin user

When I get allowed RFA actions
Then Request is successful

When I send get owner a RFA in Take Ownership request
Then Request is successful

When I send Take Ownership a RFA request
Then Request is successful
And RFA is Under Approval and IN_PROGRESS

When I get allowed RFA actions
Then Request is successful

When I send get owner a RFA in Reject request
Then Request is successful

When I send Reject a RFA request
Then Request is successful
And RFA is Rejected and FINAL

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event |  |   type:"CALL" AND eventTime:[$NOW-90d..$NOW] AND senderCountry:"AE" AND receiverCountry:"AE" AND HAS_VPRINT:"true"   | 0 | 1000 |

Scenario: Awaiting Approval: Edit
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate RFA number request
Then Request is successful

When I get allowed RFA actions
Then Request is successful

When I send Save as Draft a RFA request
Then Request is successful
And RFA is Initial Draft and INITIAL

When I get allowed RFA actions
Then Request is successful

When I send get owner a RFA in Send for Approval request
Then Request is successful

When I send Send for Approval a RFA request
Then Request is successful
And RFA is Awaiting Approval and INITIAL

Given I sign in as admin user

When I get allowed RFA actions
Then Request is successful

When I send get owner a RFA in Take Ownership request
Then Request is successful

When I send Take Ownership a RFA request
Then Request is successful
And RFA is Under Approval and IN_PROGRESS

When I get allowed RFA actions
Then Request is successful

When I send Save a RFA request
Then Request is successful

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event |  |   type:"CALL" AND eventTime:[$NOW-90d..$NOW] AND senderCountry:"AE" AND receiverCountry:"AE" AND HAS_VPRINT:"true"   | 0 | 1000 |


Scenario: Awaiting Approval: Unassign
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate RFA number request
Then Request is successful

When I get allowed RFA actions
Then Request is successful

When I send Save as Draft a RFA request
Then Request is successful
And RFA is Initial Draft and INITIAL

When I get allowed RFA actions
Then Request is successful

When I send get owner a RFA in Send for Approval request
Then Request is successful

When I send Send for Approval a RFA request
Then Request is successful
And RFA is Awaiting Approval and INITIAL

Given I sign in as admin user

When I get allowed RFA actions
Then Request is successful

When I send get owner a RFA in Take Ownership request
Then Request is successful

When I send Take Ownership a RFA request
Then Request is successful
And RFA is Under Approval and IN_PROGRESS

When I get allowed RFA actions
Then Request is successful

When I send get owner a RFA in Unassign request
Then Request is successful

When I send Unassign a RFA request
Then Request is successful
And RFA is Awaiting Approval and INITIAL

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event |  |   type:"CALL" AND eventTime:[$NOW-90d..$NOW] AND senderCountry:"AE" AND receiverCountry:"AE" AND HAS_VPRINT:"true"   | 0 | 1000 |


Scenario: User able get access to audio after approve RFA
When I save logged user

When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate RFA number request
Then Request is successful

When I get allowed RFA actions
Then Request is successful

When I send Save as Draft a RFA request
Then Request is successful
And RFA is Initial Draft and INITIAL

When I get allowed RFA actions
Then Request is successful

When I send get owner a RFA in Send for Approval request
Then Request is successful

When I send Send for Approval a RFA request
Then Request is successful
And RFA is Awaiting Approval and INITIAL

Given I sign in as admin user

When I get allowed RFA actions
Then Request is successful

When I send get owner a RFA in Take Ownership request
Then Request is successful

When I send Take Ownership a RFA request
Then Request is successful
And RFA is Under Approval and IN_PROGRESS

When I get allowed RFA actions
Then Request is successful

When I send get owner a RFA in Approve request
Then Request is successful

When I send Approve a RFA request
Then Request is successful
And RFA is Approved and FINAL

When I as login first user
Then Request is successful

When I send search for accessed audio request
Then Request is successful
Then Audio content is available
Then User able access to audio

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event |  |   type:"CALL" AND eventTime:[$NOW-90d..$NOW] AND senderCountry:"AE" AND receiverCountry:"AE" AND HAS_VPRINT:"true"   | 0 | 1000 |