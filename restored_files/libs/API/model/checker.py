from abc import abstractmethod


class Checker:
    """
    Base class to verify output of http command
    """

    def __init__(self):
        pass

    @staticmethod
    @abstractmethod
    def check(request, response):
        pass
