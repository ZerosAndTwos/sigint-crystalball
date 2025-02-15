from helpers.metaclasses import Singleton
from libs.API.controllers.users_mgmt_controller.requests.sign_in_request import \
    SignInHttp
from libs.API.requests_builder.request_manager import RequestManager


class AuthManager(object):
    """
    Serves for getting auth_token based on user type
    """
    __metaclass__ = Singleton

    def __init__(self, context):
        self.context = context

    def get_token(self, user_role):
        """
        Gets auth token for user
        :param user_role: string.role of the user.
        Accepts valid roles from Aurelia
        :return: auth_token as string.
        """
        my_user = self.context.users.get_user(user_role.lower())
        request = SignInHttp(self.context, my_user.username, my_user.password)
        request.build_json()
        response = RequestManager(self.context).send_request(request).json()
        return response['result']['token']

