def lazyprop(fn):
    __attr_name = fn.__name__

    @property
    def _lazyprop(self):
        if __attr_name not in self.keys():
            self[__attr_name] = None
        return fn(self)
    return _lazyprop


