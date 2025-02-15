from libs.API.model.search_filter import SearchFilter
from libs.API.requests_builder.http_request import HttpRequest


class UploadRFIHttp(HttpRequest):
    """
    Describes /rfis/upload request
    """
    def _build_url(self):
        """
        Forms url for request
        :return: string url
        """
        url = self._base + 'rfis/upload'
        return url

    def __init__(self, context):
        super(UploadRFIHttp, self).__init__(context, 'post')


class SearchRFIHttp(HttpRequest):
    """
    Describes /rfis/search request
    """

    def _build_url(self):
        """
        Forms url for request
        :return: string url
        """
        url = self._base + 'rfis/search'
        return url

    def build_json(self, search):
        json_dict = dict()
        for attr, search_f in search.iteritems():
            if isinstance(search_f, SearchFilter):
                json_dict[attr] = search_f.value
            else:
                json_dict[attr] = search_f
        self.json = json_dict

    def __init__(self, context):
        super(SearchRFIHttp, self).__init__(context, 'post')


class DeleteRFIHttp(HttpRequest):
    """
    Describes /rfis/<rfi_id> delete request
    """

    def __init__(self, context, rfi_id):
        self._rfi_id = rfi_id
        super(DeleteRFIHttp, self).__init__(context, 'delete')

    def _build_url(self):
        url = self._base +'rfis/{}'.format(self._rfi_id)
        return url


class CancelRFIHttp(HttpRequest):
    """
    Describes /rfis/<rfi_id>/CANCELLED request
    """

    def __init__(self, context, rfi_id):
        self.rfi_id = rfi_id
        super(CancelRFIHttp, self).__init__(context, 'post')

    def _build_url(self):
        url = self._base + 'rfis/{}/CANCELLED'.format(self.rfi_id)
        return url


class DetailsRFIHttp(HttpRequest):
    """
    Describes /rfis/<rfi_id> request
    Returns rfi details
    """

    def __init__(self, context, rfi_id):
        self.rfi_id = rfi_id
        super(DetailsRFIHttp, self).__init__(context, 'get')

    def _build_url(self):
        url = self._base + 'rfis/{}'.format(self.rfi_id)
        return url


class AssignRFIHttp(HttpRequest):
    """
    Describes /rfis/<rfi_id>/assign request
    Assign rfi to the user who has sent request
    """

    def __init__(self, context, rfi_id):
        self.rfi_id = rfi_id
        super(AssignRFIHttp, self).__init__(context, 'post')

    def _build_url(self):
        url = self._base + '/rfis/{}/assign'.format(self.rfi_id)
        return url
