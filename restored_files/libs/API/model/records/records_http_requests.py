from libs.API.requests_builder.http_request import HttpRequest


class ManualHttpRequest(HttpRequest):

    def _build_url(self):
        url = self._base + "record/manual"
        return url

    def __init__(self, context):
        super(ManualHttpRequest, self).__init__(context, 'post')
