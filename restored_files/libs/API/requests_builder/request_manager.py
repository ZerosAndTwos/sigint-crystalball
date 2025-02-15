import requests


class RequestManager(object):

    """
    Sends http request based on type
    """

    def __init__(self, context):
        self.context = context

    def send_request(self, request, **kwargs):
        """
        Sends request to api url.
        :param request: type :class:HttpRequest. Request that should be send.
        :param kwargs: (optional) optional arguments that python requests
        module accepts
        :return: repsone on request.
        """
        url = request.url
        self.context.logger.info("Sending {0} request to URL {1}".format(
                request.type.upper(), url))
        if request.type.lower() == 'get':
            resp = requests.get(url, headers=request.headers, **kwargs)
        elif request.type.lower() == 'put':
            resp = requests.put(url, data=request.data, json=request.json,
                                headers=request.headers,
                                **kwargs)
        elif request.type.lower() == 'post':
            resp = requests.post(url, data=request.data, json=request.json,
                                 headers=request.headers, **kwargs)
        elif request.type.lower() == 'delete':
            resp = requests.delete(url, headers=request.headers, **kwargs)
        else:
            raise TypeError("Unknown request type - {}".format(request.type))

        self.context.logger.info("Got response on request: "
                                 "{}".format(resp))

        if resp.status_code is not 200:
            self.context.logger.error("Got error on request: {}".format(
                    resp))
            self.context.logger.debug("Actual response code is {}".format(
                    resp.status_code))
        return resp
