from abc import ABCMeta, abstractmethod
from datetime import datetime

from settings import date_with_time_format


class SearchFilter:

    __metaclass__ = ABCMeta

    def __init__(self, value=None):
        self._value = value

    @property
    def value(self):
        return self._value

    @value.setter
    def value(self, val):
        self._value = val

    @abstractmethod
    def check(self, obj):
        pass


class SearchDateFilter(SearchFilter):

    def __init__(self, value=None):
        if isinstance(value, datetime):
            self._value = value
        elif isinstance(value, (str, unicode)):
            self._value = datetime.strptime(value, date_with_time_format)
        elif not value:
            self._value = value


    @property
    def value(self):
        if self._value:
            return self._value.strftime(date_with_time_format)
        return self._value
