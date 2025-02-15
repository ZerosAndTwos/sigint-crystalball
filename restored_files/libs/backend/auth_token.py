from datetime import datetime
from helpers.metaclasses import Singleton


class AuthToken(object):

    __metaclass__ = Singleton

    def __init__(self):
        self._created = None
        self._token = None

    @property
    def token(self):
        return self._token

    @token.setter
    def token(self, u_token):
        self._token = u_token
        self._created = datetime.now()

auth_token = AuthToken()
