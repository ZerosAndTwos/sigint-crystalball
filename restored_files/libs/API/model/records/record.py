from libs.API.model.Entities import Entity
from libs.utils.lazy_property import lazyprop



class Records(object):

    def __init__(self):
        self._records = list()

    def add_record(self, record):
        for i_record in self._records:
            if record.id == i_record.id:
                i_record.update(record)
                return
        self._records.append(record)

    def get_latest(self):
        return self._records[-1]


class Record(Entity):

    def __init__(self, **kwargs):
        super(Record, self).__init__(**kwargs)


    @lazyprop
    def duration(self):
        return self['duration']

    @duration.setter
    def duration(self, value):
        self['duration'] = value

    @lazyprop
    def fromCountry(self):
        return self['fromCountry']

    @fromCountry.setter
    def fromCountry(self, value):
        self['fromCountry'] = value

    @lazyprop
    def fromNumber(self):
        return self['fromNumber']

    @fromNumber.setter
    def fromNumber(self, value):
        self['fromNumber'] = value

    @lazyprop
    def imsi(self):
        return self['imsi']

    @imsi.setter
    def imsi(self, value):
        self['imsi'] = value

    @lazyprop
    def manualEntry(self):
        return self['manualEntry']

    @manualEntry.setter
    def manualEntry(self, value):
        self['manualEntry'] = value

    @lazyprop
    def optimizedDuration(self):
        return self['optimizedDuration']

    @optimizedDuration.setter
    def optimizedDuration(self, value):
        self['optimizedDuration'] = value

    @lazyprop
    def originalId(self):
        return self['originalId']

    @originalId.setter
    def originalId(self, value):
        self['originalId'] = value

    @lazyprop
    def priority(self):
        return self['priority']

    @priority.setter
    def priority(self, value):
        self['priority'] = value

    @lazyprop
    def sourceId(self):
        return self['sourceId']

    @sourceId.setter
    def sourceId(self, value):
        self['sourceId'] = value

    @lazyprop
    def state(self):
        return self['state']

    @state.setter
    def state(self, value):
        self['state'] = value

    @lazyprop
    def text(self):
        return self['text']

    @text.setter
    def text(self, value):
        self['text'] = value

    @lazyprop
    def time(self):
        return self['time']

    @time.setter
    def time(self, value):
        self['time'] = value

    @lazyprop
    def tmsi(self):
        return self['tmsi']

    @tmsi.setter
    def tmsi(self, value):
        self['tmsi'] = value

    @lazyprop
    def toNumber(self):
        return self['toNumber']

    @toNumber.setter
    def toNumber(self, value):
        self['toNumber'] = value

    @lazyprop
    def type(self):
        return self['type']

    @type.setter
    def type(self, value):
        self['type'] = value
