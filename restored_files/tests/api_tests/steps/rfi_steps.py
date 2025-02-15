from behave import *
from libs.API.model.information_request.information_request_checker import InformationRequestChecker
from libs.API.model.information_request.information_request_manager import InformationRequestManager
from libs.API.model.information_request.rfi_search_checker import RFISearchChecker
from libs.API.model.information_request.rfi_search_request import RFISearchRequest
from libs.API.model.information_request.rfi_search_response import RFISearchResponse
from libs.API.services.rfi_service import RFIService


rfi_manager = InformationRequestManager()



@then('Rfi record is created')
def rfi_record_is_created(context):
    rfi_request = context.request[0]
    response = context.request[-1]
    response_rfi = rfi_manager.to_response(**response.json()['result'])
    InformationRequestChecker.check(rfi_request, response_rfi)
    context.logger.info(
                "Request was sent successfully. Parsing results...")
    assert response_rfi.id
    assert response_rfi.internalRequestNumber
    assert response_rfi.createdAt
    assert response_rfi.createdBy
    assert response_rfi.modifiedAt
    context.logger.info("Information request record was created successfully")
    context.rfis.add_rfi(response_rfi)


@then('Search result is correct')
def search_result_is_correct(context):
    rfi_search = context.request[0]
    response = context.request[-1]
    search_response = RFISearchResponse(context, **response.json()['result'])
    context.logger.debug("Checking results....")
    RFISearchChecker.check(rfi_search, search_response)
    context.logger.debug("Verification successfully completed")


@when('I create new RFI with default values')
def create_rfi_with_data(context):
    __send_rfi(context)


@when('I create new RFI with specific values')
def create_rfi_with_specific_data(context):
    row = context.table.rows[0]
    param_dict = dict()
    for name in row.headings:
        param_dict[name] = row[name]
    __send_rfi(context, **param_dict)


@when('I search for RFI with query')
def find_rfi_with_search_request(context):
    row = context.table.rows[0]
    param_dict = dict()
    for name in row.headings:
        param_dict[name] = row[name]
    __search_rfi(context, **param_dict)


def __search_rfi(context, **param_dict):
    rfi_search = RFISearchRequest(**param_dict)
    rfi_service = RFIService(context)
    response = rfi_service.search_for_rfi(rfi_search)
    context.request = rfi_search, response


def __send_rfi(context, rfi=None, approved=None, original=None,  **kwargs):
    rfi_service = RFIService(context)
    rfi_request = rfi_manager.to_request(rfi, **kwargs)
    response = rfi_service.create_rfi(rfi_request, approved=approved, original=original)
    context.request = rfi_request, response


@when('I update record with files')
def send_update_request(context):
    rfi = context.rfis.get_latest()
    __send_rfi(context, rfi, approved=True, original=True)


@then('Record has attached files')
def rfi_record_has_attached_files(context):
    response = context.request[-1]
    response_rfi = rfi_manager.to_response(**response.json()['result'])
    assert response_rfi.approvedCopy['filename'] == 'approved'
    assert response_rfi.originalDocument['filename'] == 'original'
    context.rfis.add_rfi(response_rfi)


@when('I delete rfi')
def delete_rfi(context):
    rfi = context.rfis.get_latest()
    rfi_service = RFIService(context)
    response = rfi_service.delete_rfi(rfi.id)
    context.request = rfi, response


@then("RFI record is deleted")
def rfi_is_deleted(context):
    rfi = context.request[0]
    __search_rfi(context)
    search_response = RFISearchResponse(context, **context.request[-1].json()['result'])
    for entity in search_response.found_objects:
        assert not entity.id == rfi.id
    context.rfis.delete_rfi(rfi)


@when('I cancel rfi')
def cancel_rfi(context):
    rfi = context.rfis.get_latest()
    rfi_service = RFIService(context)
    response = rfi_service.cancel_rfi(rfi.id)
    context.request = rfi, response


@then('RFI has status "{status}"')
def rfi_has_status(context, status):
    rfi = context.request[0]
    response = context.request[-1]
    details_page_rfi(context)
    rfi_details_http = rfi_manager.to_object(**response.json()['result'])
    assert rfi_details_http.state == status.upper()
    rfi.state = status.upper()
    context.rfis.add_rfi(rfi)


@then('Details of rfi are correct')
def details_rfi_correct(context):
    rfi = context.request[0]
    response = context.request[-1]
    response_rfi = rfi_manager.to_object(**response.json()['result'])
    InformationRequestChecker.check(rfi, response_rfi)


@when('I get details of rfi')
def details_page_rfi(context):
    rfi = context.rfis.get_latest()
    rfi_service = RFIService(context)
    response = rfi_service.rfi_details(rfi.id)
    context.request = rfi, response

@then("RFI assigned to analyst")
def rfi_assigned_to_analyst(context):
    rfi = context.request[0]
    response = context.request[-1]
    response_rfi = rfi_manager.to_object(**response.json()['result'])
    assert response_rfi.assignedTo
    InformationRequestChecker.check(rfi, response_rfi)
    context.rfis.add_rfi(response_rfi)


@when('I take ownership of rfi')
def take_ownership_of_report(context):
    rfi = context.rfis.get_latest()
    rfi_service = RFIService(context)
    response = rfi_service.assign_rfi(rfi.id)
    context.request = rfi, response



@then('I cannot create new rfi with error code "{code}"')
def i_cannot_create_rfi(context, code):
    try:
        __send_rfi(context)
    except AssertionError as e:
        if code in e.message:
            context.logger.debug('got expected error code {}'.format(code))
        else:
            raise AssertionError("Expected error code {} was not met".format(code))
