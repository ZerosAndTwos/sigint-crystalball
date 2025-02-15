Meta:
@API
@component Information Management
@story master report

Lifecycle:
Before:
Given I sign in as admin user
When I send create finder file request
Then Request is successful

Scenario: Create/View a Master Report
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate master report number request
Then Request is successful

When I get allowed master report actions
Then Request is successful

When I send Save as Draft a master report request
Then Request is successful
And Master report is Initial Draft and INITIAL

When I send View a master report request
Then Request is successful

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| INFORMATION_MANAGEMENT | entity | EventVO |   eventFeed:"INFORMATION_MANAGEMENT" AND type:"OperatorReport" AND includeSpam:"false" AND state:"Approved"   | 0 | 100 |

Scenario: Draft: Delete a Master Report
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate master report number request
Then Request is successful

When I get allowed master report actions
Then Request is successful

When I send Save as Draft a master report request
Then Request is successful
And Master report is Initial Draft and INITIAL

When I send Delete a master report request
Then Request is successful

When I send View a master report request
Then Request is unsuccessful

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| INFORMATION_MANAGEMENT | entity | EventVO |   eventFeed:"INFORMATION_MANAGEMENT" AND type:"OperatorReport" AND includeSpam:"false" AND state:"Approved"   | 0 | 10 |

Scenario: Draft: Edit a Master Report
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate master report number request
Then Request is successful

When I get allowed master report actions
Then Request is successful

When I send Save as Draft a master report request
Then Request is successful
And Master report is Initial Draft and INITIAL

When I get allowed master report actions
Then Request is successful

When I send Save a master report request
Then Request is successful
Then Master report is edited

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| INFORMATION_MANAGEMENT | entity | EventVO |   eventFeed:"INFORMATION_MANAGEMENT" AND type:"OperatorReport" AND includeSpam:"false" AND state:"Approved"   | 0 | 10 |

Scenario: Draft: Submit a Master Report
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate master report number request
Then Request is successful

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Submit for Review request
Then Request is successful

When I send Submit for Review a master report request
Then Request is successful
And Master report is Awaiting Review and INITIAL

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| INFORMATION_MANAGEMENT | entity | EventVO |   eventFeed:"INFORMATION_MANAGEMENT" AND type:"OperatorReport" AND includeSpam:"false" AND state:"Approved"   | 0 | 10 |

Scenario: Awaiting Review: Move Draft MR to Awaiting Review
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate master report number request
Then Request is successful

When I get allowed master report actions
Then Request is successful

When I send Save as Draft a master report request
Then Request is successful
And Master report is Initial Draft and INITIAL

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Submit for Review request
Then Request is successful

When I send Submit for Review a master report request
Then Request is successful
And Master report is Awaiting Review and INITIAL

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| INFORMATION_MANAGEMENT | entity | EventVO |   eventFeed:"INFORMATION_MANAGEMENT" AND type:"OperatorReport" AND includeSpam:"false" AND state:"Approved"   | 0 | 10 |

Scenario: Awaiting Review: Cancel Master Report
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate master report number request
Then Request is successful

When I get allowed master report actions
Then Request is successful

When I send Save as Draft a master report request
Then Request is successful
And Master report is Initial Draft and INITIAL

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Submit for Review request
Then Request is successful

When I send Submit for Review a master report request
Then Request is successful
And Master report is Awaiting Review and INITIAL

When I get allowed master report actions
Then Request is successful

When I send Cancel a master report request
Then Request is successful
And Master report is Cancelled and FINAL

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| INFORMATION_MANAGEMENT | entity | EventVO |   eventFeed:"INFORMATION_MANAGEMENT" AND type:"OperatorReport" AND includeSpam:"false" AND state:"Approved"   | 0 | 10 |

Scenario: Awaiting Review: Take ownership Master Report
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate master report number request
Then Request is successful

When I get allowed master report actions
Then Request is successful

When I send Save as Draft a master report request
Then Request is successful
And Master report is Initial Draft and INITIAL

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Submit for Review request
Then Request is successful

When I send Submit for Review a master report request
Then Request is successful
And Master report is Awaiting Review and INITIAL

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Take Ownership request
Then Request is successful

When I send Take Ownership a master report request
Then Request is successful
And Master report is Under Review and IN_PROGRESS

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| INFORMATION_MANAGEMENT | entity | EventVO |   eventFeed:"INFORMATION_MANAGEMENT" AND type:"OperatorReport" AND includeSpam:"false" AND state:"Approved"   | 0 | 10 |

Scenario: Awaiting Review: Assign Master Report
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate master report number request
Then Request is successful

When I get allowed master report actions
Then Request is successful

When I send Save as Draft a master report request
Then Request is successful
And Master report is Initial Draft and INITIAL

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Submit for Review request
Then Request is successful

When I send Submit for Review a master report request
Then Request is successful
And Master report is Awaiting Review and INITIAL

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Assign request
Then Request is successful

When I send Assign a master report request
Then Request is successful
And Master report is Under Review and IN_PROGRESS

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| INFORMATION_MANAGEMENT | entity | EventVO |   eventFeed:"INFORMATION_MANAGEMENT" AND type:"OperatorReport" AND includeSpam:"false" AND state:"Approved"   | 0 | 10 |

Scenario: Under Review: Unassign Master Report
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate master report number request
Then Request is successful

When I get allowed master report actions
Then Request is successful

When I send Save as Draft a master report request
Then Request is successful
And Master report is Initial Draft and INITIAL

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Submit for Review request
Then Request is successful

When I send Submit for Review a master report request
Then Request is successful
And Master report is Awaiting Review and INITIAL

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Take Ownership request
Then Request is successful

When I send Take Ownership a master report request
Then Request is successful
And Master report is Under Review and IN_PROGRESS

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Unassign request
Then Request is successful

When I send Unassign a master report request
Then Request is successful
And Master report is Awaiting Review and INITIAL

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| INFORMATION_MANAGEMENT | entity | EventVO |   eventFeed:"INFORMATION_MANAGEMENT" AND type:"OperatorReport" AND includeSpam:"false" AND state:"Approved"   | 0 | 10 |

Scenario: Under Review: Re-assign Master Report
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate master report number request
Then Request is successful

When I get allowed master report actions
Then Request is successful

When I send Save as Draft a master report request
Then Request is successful
And Master report is Initial Draft and INITIAL

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Submit for Review request
Then Request is successful

When I send Submit for Review a master report request
Then Request is successful
And Master report is Awaiting Review and INITIAL

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Take Ownership request
Then Request is successful

When I send Take Ownership a master report request
Then Request is successful
And Master report is Under Review and IN_PROGRESS

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Re-assign request
Then Request is successful

When I send Re-assign a master report request
Then Request is successful
And Master report is Under Review and IN_PROGRESS

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| INFORMATION_MANAGEMENT | entity | EventVO |   eventFeed:"INFORMATION_MANAGEMENT" AND type:"OperatorReport" AND includeSpam:"false" AND state:"Approved"   | 0 | 100 |

Scenario: Under Review: Return to Author Master Report
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate master report number request
Then Request is successful

When I get allowed master report actions
Then Request is successful

When I send Save as Draft a master report request
Then Request is successful
And Master report is Initial Draft and INITIAL

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Submit for Review request
Then Request is successful

When I send Submit for Review a master report request
Then Request is successful
And Master report is Awaiting Review and INITIAL

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Take Ownership request
Then Request is successful

When I send Take Ownership a master report request
Then Request is successful
And Master report is Under Review and IN_PROGRESS

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Return to Author request
Then Request is successful

When I send Return to Author a master report request
Then Request is successful
And Master report is Returned for Revision and IN_PROGRESS

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| INFORMATION_MANAGEMENT | entity | EventVO |   eventFeed:"INFORMATION_MANAGEMENT" AND type:"OperatorReport" AND includeSpam:"false" AND state:"Approved"   | 0 | 100 |

Scenario: Under Review: Approve Master Report
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate master report number request
Then Request is successful

When I get allowed master report actions
Then Request is successful

When I send Save as Draft a master report request
Then Request is successful
And Master report is Initial Draft and INITIAL


When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Submit for Review request
Then Request is successful

When I send Submit for Review a master report request
Then Request is successful
And Master report is Awaiting Review and INITIAL


When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Take Ownership request
Then Request is successful

When I send Take Ownership a master report request
Then Request is successful
And Master report is Under Review and IN_PROGRESS

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Approve request
Then Request is successful

When I send Approve a master report request
Then Request is successful
And Master report is Approved and FINAL

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| INFORMATION_MANAGEMENT | entity | EventVO |   eventFeed:"INFORMATION_MANAGEMENT" AND type:"OperatorReport" AND includeSpam:"false" AND state:"Approved"   | 0 | 100 |

Scenario: Under Review: Reject Master Report
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate master report number request
Then Request is successful

When I get allowed master report actions
Then Request is successful

When I send Save as Draft a master report request
Then Request is successful
And Master report is Initial Draft and INITIAL

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Submit for Review request
Then Request is successful

When I send Submit for Review a master report request
Then Request is successful
And Master report is Awaiting Review and INITIAL

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Take Ownership request
Then Request is successful

When I send Take Ownership a master report request
Then Request is successful
And Master report is Under Review and IN_PROGRESS

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Reject request
Then Request is successful

When I send Reject a master report request
Then Request is successful
And Master report is Rejected and FINAL

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| INFORMATION_MANAGEMENT | entity | EventVO |   eventFeed:"INFORMATION_MANAGEMENT" AND type:"OperatorReport" AND includeSpam:"false" AND state:"Approved"   | 0 | 100 |

Scenario: Under Review: Edit Master Report
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate master report number request
Then Request is successful

When I get allowed master report actions
Then Request is successful

When I send Save as Draft a master report request
Then Request is successful
And Master report is Initial Draft and INITIAL

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Submit for Review request
Then Request is successful

When I send Submit for Review a master report request
Then Request is successful
And Master report is Awaiting Review and INITIAL

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Take Ownership request
Then Request is successful

When I send Take Ownership a master report request
Then Request is successful
And Master report is Under Review and IN_PROGRESS

When I get allowed master report actions
Then Request is successful

When I send Save a master report request
Then Request is successful
Then Master report is edited

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| INFORMATION_MANAGEMENT | entity | EventVO |   eventFeed:"INFORMATION_MANAGEMENT" AND type:"OperatorReport" AND includeSpam:"false" AND state:"Approved"   | 0 | 100 |

Scenario: Under Review: Cancel Master Report
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate master report number request
Then Request is successful

When I get allowed master report actions
Then Request is successful

When I send Save as Draft a master report request
Then Request is successful
And Master report is Initial Draft and INITIAL

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Submit for Review request
Then Request is successful

When I send Submit for Review a master report request
Then Request is successful
And Master report is Awaiting Review and INITIAL

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Take Ownership request
Then Request is successful

When I send Take Ownership a master report request
Then Request is successful
And Master report is Under Review and IN_PROGRESS

When I get allowed master report actions
Then Request is successful

When I send Cancel a master report request
Then Request is successful
And Master report is Cancelled and FINAL

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| INFORMATION_MANAGEMENT | entity | EventVO |   eventFeed:"INFORMATION_MANAGEMENT" AND type:"OperatorReport" AND includeSpam:"false" AND state:"Approved"   | 0 | 1 |

Scenario: Returned to Author: Cancel Master Report
Meta:@skip
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate master report number request
Then Request is successful

When I get allowed master report actions
Then Request is successful

When I send Save as Draft a master report request
Then Request is successful
And Master report is Initial Draft and INITIAL

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Submit for Review request
Then Request is successful

When I send Submit for Review a master report request
Then Request is successful
And Master report is Awaiting Review and INITIAL

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Assign request
Then Request is successful

When I send Assign a master report request
Then Request is successful
And Master report is Under Review and IN_PROGRESS

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Return to Author request
Then Request is successful

When I send Return to Author a master report request
Then Request is successful
And Master report is Returned for Revision and IN_PROGRESS

When I get allowed master report actions
Then Request is successful

When I send Cancel a master report request
Then Request is successful
And Master report is Cancelled and FINAL

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| INFORMATION_MANAGEMENT | entity | EventVO |   eventFeed:"INFORMATION_MANAGEMENT" AND type:"OperatorReport" AND includeSpam:"false" AND state:"Approved"   | 0 | 100 |

Scenario: Returned to Author: Submit for review Master Report
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate master report number request
Then Request is successful

When I get allowed master report actions
Then Request is successful

When I send Save as Draft a master report request
Then Request is successful
And Master report is Initial Draft and INITIAL

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Submit for Review request
Then Request is successful

When I send Submit for Review a master report request
Then Request is successful
And Master report is Awaiting Review and INITIAL

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Take Ownership request
Then Request is successful

When I send Take Ownership a master report request
Then Request is successful
And Master report is Under Review and IN_PROGRESS

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Return to Author request
Then Request is successful

When I send Return to Author a master report request
Then Request is successful
And Master report is Returned for Revision and IN_PROGRESS

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Submit for Review request
Then Request is successful

When I send Submit for Review a master report request
Then Request is successful
And Master report is Awaiting Review and INITIAL

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| INFORMATION_MANAGEMENT | entity | EventVO |   eventFeed:"INFORMATION_MANAGEMENT" AND type:"OperatorReport" AND includeSpam:"false" AND state:"Approved"   | 0 | 100 |

Scenario: Returned to Author: Edir Master Report
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate master report number request
Then Request is successful

When I get allowed master report actions
Then Request is successful

When I send Save as Draft a master report request
Then Request is successful
And Master report is Initial Draft and INITIAL

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Submit for Review request
Then Request is successful

When I send Submit for Review a master report request
Then Request is successful
And Master report is Awaiting Review and INITIAL

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Take Ownership request
Then Request is successful

When I send Take Ownership a master report request
Then Request is successful
And Master report is Under Review and IN_PROGRESS

When I get allowed master report actions
Then Request is successful

When I send get owner a master report in Return to Author request
Then Request is successful

When I send Return to Author a master report request
Then Request is successful
And Master report is Returned for Revision and IN_PROGRESS

When I get allowed master report actions
Then Request is successful

When I send Save a master report request
Then Request is successful
Then Master report is edited

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| INFORMATION_MANAGEMENT | entity | EventVO |   eventFeed:"INFORMATION_MANAGEMENT" AND type:"OperatorReport" AND includeSpam:"false" AND state:"Approved"   | 0 | 1 |