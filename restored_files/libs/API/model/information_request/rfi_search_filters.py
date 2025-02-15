from datetime import datetime
from libs.API.model.search_filter import SearchFilter, SearchDateFilter
from settings import date_str_format, date_with_time_format

"""
Filters for RFI Search
"""


class MinPriorityFilter(SearchFilter):

    description = 'Min priority filter'

    def check(self, information_request):
        return self._value >= information_request.priority


class StateFilter(SearchFilter):

    description = "State Filter"

    def check(self, information_request):
        return self._value == information_request.state


class RequestSourceFilter(SearchFilter):

    description = "Request Source Filter"

    def check(self, information_request):
        return self._value == information_request.requestSource


class CreatedByFilter(SearchFilter):

    def check(self, information_request):
        return self._value == information_request.createdBy


class MinCreatedDateFilter(SearchDateFilter):

    description = "Minimal Created Date Filter"

    def check(self, information_request):
        return information_request.createdDate >= self._value


class MaxCreatedDateFilter(SearchDateFilter):

    description = "Max Created Date Filter"

    def check(self, information_request):
        return information_request.createdDate <= self._value


class MinDueDateFilter(SearchDateFilter):

    description = "Minimal Due Date Filter"

    def check(self, information_request):
        return information_request.dueDate >= self._value


class MaxDueDateFilter(SearchDateFilter):

    description = "Max Due Date Filter"

    def check(self, information_request):
        return information_request.dueDate <= self._value


class MinLastRespondDateFilter(SearchDateFilter):

    description = "Min Last Respond Date Filter"

    def check(self, information_request):
        return information_request.modifiedAt >= self._value


class MaxLastRespondDateFilter(SearchDateFilter):

    description = "Max Last Respond Date Filter"

    def check(self, information_request):
        return information_request.modifiedAt <= self._value


class RequestNumberFilter(SearchFilter):

    description = "Request Number Filter"

    def check(self, information_request):

        return information_request.internalRequestNumber.startswith(self._value) or \
               information_request.externalRequestNumber.startswith(self._value)


class SubjectFilter(SearchFilter):

    description = "Subject Filter"

    def check(self, information_request):
        return information_request.subject.startswith(self._value)
