from libs.API.model.checker import Checker


class CreateManualChecker(Checker):

    @staticmethod
    def check(request_entity, response_entity):
        print request_entity
        print response_entity
        for attribute, value in request_entity:
            if not value:
                continue
            assert value == getattr(request_entity, attribute)
            assert response_entity.id
