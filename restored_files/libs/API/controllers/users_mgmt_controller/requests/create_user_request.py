from libs.API.requests_builder.http_request import HttpRequest
from libs.backend.auth_token import auth_token


class CreateUserHttp(HttpRequest):

    def __init__(self, context):
        super(CreateUserHttp, self).__init__(context, 'put')

    def _build_url(self):
        url = self._base + 'admin/user/add'
        return url

    def build_json(self, email, staf_id, roles, username, password):
        user_dict = dict(
            email=email,
            username=username,
            staffId=staf_id,
            password=password,
            roles=roles
        )
        self.json = user_dict

    def _build_headers(self):
        headers = {'token': auth_token.token,
                   'Content-Type': 'application/json'}
        return headers

    def __init__(self, context, ):
        super(CreateUserHttp, self).__init__(context, 'put')

