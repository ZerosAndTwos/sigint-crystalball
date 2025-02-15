from tempfile import TemporaryFile

from libs.API.controllers.users_mgmt_controller.requests.create_user_request import \
    CreateUserHttp
from libs.API.requests_builder.request_manager import RequestManager
from libs.teela_entities.users import User


class UsersController(object):

    def __init__(self, context):
        self.context = context
        self.request_manager = RequestManager(context)

    def create_user(self, roles, **kwargs):
        """
        creates new user via HTTP API. Accepts optional number of
        arguments that user:class:'User' has. If no value provided for
        optional argument, random value will be generated
        :param roles: list. roles of the user that should be created.
        E.g. ['user', 'analyst'] etc.
        :param username: (optional) string. Indicates user login name.
         Will be sent as param to request:class:'CreateUserHttp'
        :param name: (optional) string. Indicates user name, like 'John Doe'.
         Will be sent as param to request:class:'CreateUserHttp'
        :param password: (optional) string. User password.
        If not provided, general password from settings will be used.
        :param staff_id: (optional) string. user's id that is printed on
        reports.


        :return:
        """
        user = User(roles, self.context, **kwargs)
        request = CreateUserHttp(self.context)
        request.build_json(user.username, user.staff_id,
                                          user.roles, user.name, user.password)
        response = self.request_manager.send_request(request)
        if response.status_code == 200:
            self.context.users.add_user(user)
        return response

    def update_user(self, user, upd_data):
        pass

    def list_user(self):
        pass

    def delete_user(self, user):
        pass
