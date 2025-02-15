from libs.API.model.Entities import Entity
from libs.utils.lazy_property import lazyprop


class Sources(object):

    def __init__(self):
        self._sources = list()

    def add_source(self, source):
        self._sources.append(source)

    def delete_source(self, source):
        self._sources.pop(source)

    def get_source_id(self, source_name):
        for source in self._sources:
            if source.name.lower() == source_name.lower():
                return source.id
        return None


class Source(Entity):
    """
    Describes a source entity within the application. Set of properties are the same as per Source json
    """

    @lazyprop
    def id(self):
        return self['id']

    @id.setter
    def id(self, value):
        self['id'] = value

    @lazyprop
    def createdAt(self):
        return self['createdAt']

    @createdAt.setter
    def createdAt(self, value):
        self['createdAt'] = value

    @lazyprop
    def deleted(self):
        return self['deleted']

    @deleted.setter
    def deleted(self, value):
        self['deleted'] = value

    @lazyprop
    def lmt(self):
        return self['lmt']

    @lmt.setter
    def lmt(self, value):
        self['lmt'] = value

    @lazyprop
    def modifiedAt(self):
        return self['modifiedAt']

    @modifiedAt.setter
    def modifiedAt(self, value):
        self['modifiedAt'] = value

    @lazyprop
    def modifiedBy(self):
        return self['modifiedBy']

    @modifiedBy.setter
    def modifiedBy(self, value):
        self['modifiedBy'] = value

    @lazyprop
    def name(self):
        return self['name']

    @name.setter
    def name(self, value):
        print self['name']
        self['name'] = value

    @lazyprop
    def type(self):
        return self['type']

    @type.setter
    def type(self, value):
        self['type'] = value

    @lazyprop
    def version(self):
        return self['version']

    @version.setter
    def version(self, value):
        self['version'] = value
