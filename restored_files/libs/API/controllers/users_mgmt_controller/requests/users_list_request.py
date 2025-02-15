from libs.API.requests_builder.http_request import HttpRequest


class UsersListHttp(HttpRequest):

    def __init__(self, context):
        super(UsersListHttp, self).__init__(context)
        self.request_type = 'get'

    def build_headers(self):
        headers = {'token': self.build_token()}
        return headers

    def build_url(self):
        url = self.context.config.userdata.get("environment") + \
              "/sigint/api/admin/users"

        return url
