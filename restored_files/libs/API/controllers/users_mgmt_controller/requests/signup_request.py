from libs.API.requests_builder.http_request import HttpRequest
from libs.utils.randomizer import random_email


class SignupHttpRequest(HttpRequest):

    def _build_url(self):
        url = self._base + "auth/signup"
        return url

    def build_json(self):
        email = random_email()
        password = self.context.config.userdata.get("user.password")
        json = {"email": email,
                "password": password}
        self.json = json

    @staticmethod
    def _build_headers():
        headers = {'Content-Type': 'application/json'}
        return headers

    def __init__(self, context):
        super(SignupHttpRequest, self).__init__(context, 'post')
