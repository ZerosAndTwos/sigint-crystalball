"""
Describes RFI object data ranges for pre-defined attributes
"""

class RFIData:
    priorities = {0: 5, 1: 3, 2: 1}
    distributions = ['OA', 'CIO', 'ANALYST', 'SIGINT']
    task_categories = ['ANALYSIS_OSINT', 'TARGET_DEVELOPMENT',
                       'OPERATIONAL_REQUIREMENTS','TALENT_DEVELOPMENT']
    search_types = ['ST', 'S']
    states = ["PENDING", "ASSIGNED", "RESPONDED", "CANCELLED"]


