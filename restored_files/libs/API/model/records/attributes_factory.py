from libs.API.model.records.type_factory import RecordTypeFactory
from libs.utils.randomizer import random_string, random_int


class AttributeFactory:

    def __init__(self):
        pass

    @staticmethod
    def get_factory(record_type):
        if record_type.lower() == 'sms':
            return SMSFactory()
        if record_type.lower() == 'voice':
            return VoiceFactory()


class SMSFactory(RecordTypeFactory):

    def set_attributes(self, new_obj, obj):
        new_obj.text = obj.text or random_string()
        new_obj.duration = ''
        new_obj.optimizedDuration = ''


class VoiceFactory(RecordTypeFactory):

    def set_attributes(self, new_obj, obj):
        new_obj.text = ''
        duration = random_int(record_len=2)
        new_obj.duration = duration
        new_obj.optimizedDuration = duration
