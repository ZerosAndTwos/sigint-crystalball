Meta:
@API
@component Information Management
@story operator report

Lifecycle:
Before:
Given I sign in as admin user
When I send create finder file request
Then Request is successful

Scenario: Initial Draft: Save as draft/View report
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate report number request
Then Request is successful

When I get allowed actions
Then Request is successful

When I send Save as Draft a report request
Then Request is successful
Then Operator report is Initial Draft and INITIAL

When I send view a report request
Then Request is successful

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event | EventVO |      | 0 | 150 |
| SIGINT | entity| EntityVO|      | 0 | 150 |

Scenario: Initial Draft: Submit
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate report number request
Then Request is successful

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Submit for Review request
Then Request is successful

When I send Submit for Review a report request
Then Request is successful
Then Operator report is Awaiting Review and INITIAL

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event | EventVO |      | 0 | 150 |
| SIGINT | entity| EntityVO|      | 0 | 150 |

Scenario: Initial Draft: Delete
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate report number request
Then Request is successful

When I get allowed actions
Then Request is successful

When I send Save as Draft a report request
Then Request is successful
And Report is created

When I send delete a report request
Then Request is successful

When I send view a report request
Then Request is unsuccessful

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event | EventVO |      | 0 | 150 |
| SIGINT | entity| EntityVO|      | 0 | 150 |

Scenario: Initial Draft: Edit
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate report number request
Then Request is successful

When I get allowed actions
Then Request is successful

When I send Save as Draft a report request
Then Request is successful
And Report is created

When I get allowed actions
Then Request is successful

When I send Save a report request
Then Request is successful

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event | EventVO |      | 0 | 150 |
| SIGINT | entity| EntityVO|      | 0 | 150 |

Scenario: Awaiting Review: Take Ownership
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate report number request
Then Request is successful

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Submit for Review request
Then Request is successful

When I send Submit for Review a report request
Then Request is successful
Then Operator report is Awaiting Review and INITIAL

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Take Ownership request
Then Request is successful

When I send Take Ownership a report request
Then Request is successful
Then Operator report is Under Review and IN_PROGRESS

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event | EventVO |      | 0 | 150 |
| SIGINT | entity| EntityVO|      | 0 | 150 |

Scenario: Awaiting Review: Assign
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate report number request
Then Request is successful

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Submit for Review request
Then Request is successful

When I send Submit for Review a report request
Then Request is successful
Then Operator report is Awaiting Review and INITIAL

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Assign request
Then Request is successful

When I send Assign a report request
Then Request is successful
Then Operator report is Under Review and IN_PROGRESS

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event | EventVO |      | 0 | 150 |
| SIGINT | entity| EntityVO|      | 0 | 150 |

Scenario: Awaiting Review: Cancel
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate report number request
Then Request is successful

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Submit for Review request
Then Request is successful

When I send Submit for Review a report request
Then Request is successful
Then Operator report is Awaiting Review and INITIAL

When I get allowed actions
Then Request is successful

When I send Cancel a report request
Then Request is successful
Then Operator report is Cancelled and FINAL

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event | EventVO |      | 0 | 150 |
| SIGINT | entity| EntityVO|      | 0 | 150 |

Scenario: Under Review: Unassign
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate report number request
Then Request is successful

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Submit for Review request
Then Request is successful

When I send Submit for Review a report request
Then Request is successful
Then Operator report is Awaiting Review and INITIAL

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Take Ownership request
Then Request is successful

When I send Take Ownership a report request
Then Request is successful
Then Operator report is Under Review and IN_PROGRESS

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Unassign request
Then Request is successful

When I send Unassign a report request
Then Request is successful
Then Operator report is Awaiting Review and INITIAL

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event | EventVO |      | 0 | 150 |
| SIGINT | entity| EntityVO|      | 0 | 150 |

Scenario: Under Review: Re-assign
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate report number request
Then Request is successful

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Submit for Review request
Then Request is successful

When I send Submit for Review a report request
Then Request is successful
Then Operator report is Awaiting Review and INITIAL

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Take Ownership request
Then Request is successful

When I send Take Ownership a report request
Then Request is successful
Then Operator report is Under Review and IN_PROGRESS

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Re-assign request
Then Request is successful

When I send Re-assign a report request
Then Request is successful
Then Operator report is Under Review and IN_PROGRESS

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event | EventVO |      | 0 | 150 |
| SIGINT | entity| EntityVO|      | 0 | 150 |

Scenario: Under Review: Return to Author
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate report number request
Then Request is successful

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Submit for Review request
Then Request is successful

When I send Submit for Review a report request
Then Request is successful
Then Operator report is Awaiting Review and INITIAL

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Take Ownership request
Then Request is successful

When I send Take Ownership a report request
Then Request is successful
Then Operator report is Under Review and IN_PROGRESS

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Return to Author request
Then Request is successful

When I send Return to Author a report request
Then Request is successful
Then Operator report is Returned for Revision and IN_PROGRESS

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event | EventVO |      | 0 | 150 |
| SIGINT | entity| EntityVO|      | 0 | 150 |

Scenario: Under Review: Approve
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate report number request
Then Request is successful

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Submit for Review request
Then Request is successful

When I send Submit for Review a report request
Then Request is successful
Then Operator report is Awaiting Review and INITIAL

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Take Ownership request
Then Request is successful

When I send Take Ownership a report request
Then Request is successful
Then Operator report is Under Review and IN_PROGRESS

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Approve request
Then Request is successful

When I send Approve a report request
Then Request is successful
Then Operator report is Approved and FINAL

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event | EventVO |      | 0 | 150 |
| SIGINT | entity| EntityVO|      | 0 | 150 |

Scenario: Under Review: Reject
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate report number request
Then Request is successful

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Submit for Review request
Then Request is successful

When I send Submit for Review a report request
Then Request is successful
Then Operator report is Awaiting Review and INITIAL

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Take Ownership request
Then Request is successful

When I send Take Ownership a report request
Then Request is successful
Then Operator report is Under Review and IN_PROGRESS

When I get allowed actions
Then Request is successful

When I send Reject a report request
Then Request is successful
Then Operator report is Rejected and FINAL

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event | EventVO |      | 0 | 150 |
| SIGINT | entity| EntityVO|      | 0 | 150 |

Scenario: Under Review: Edit
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate report number request
Then Request is successful

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Submit for Review request
Then Request is successful

When I send Submit for Review a report request
Then Request is successful
Then Operator report is Awaiting Review and INITIAL

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Take Ownership request
Then Request is successful

When I send Take Ownership a report request
Then Request is successful
Then Operator report is Under Review and IN_PROGRESS

When I get allowed actions
Then Request is successful

When I send Edit a report request
Then Request is successful

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event | EventVO |      | 0 | 150 |
| SIGINT | entity| EntityVO|      | 0 | 150 |

Scenario: Under Review: Cancel
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate report number request
Then Request is successful

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Submit for Review request
Then Request is successful

When I send Submit for Review a report request
Then Request is successful
Then Operator report is Awaiting Review and INITIAL

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Take Ownership request
Then Request is successful

When I send Take Ownership a report request
Then Request is successful
Then Operator report is Under Review and IN_PROGRESS

When I get allowed actions
Then Request is successful

When I send Cancel a report request
Then Request is successful
Then Operator report is Cancelled and FINAL

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event | EventVO |      | 0 | 150 |
| SIGINT | entity| EntityVO|      | 0 | 150 |

Scenario: Returned to Author: Submit
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate report number request
Then Request is successful

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Submit for Review request
Then Request is successful

When I send Submit for Review a report request
Then Request is successful
Then Operator report is Awaiting Review and INITIAL

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Take Ownership request
Then Request is successful

When I send Take Ownership a report request
Then Request is successful
Then Operator report is Under Review and IN_PROGRESS

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Return to Author request
Then Request is successful

When I send Return to Author a report request
Then Request is successful
Then Operator report is Returned for Revision and IN_PROGRESS

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Submit for Review request
Then Request is successful

When I send Submit for Review a report request
Then Request is successful
Then Operator report is Awaiting Review and INITIAL

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event | EventVO |      | 0 | 150 |
| SIGINT | entity| EntityVO|      | 0 | 150 |

Scenario: Returned to Author: Edit
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate report number request
Then Request is successful

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Submit for Review request
Then Request is successful

When I send Submit for Review a report request
Then Request is successful
Then Operator report is Awaiting Review and INITIAL

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Take Ownership request
Then Request is successful

When I send Take Ownership a report request
Then Request is successful
Then Operator report is Under Review and IN_PROGRESS

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Return to Author request
Then Request is successful

When I send Return to Author a report request
Then Request is successful
Then Operator report is Returned for Revision and IN_PROGRESS

When I get allowed actions
Then Request is successful

When I send Edit a report request
Then Request is successful

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event | EventVO |      | 0 | 150 |
| SIGINT | entity| EntityVO|      | 0 | 150 |

Scenario: Returned to Author: Edit
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate report number request
Then Request is successful

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Submit for Review request
Then Request is successful

When I send Submit for Review a report request
Then Request is successful
Then Operator report is Awaiting Review and INITIAL

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Take Ownership request
Then Request is successful

When I send Take Ownership a report request
Then Request is successful
Then Operator report is Under Review and IN_PROGRESS

When I get allowed actions
Then Request is successful

When I send get owner a operator report in Return to Author request
Then Request is successful

When I send Return to Author a report request
Then Request is successful
Then Operator report is Returned for Revision and IN_PROGRESS

When I get allowed actions
Then Request is successful

When I send Cancel a report request
Then Request is successful
Then Operator report is Cancelled and FINAL

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize |
| SIGINT | event | EventVO |      | 0 | 150 |
| SIGINT | entity| EntityVO|      | 0 | 150 |

Scenario: Create a report. [SIGINT] Data Source filters.
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate report number request
Then Request is successful

When I get allowed actions
Then Request is successful

When I send Save as Draft a report request
Then Request is successful
And Report is created

When I send view a report request
Then Request is successful

When I send delete a report request
Then Request is successful

Examples:
| eventFeed | objectType | query  | pageNumber | pageSize |
| SIGINT | event | dataSource:"J1"| 0 | 100 |
| SIGINT | event | dataSource:"J2" | 0 | 100 |
| SIGINT | event | dataSource:"O"| 0 | 100 |
| SIGINT | event | dataSource:"S" | 0 | 100 |
| SIGINT | event | dataSource:"T" | 0 | 100 |
| SIGINT | event | dataSource:"F" | 0 | 100 |
| SIGINT | event | dataSource:"PHONEBOOK" | 0 | 100 |
| SIGINT | event | dataSource:"E" | 0 | 100 |
| SIGINT | event | dataSource:"DU" | 0 | 100 |
| SIGINT | event | dataSource:"EID" | 0 | 100 |
| FININT | event | dataSource:"CentralBank" | 0 | 100 |
| TRAFFIC | event | dataSource:"MCC"| 0 | 100 |
| OSINT | entity | dataSource:"TWITTER" | 0 | 100 |
| OSINT | entity | dataSource:"NEWS" | 0 | 100 |
| OSINT | entity | dataSource:"INSTAGRAM" | 0 | 100 |
| OSINT | entity | dataSource:"YOUTUBE" | 0 | 100 |
| OSINT | entity | dataSource:"DARK_WEB" | 0 | 100 |
| OSINT | entity | dataSource:"DARK_WEB_REPORTS" | 0 | 100 |
| OSINT | entity | dataSource:"GPLUS" | 0 | 100 |
| OSINT | entity | dataSource:"TUMBLR" | 0 | 100 |
| GOVINT | entity | dataSource:"SITA" | 0 | 100 |
| GOVINT | entity | dataSource:"UDB" | 0 | 100 |

Scenario: Create a report. [SIGINT] Data Source filters.
Meta:@skip
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate report number request
Then Request is successful

When I get allowed actions
Then Request is successful

When I send Save as Draft a report request
Then Request is successful
And Report is created

When I send view a report request
Then Request is successful

When I send delete a report request
Then Request is successful

Examples:
| eventFeed | objectType | query  | pageNumber | pageSize |
| CIO | entity | dataSource:"ZELZAL" | 0 | 100 |
| CIO | entity | dataSource:"KARMA" | 0 | 100 |

Scenario: Create a report. [SIGINT] Data Subsource filters
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate report number request
Then Request is successful

When I get allowed actions
Then Request is successful

When I send Save as Draft a report request
Then Request is successful
And Report is created

When I send view a report request
Then Request is successful

When I send delete a report request
Then Request is successful

Examples:
| eventFeed | objectType | query  | pageNumber | pageSize |
| SIGINT | event | subSource:"CDR" | 0 | 100 |
| SIGINT | event | subSource:"CELL" | 0 | 100 |
| SIGINT | event | subSource:"FAX" | 0 | 100 |
| SIGINT | event | subSource:"SMS" | 0 | 100 |
| SIGINT | event | subSource:"VLR" | 0 | 100 |
| SIGINT | event | subSource:"Voice" | 0 | 100 |
| SIGINT | event | subSource:"MMS" | 0 | 100 |
| SIGINT | event | subSource:"NLD" | 0 | 100 |
| SIGINT | entity | subSource:"Subscriber" | 0 | 100 |

Scenario: Create a report. [SIGINT] Record Type filters.
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate report number request
Then Request is successful

When I get allowed actions
Then Request is successful

When I send Save as Draft a report request
Then Request is successful
And Report is created

When I send view a report request
Then Request is successful

When I send delete a report request
Then Request is successful

Examples:
| eventFeed | objectType | query  | pageNumber | pageSize |
| SIGINT | event | type:"CALL" | 0 | 100 |
| SIGINT | event | type:"LOCATION" | 0 | 100 |
| SIGINT | event | type:"FAX" | 0 | 100 |
| SIGINT | event | type:"VLR" | 0 | 100 |
| SIGINT | event | type:"MMS_ROAMING" | 0 | 100 |
| SIGINT | event | type:"SMS" | 0 | 100 |
| SIGINT | event | type:"MMS" | 0 | 100 |
| SIGINT | event | type:"VSMS" | 0 | 100 |
| SIGINT | event | type:"SIP_VIDEO" | 0 | 100 |
| SIGINT | entity | type:"TELECOM_SUBSCRIBER" | 0 | 100 |

Scenario: Verify that user could export a report
Meta:@skip
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send generate report number request
Then Request is successful

When I get allowed actions
Then Request is successful

When I send Save as Draft a report request
Then Request is successful
And Report is created

When I send view a report request
Then Request is successful

When I send export with sources:<sources> and without creator:<creator> a report request
Then Request is successful
Then Check check that archive is not empty
Then Check content of archive one report
Then Delete exported reports

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize | sources | creator |
| SIGINT    | entity     | EntityVO   |        | 0          | 25      | true    | falce   |
| SIGINT    | entity     | EntityVO   |        | 0          | 25      | falce   | falce   |
| SIGINT    | event     | EventVO   |        | 0          | 25      | true   | falce   |
| SIGINT    | event     | EventVO   |        | 0          | 25      | falce  | falce   |

Scenario: Verify that user could export bundle of reports
When I send CB search request - query:<query>, eventFeed:<eventFeed>, objectType:<objectType>, pageNumber:<pageNumber>, pageSize:<pageSize>
Then Request is successful
And CB search result list size > 0

When I send export report bundle with sources:<sources> and creator:<creator> of reports request
Then Request is successful
Then Check check that archive is not empty
Then Check content of bundle of reports
Then Delete exported reports

Examples:
| eventFeed | objectType | resultType | query  | pageNumber | pageSize | sources | creator |
| INFORMATION_MANAGEMENT    | entity     | EntityVO   |       | 0      | 10      | true    | false   |
| INFORMATION_MANAGEMENT    | entity     | EntityVO   |       | 0      | 10      | false    | false   |