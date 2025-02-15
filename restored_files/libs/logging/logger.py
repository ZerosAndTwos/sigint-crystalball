import logging
from helpers.metaclasses import Singleton


class CustomLogger(object):

    __metaclass__ = Singleton

    def __init__(self, context):
        self.context = context
        num_level = getattr(logging,
                            context.config.userdata.get("level").upper())
        logging.basicConfig(filename='logs/debug_log.txt', filemode='w',
                            level=num_level, format='[%(levelname)s] '
                                                    '%(asctime)s - %(module)s: '
                                                    '%(message)s', datefmt=
                            '%Y-%m-%d %H:%M:%S')

    def get_logger(self):
        return logging.getLogger()
