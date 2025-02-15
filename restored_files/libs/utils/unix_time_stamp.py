import datetime
from settings import date_str_format

epoch = datetime.datetime(1970, 1, 1)


def to_time_stamp(dt):
    if not dt:
        return dt
    if isinstance(dt, str):
        dt = datetime.datetime.strptime(dt, date_str_format)
    return int((dt - epoch).total_seconds() * 1000.0)


def from_time_stamp(dt):
    if not dt:
        return dt
    if isinstance(dt, (str, unicode)):
        return str(dt)
    return datetime.datetime.utcfromtimestamp(dt/1000.0)
