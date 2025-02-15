import copy
from abc import abstractmethod

class Manager:

    template = None

    def __init__(self):
        pass

    def _parse(self, req_obj, **kwargs):
        if req_obj:
            return req_obj
        obj = copy.copy(self.template)
        for attribute, value in kwargs.iteritems():
            setattr(obj, attribute, value)
        return obj

    @abstractmethod
    def to_request(self, **kwargs):
        pass

    @abstractmethod
    def to_response(self, **kwargs):
        pass

    @abstractmethod
    def to_object(self, **kwargs):
        pass


class Entity(dict):
    """
    Abstract class to describe entity
    """
    def __init__(self, **kwargs):
        super(Entity, self).__init__(**kwargs)

    def __iter__(self):
        return self.iteritems()
