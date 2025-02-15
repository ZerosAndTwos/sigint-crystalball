from abc import abstractmethod


class RecordTypeFactory(object):

    def __init__(self):
        pass

    @abstractmethod
    def set_attributes(self, new_obj, obj):
        pass
