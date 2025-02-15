from behave import *

from libs.API.model.records.records_checkers import CreateManualChecker
from libs.API.model.records.records_manager import RecordsManager
from libs.API.services.record_service import RecordService


records_manager = RecordsManager()

@when('I create new manual record with default values')
def create_record_with_data(context):
    __create_record(context)


def __create_record(context, record=None, **kwargs):
    record_service = RecordService(context)
    record_request = records_manager.to_request(context, record, **kwargs)
    response = record_service.create_manual(record_request)
    context.request = record_request, response


@then('Record is created')
def rfi_record_is_created(context):
    request_record = context.request[0]
    response = context.request[-1]
    response_record = records_manager.to_response(**response.json()['result'])
    CreateManualChecker.check(request_record, response_record)
    context.logger.info(
                "Request was sent successfully. Parsing results...")
    assert response_record.id
    context.logger.info("Record was created successfully")
    context.records.add_record(response_record)
