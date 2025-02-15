from libs.API.requests_builder.http_request import HttpRequest
from libs.backend.auth_token import auth_token


class DeleteUserHttp(HttpRequest):

    def _build_url(self):
        pass

    def __init__(self, context, user):
        super(DeleteUserHttp, self).__init__(context)
        self.request_type = 'delete'
        self.user = user

    @staticmethod
    def build_headers():
        headers = {'token': auth_token.token}
        return headers

    def _build_url(self):
        url = self._base + "admin/user/{}".format(self.user['id'])
        return url
