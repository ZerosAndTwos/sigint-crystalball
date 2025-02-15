from libs.utils.randomizer import random_string, random_int, random_email


class Users(object):

    def __init__(self):
        self._users = list()

    def add_user(self, user):
        """
        adds new user to the system
        :param user_dict: dictionary of user attributes. The following attributes
        :return:
        """

        self._users.append(user)

    def delete_user(self, user_id):
        pass

    def get_user_by_role(self, role):
        pass

    def get_user_by_id(self, role):
        pass


class User(object):

    def __init__(self, roles, context, name=None, staff_id=None,
                 username=None, password=None, languages=None):
        self._name = name or random_string()
        self._staff_id = staff_id or random_int()
        self._roles = roles
        self._username = username or random_email()
        print context.config.userdata.get("password")
        self._password = password or 123456
        self._languages = languages
        self._id = None
        self._created_at = None
        self._modified_at = None
        self._modified_by = None

    @property
    def name(self):
        return self._name

    @name.setter
    def name(self, value):
        self._name = value

    @property
    def staff_id(self):
        return self._staff_id

    @staff_id.setter
    def staff_id(self, value):
        self._staff_id = value

    @property
    def roles(self):
        return self._roles

    @roles.setter
    def roles(self, value):
        if isinstance(value, list):
            self.roles.extend(value)
        elif isinstance(value, str):
            self.roles.append(value)
        else:
            raise TypeError("Incorrect value type. Should be either string or"
                            "list")

    @property
    def username(self):
        return self._username

    @username.setter
    def username(self, value):
        self._username = value

    @property
    def password(self):
        return self._password

    @password.setter
    def password(self, value):
        self._password = value

    @property
    def languages(self):
        return self._languages

    @languages.setter
    def languages(self, value):
        if isinstance(value, list):
            self.roles.extend(value)
        elif isinstance(value, str):
            self.roles.append(value)
        else:
            raise TypeError("Incorrect value type. Should be either string or"
                            "list")
