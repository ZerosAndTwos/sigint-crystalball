import copy
from datetime import datetime

from libs.API.model.Entities import Manager
from libs.API.model.records.attributes_factory import AttributeFactory
from libs.API.model.records.record import Record
from libs.API.model.records.record_data import RecordData, RecordStates
from libs.API.services.source_service import SourceService
from libs.utils.randomizer import select_random_item, random_string
from settings import date_with_time_format


class RecordsManager(Manager):

    template = Record()

    def to_response(self, obj=None, **kwargs):
        obj = self._parse(obj, **kwargs)
        return obj

    def to_request(self, context, obj=None, **kwargs):
        new_obj = copy.copy(self.template)
        obj = self._parse(obj, **kwargs)
        new_obj.duration = obj.duration or ""
        new_obj.fromNumber = obj.fromNumber or ""
        new_obj.imsi = obj.imsi or ""
        new_obj.manualEntry = obj.manualEntry or True
        new_obj.type = obj.type or select_random_item(RecordData.types)
        AttributeFactory.get_factory(new_obj.type).set_attributes(new_obj, obj)
        new_obj.priority = 0
        new_obj.originalId = obj.originalId or ""
        SourceService(context).load_services()
        new_obj.sourceId = context.sources.get_source_id(obj.sourceId) if obj.sourceId else \
            context.sources.get_source_id(select_random_item(RecordData.sources))
        new_obj.state = obj.state or RecordStates.ready
        new_obj.time = obj.time or datetime.utcnow().strftime(date_with_time_format)
        new_obj.tmsi = obj.tmsi or ''
        new_obj.toNumber = obj.toNumber or ''
        return new_obj

    def to_object(self, obj=None, **kwargs):
        obj = self._parse(obj, **kwargs)
        return obj
