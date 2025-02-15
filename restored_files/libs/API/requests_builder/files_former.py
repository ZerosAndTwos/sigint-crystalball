from abc import abstractmethod
from tempfile import NamedTemporaryFile

"""
Former classes that helps to form 'files' request attribute for RFi upload
request. Insert into files dict original, approved files if defined, forms
json file.
"""


class Attachments(object):

    def __init__(self, files=None, *u_file):
        if files:
            self._files = files.form_data()
        else:
            self._files = dict()
        for u_f in u_file:
            self._files.update(u_f)

    def __enter__(self):
        return self

    def add_attachment(self, attachment):
        if not isinstance(attachment, Attachment):
            raise TypeError("Passed file obj should have")
        self._files.update(attachment.form_data())

    @property
    def files(self):
        return self._files

    def __exit__(self, exc_type, exc_val, exc_tb):
        for u_file in self._files.itervalues():
            fs = u_file[1]
            fs.close()


class Attachment(object):
    def __init__(self, ):
        pass

    @abstractmethod
    def form_data(self):
        pass


class FileAttachment(Attachment):

    def __init__(self, file_name):
        self.name = file_name

    def form_data(self):
        """
        :return: dict
        """
        app_tmp = NamedTemporaryFile(mode='wb+', prefix=self.name)
        app_tmp.write(self.name)
        app_tmp.seek(0)
        file_dict = {self.name:
                         (self.name, app_tmp, 'application/octet-stream')}
        return file_dict


class JsonAttachment(Attachment):

    def __init__(self, obj):
        self.obj = obj

    def form_data(self):
        """
        :return: dict
        """
        json_tmp = NamedTemporaryFile(suffix="body.json")
        import json
        json.dump(self.obj, json_tmp)
        json_tmp.seek(0)
        file_dict = {"json": ('blob', json_tmp, 'application/json')}
        return file_dict


