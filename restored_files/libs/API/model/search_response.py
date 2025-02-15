class SearchResponse(dict):

    entity_manager = None

    def __init__(self, context,  **kwargs):
        self.context = context
        super(SearchResponse, self).__init__(**kwargs)
        self.found_objects = self.__parse_content()

    def __parse_content(self):
        cont = list()
        if not self.content:
            return cont
        for item in self.content:
            obj = self.entity_manager.to_object(**item)
            cont.append(obj)
        return cont

    @property
    def totalElements(self):
        return self['totalElements']

    @property
    def totalPages(self):
        return self['totalPages']
