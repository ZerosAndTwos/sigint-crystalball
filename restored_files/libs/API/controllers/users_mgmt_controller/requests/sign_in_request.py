from libs.API.requests_builder.http_request import HttpRequest


class SignInHttp(HttpRequest):

    def _build_url(self):
        url = self._base + 'auth/login'
        return url

    def build_json(self):
        user_dict = dict(
            username=self.email,
            password=self.password,
        )
        self.json = user_dict

    def _build_headers(self):
        headers = {'Content-Type': 'application/json'}
        return headers

    def __init__(self, context, email, password):
        super(SignInHttp, self).__init__(context, 'post')
        self.email = email
        self.password = password
