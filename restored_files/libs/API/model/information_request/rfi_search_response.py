from libs.API.model.information_request.information_request_manager import \
    InformationRequestManager
from libs.API.model.search_response import SearchResponse


class RFISearchResponse(SearchResponse):

    entity_manager = InformationRequestManager()

    def __init__(self, context, **kwargs):
        super(RFISearchResponse, self).__init__(context, **kwargs)


    @property
    def content(self):
        return self['content']

    @property
    def first(self):
        return self['first']

    @property
    def last(self):
        return self['last']

    @property
    def number(self):
        return self['number']

    @property
    def sort(self):
        return self['sort']
