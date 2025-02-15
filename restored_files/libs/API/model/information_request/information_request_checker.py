from libs.API.model.checker import Checker


class InformationRequestChecker(Checker):
    """
    Checks if created information request is correct according to request
    """

    @staticmethod
    def check(query, response):
        """
        compare in and out of http create RFI
        :param query: send create or update RFI with type:class:InformationRequest.
        Use class:InformationRequestManager.to_request(InformationRequest) to create request obj.
        :param response:
        :return:
        """
        assert query.createdDate == response.createdDate
        assert query.description == response.description
        assert query.distributionList == response.distributionList
        assert query.externalRequestNumber == response.externalRequestNumber
        assert query.goals == response.goals
        assert query.priority == response.priority
        assert query.requestSource == response.requestSource
        assert query.searchType == response.searchType
        assert query.state == response.state
        assert query.subject == response.subject
        assert query.taskCategories == response.taskCategories
        assert query.dueDate == response.dueDate

