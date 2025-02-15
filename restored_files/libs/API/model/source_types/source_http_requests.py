from libs.API.requests_builder.http_request import HttpRequest


class SourceListHttp(HttpRequest):

    def _build_url(self):
        url = self._base + 'sources'
        return url

    def __init__(self, context):
        super(SourceListHttp, self).__init__(context, 'get')
