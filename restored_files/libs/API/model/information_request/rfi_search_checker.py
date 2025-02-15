from libs.API.model.checker import Checker


class RFISearchChecker(Checker):
    """
    Checks if output is correct according to search request
    """

    @staticmethod
    def check(request, response):
        """
        Checks if response is correct
        :param request: sent request to API. Has type:class:RFISearchRequest
        :param response: received response obj with type:class:RFISearchResponse
        :return: raises AssertionError if results are incorrect
        """
        for filtr in request.filters:
            assert map(filtr.check, response.found_objects)
