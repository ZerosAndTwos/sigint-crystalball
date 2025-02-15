import logging

from libs.utils.selenium_extensions import wait_element


class BasePage(object):
    def __init__(self, context):
        self.user_menu = UserMenu(context)
        logging.getLogger(__name__)
        logging.debug(context.driver.session_id)


class UserMenu(object):
    def __init__(self, context):
        self.menu_link = wait_element(context.driver, "li.dropdown.username")
