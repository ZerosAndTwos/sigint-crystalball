from libs.API.model.records.records_http_requests import ManualHttpRequest


class RecordService:
    """
    Controller class to manipulate with records. Supports all methods that API
    has. Call appropriate method of controller to call API method.
    """

    def __init__(self, context):
        self.context = context

    def create_manual(self, record_request):
        """
        Analog for api method /record/manual. Sends http request to create record with params
        'record_request' and returns response as it is
        :param record_request: http request data. Should have type
        :return: http response
        """
        manual_http = ManualHttpRequest(self.context)
        manual_http.json = record_request
        response = self.context.request_manager.send_request(manual_http)
        return response
