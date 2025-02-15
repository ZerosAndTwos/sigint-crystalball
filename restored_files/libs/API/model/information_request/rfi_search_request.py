from libs.API.model.information_request.rfi_data import RFIData
from libs.API.model.information_request.rfi_search_filters import StateFilter,  RequestSourceFilter, \
    CreatedByFilter, MinCreatedDateFilter, MaxCreatedDateFilter, MinDueDateFilter, MaxDueDateFilter, \
    MinLastRespondDateFilter, MaxLastRespondDateFilter, RequestNumberFilter, SubjectFilter, MinPriorityFilter
from libs.API.model.search_request import SearchRequest


class RFISearchRequest(SearchRequest):

    def  __init__(self,  sort_direction=False,
                 sort_field='createdAt', states=RFIData.states,
                 state=None, min_priority=None, request_source=None,
                 created_by=None, min_created_date=None, max_created_date=None,
                 min_due_date=None, max_due_date=None,
                 min_last_respond_date=None, max_last_respond_date=None,
                 request_number=None, subject=None):

        super(RFISearchRequest, self).__init__()
        self.sortDirection = sort_direction
        self.sortField = sort_field
        self.states = states
        self.state = state
        self.minPriority = min_priority
        self.requestSource = request_source
        self.createdBy = created_by
        self.minCreatedDate = min_created_date
        self.maxCreatedDate = max_created_date
        self.minDueDate = min_due_date
        self.maxDueDate = max_due_date
        self.minLastRespondDate = min_last_respond_date
        self.maxLastRespondDate = max_last_respond_date
        self.requestNumber = request_number
        self.subject = subject

    @property
    def sortDirection(self):
        return self['sortDirection']

    @sortDirection.setter
    def sortDirection(self, value):
        self['sortDirection'] = value

    @property
    def sortField(self):
        return self['sortField']

    @sortField.setter
    def sortField(self, value):
        self['sortField'] = value

    @property
    def states(self):
        return self['states']

    @states.setter
    def states(self, value):
        self['states'] = value

    @property
    def state(self):
        return self['state']

    @state.setter
    def state(self, value):
        self['state'] = StateFilter(value)

    @property
    def minPriority(self):
        return self['minPriority']

    @minPriority.setter
    def minPriority(self, value):
        self['minPriority'] = MinPriorityFilter(value)

    @property
    def requestSource(self):
        return self['requestSource']

    @requestSource.setter
    def requestSource(self, value):
        self['requestSource'] = RequestSourceFilter(value)

    @property
    def createdBy(self):
        return self['createdBy']

    @createdBy.setter
    def createdBy(self, value):
        self['createdBy'] = CreatedByFilter(value)

    @property
    def minCreatedDate(self):
        return self['minCreatedDate']

    @minCreatedDate.setter
    def minCreatedDate(self, value):
        self['minCreatedDate'] = MinCreatedDateFilter(value)

    @property
    def maxCreatedDate(self):
        return self['maxCreatedDate']

    @maxCreatedDate.setter
    def maxCreatedDate(self, value):
        self['maxCreatedDate'] = MaxCreatedDateFilter(value)

    @property
    def minDueDate(self):
        return self['minDueDate']

    @minDueDate.setter
    def minDueDate(self, value):
        self['minDueDate'] = MinDueDateFilter(value)

    @property
    def maxDueDate(self):
        return self['maxDueDate']

    @maxDueDate.setter
    def maxDueDate(self, value):
        self['maxDueDate'] = MaxDueDateFilter(value)


    @property
    def minLastRespondDate(self):
        return self['minLastRespondDate']

    @minLastRespondDate.setter
    def minLastRespondDate(self, value):
        self['minLastRespondDate'] = MinLastRespondDateFilter(value)


    @property
    def maxLastRespondDate(self):
        return self['maxLastRespondDate']

    @maxLastRespondDate.setter
    def maxLastRespondDate(self, value):
        self['maxLastRespondDate'] = MaxLastRespondDateFilter(value)

    @property
    def requestNumber(self):
        return self['requestNumber']

    @requestNumber.setter
    def requestNumber(self, value):
        self['requestNumber'] = RequestNumberFilter(value)


    @property
    def subject(self):
        return self['subject']

    @subject.setter
    def subject(self, value):
        self['subject'] = SubjectFilter(value)
