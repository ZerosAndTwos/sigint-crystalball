from helpers.metaclasses import Singleton
from libs.API.controllers.users_mgmt_controller.requests.delete_user_request import \
    DeleteUserHttp
from libs.API.controllers.users_mgmt_controller.requests.signup_request import \
    SignupHttpRequest
from libs.API.controllers.users_mgmt_controller.requests.users_list_request import \
    UsersListHttp
from libs.API.controllers.users_mgmt_controller.users_controller import \
    UsersController
from libs.API.requests_builder.request_manager import RequestManager
from libs.backend.auth_token import auth_token


class UserManagement(object):

    __metaclass__ = Singleton

    def __init__(self, context):
        self.context = context
        self._users = list()
        self.__signup_user()

    @property
    def users_list(self):
        return self._users

    def add_user(self, user):
        self._users.append(user)

    def get_user(self, user_role):
        user = self.__is_exist(user_role)
        if not user:
            self.__create_user(user_role)
            user = self.__is_exist(user_role)
        return user

    def delete_user(self, user):
        users_list = RequestManager(UsersListHttp(self.context)
                                    ).send_request()['result']

        for user_l in users_list:
            if user_l['email'] == user.email:
                RequestManager(DeleteUserHttp(self.context, user_l)
                               ).send_request()

    def __create_user(self, user_role):
        response = UsersController(self.context).create_user([user_role])
        if response.status_code is not 200:
            raise AssertionError

    def __signup_user(self):
        request = SignupHttpRequest(self.context)
        request.build_json()
        resp = RequestManager(self.context).\
            send_request(request)

        auth_token.token = resp.json()['result']['token']

    def __is_exist(self, user_role):
        for user in self._users:
            if user_role.lower() in user.roles:
                return user
        return False


class SiginUser(object):

    def __init__(self, email, password, roles):
        self._roles = roles
        self._email = email
        self._password = password
        self._is_logined = False

    @property
    def is_logined(self):
        return self._is_logined

    @is_logined.setter
    def is_logined(self, value):
        self._is_logined = value

    @property
    def email(self):
        return self._email

    @email.setter
    def email(self, value):
        self._email = value

    @property
    def password(self):
        return self._password

    @password.setter
    def password(self):
        return self._password

    @property
    def roles(self):
        return self._roles

    @roles.setter
    def roles(self, value):
        self._roles.append(value)
