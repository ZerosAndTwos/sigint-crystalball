from abc import ABCMeta, abstractmethod
from libs.backend.auth_token import auth_token


class HttpRequest(object):
    """
    Base class for http request. Override methods build_data, build_json or
    build_headers if your request should have corresponding fields.
    """

    __metaclass__ = ABCMeta

    def __init__(self, context, request_type):
        self._base = context.config.userdata.get("environment") + "/sigint/api/"
        self.context = context
        self._request_type = request_type
        self._url = self._build_url()
        self._data = None
        self._json = None
        self._headers = self._build_headers()

    @abstractmethod
    def _build_url(self):
        """
        Sub-classes must implement method in order to build request url.
        :return:---
        """
        pass

    @staticmethod
    def build_token():
        return auth_token.token

    @property
    def url(self):
        return self._url

    @property
    def type(self):
        return self._request_type

    @type.setter
    def type(self, r_type):
        self._request_type = r_type

    @property
    def data(self):
        return self._data

    @property
    def json(self):
        return self._json

    @property
    def headers(self):
        return self._headers

    @json.setter
    def json(self, value):
        self._json = value

    @data.setter
    def data(self, value):
        self._data = value

    @staticmethod
    def build_data():
        """
        Override method if request should build data
        :return: None
        """
        return {}

    @staticmethod
    def build_json():
        """
        Override method if request should build json
        :return:
        """
        return {}

    def _build_headers(self):
        """
        Forms header for request.
        Override method if request should build headers
        :return: dict headers
        """
        headers = {'token': self.context.auth_token}
        return headers
