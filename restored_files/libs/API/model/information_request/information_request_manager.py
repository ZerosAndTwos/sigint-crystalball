import copy

from libs.API.model.Entities import Manager
from libs.API.model.information_request.information_request import \
    InformationRequest
from datetime import datetime, timedelta
from libs.API.model.information_request.rfi_data import RFIData
from libs.utils.randomizer import random_string, select_random_item
from libs.utils.unix_time_stamp import to_time_stamp


class InformationRequestManager(Manager):

    template = InformationRequest()

    def to_request(self, obj=None, **kwargs):
        """
        Creates HTTP POST request to /rfis/upload to create or update existing RFI
        :param obj: RFI object to update. Type:class:InformationRequest
        :param kwargs: (optional) properties of RFI to create new entity with. Accepts arguments
        as :class:InformationRequest
        :return: InformationRequest obj that should be sent to HTTP API
        """
        new_obj = copy.copy(self.template)
        obj = self._parse(obj, **kwargs)
        new_obj.createdDate = to_time_stamp(obj.createdDate) if obj.createdDate \
            else to_time_stamp(datetime.utcnow())
        new_obj.description = obj.description
        new_obj.distributionList = obj.distributionList
        new_obj.externalRequestNumber = obj.externalRequestNumber or \
            random_string(s_len=5)
        new_obj.goals = obj.goals
        new_obj.priority = obj.priority if obj.priority in RFIData.priorities else \
            select_random_item(RFIData.priorities.keys())
        new_obj.requestSource = obj.requestSource or random_string()
        new_obj.searchType = obj.searchType if obj.searchType in \
                                           RFIData.search_types else \
            select_random_item(RFIData.search_types)
        new_obj.state = obj.state or RFIData.states[0]
        new_obj.subject = obj.subject or random_string()
        new_obj.taskCategories = obj.taskCategories
        new_obj.dueDate = to_time_stamp(new_obj.createdDate + timedelta(days=RFIData.priorities[new_obj.priority]))
        new_obj.id = obj.id
        new_obj.internalRequestNumber = obj.internalRequestNumber
        return new_obj

    def to_object(self, obj=None, **kwargs):
        """
        Converts RFI:class:InformationRequest to entity object
        :param obj: RFI object to update. Type:class:InformationRequest
        :param kwargs: (optional) properties of RFI to create new entity with. Accepts arguments
        as :class:InformationRequest
        :return: Information Request object
        """
        obj = self._parse(obj, **kwargs)
        return obj

    def to_response(self, obj=None, **kwargs):
        """
        Converts RFI:class:InformationRequest to response obj
        :param obj: RFI object to update. Type:class:InformationRequest
        :param kwargs: (optional) properties of RFI to create new entity with. Accepts arguments
        as :class:InformationRequest
        :return: Information Request response
        """
        obj = self._parse(obj, **kwargs)
        return obj
