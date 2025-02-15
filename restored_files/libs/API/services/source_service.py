from libs.API.model.source_types.source import Source
from libs.API.model.source_types.source_http_requests import SourceListHttp


class SourceService(object):

    def __init__(self, context):
        self.context = context

    def load_services(self):
        """
        Receive the list of known services via API http get and load it into the app context
        :return:
        """
        list_http = SourceListHttp(self.context)
        response = self.context.request_manager.send_request(list_http).json()['result']
        for source in response:
            self.context.sources.add_source(Source(**source))
