class Observable(object):

    def __init__(self):
        super(Observable, self).__init__()
        self.observers = list()

    def register_observer(self, observer):
        self.observers.append(observer)

    def remove_observer(self, observer):
        self.observers.remove(observer)

    def notify_observers(self, value):
        for observer in self.observers:
            observer.update(value)
