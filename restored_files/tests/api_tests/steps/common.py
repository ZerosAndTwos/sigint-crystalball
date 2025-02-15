from behave import *


@then('I expect response code "{code}"')
def expect_response_code(context, code):
    if context.request[-1].status_code != int(code):
        raise AssertionError("Return status code wasn't as expected."
                             "Actual code: {0}, expected code: {1}"
                             "".format(context.request[-1].status_code, code))
