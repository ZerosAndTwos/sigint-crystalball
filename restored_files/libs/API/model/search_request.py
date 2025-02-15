from libs.API.model.search_filter import SearchFilter


class SearchRequest(dict):
    page = 0
    pageSize = 50

    def __init__(self, **kwargs):
        super(SearchRequest, self).__init__(pageSize=self.pageSize,
                                            page=self.page, **kwargs)

    @property
    def filters(self):
        return [filtr for filtr in self.values() if (isinstance(filtr, SearchFilter) and filtr.value)]
