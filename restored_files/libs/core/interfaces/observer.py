from abc import abstractmethod


class Observer(object):

    def __init__(self):
        super(Observer, self).__init__()

    @abstractmethod
    def update(self, value):
        pass
