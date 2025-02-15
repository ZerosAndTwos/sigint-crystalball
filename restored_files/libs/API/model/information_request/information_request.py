from libs.utils.lazy_property import lazyprop
from libs.utils.unix_time_stamp import from_time_stamp



class RFIs(object):

    def __init__(self):
        self._rfis = list()

    def add_rfi(self, rfi):
        for i_rfi in self._rfis:
            if rfi.id == i_rfi.id:
                i_rfi.update(rfi)
                return
        self._rfis.append(rfi)

    def delete_rfi(self, rfi):
        if not isinstance(rfi, InformationRequest):
            raise TypeError("argument must have type RFI")
        for l_rfi in self._rfis:
            if l_rfi.id == rfi.id:
                self._rfis.remove(l_rfi)

    def get_latest(self):
        return self._rfis[-1]


class InformationRequest(dict):

    """
    Reflects InformationRequest Entity
    """

    def __init__(self, **kwargs):
        super(InformationRequest, self).__init__(**kwargs)

    @lazyprop
    def dueDate(self):
        return from_time_stamp(self['dueDate'])

    @dueDate.setter
    def dueDate(self, value):
        self['dueDate'] = value

    @lazyprop
    def state(self):
        return self['state']

    @state.setter
    def state(self, value):
        if value:
            self['state'] = value

    @lazyprop
    def priority(self):
        return self['priority']

    @priority.setter
    def priority(self, value):
            self['priority'] = value

    @lazyprop
    def searchType(self):
        return self['searchType']

    @searchType.setter
    def searchType(self, value):
        if value:
            self['searchType'] = value

    @lazyprop
    def externalRequestNumber(self):
        return self['externalRequestNumber']

    @externalRequestNumber.setter
    def externalRequestNumber(self, value):
            self['externalRequestNumber'] = value

    @lazyprop
    def requestSource(self):
        return self['requestSource']

    @requestSource.setter
    def requestSource(self, value):
        if value:
            self['requestSource'] = value

    @lazyprop
    def distributionList(self):
        return self['distributionList']

    @distributionList.setter
    def distributionList(self, value):
            self['distributionList'] = value

    @lazyprop
    def taskCategories(self):
        return self['taskCategories']

    @taskCategories.setter
    def taskCategories(self, value):
            self['taskCategories'] = value

    @lazyprop
    def subject(self):
        return self['subject']

    @subject.setter
    def subject(self, value):
            self['subject'] = value

    @lazyprop
    def description(self):
        return self['description']

    @description.setter
    def description(self, value):
            self['description'] = value

    @lazyprop
    def goals(self):
        return self['goals']

    @goals.setter
    def goals(self, value):
            self['goals'] = value

    @lazyprop
    def originalDocument(self):
        return self['originalDocument']

    @originalDocument.setter
    def originalDocument(self, value):
            self['originalDocument'] = value

    @lazyprop
    def approvedCopy(self):
        return self['approvedCopy']

    @approvedCopy.setter
    def approvedCopy(self, value):
            self['approvedCopy'] = value

    @lazyprop
    def createdDate(self):
        return from_time_stamp(self['createdDate'])

    @createdDate.setter
    def createdDate(self, value):
            self['createdDate'] = value

    @lazyprop
    def createdAt(self):
        return from_time_stamp(self['createdAt'])

    @createdAt.setter
    def createdAt(self, value):
            self['createdAt'] = value

    @lazyprop
    def createdBy(self):
        return self['createdBy']

    @createdBy.setter
    def createdBy(self, value):
            self['createdBy'] = value

    @lazyprop
    def id(self):
        return self['id']

    @id.setter
    def id(self, value):
            self['id'] = value

    @lazyprop
    def modifiedAt(self):
        return from_time_stamp(self['modifiedAt'])

    @modifiedAt.setter
    def modifiedAt(self, value):
            self['modifiedAt'] = value

    @lazyprop
    def modifiedBy(self):
        return self['modifiedBy']

    @modifiedBy.setter
    def modifiedBy(self, value):
            self['modifiedBy'] = value

    @lazyprop
    def internalRequestNumber(self):
        return self['internalRequestNumber']

    @internalRequestNumber.setter
    def internalRequestNumber(self, value):
            self['internalRequestNumber'] = value

    @lazyprop
    def previousRequests(self):
        return self['previousRequests']

    @previousRequests.setter
    def previousRequests(self, value):
            self['previousRequests'] = value

    @lazyprop
    def assignedTo(self):
        return self['assignedTo']

    @assignedTo.setter
    def assignedTo(self, value):
        self['assignedTo'] = value
