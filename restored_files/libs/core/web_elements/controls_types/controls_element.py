"""
This module represents classes for all control types available.
"""
import logging
from libs.core.web_elements.controls_types.controls_base import BaseControl
from libs.utils.randomizer import random_string, select_random_item


class TextField(BaseControl):
    """
    Represents text field control
    """

    def __init__(self, control, name):
        """
        Creates text field control object with defined arguments:
        :param control: web_element that is associated with this control
        :param name: name of control that is passed through mapper
        :return:None
        """
        super(TextField, self).__init__(control, name)

    def prepare_data(self, data=None):
        if data:
            l_input = data
        else:
            l_input = random_string()
        return l_input

    def fill_control(self, data):
        self.control.send_keys(data)


class Dropdown(BaseControl):
    """
    Represents drop-down control
    """

    def __init__(self, control, name):
        """
        Creates drop-down control object with defined arguments:
        :param control: web_element that is associated with this control
        :param name: name of control that is passed through mapper
        :return:None
        """
        super(Dropdown, self).__init__(control, name)

    def prepare_data(self, data=None):
        options = self.get_available_options()
        if not data:
            chosen_option = select_random_item(options)
        else:
            try:
                chosen_option = [option for option in options if
                                 data in (option.get_attribute('value'),
                                 option.text)][0]
            except KeyError:
                raise AssertionError("required data is not found!")
        return chosen_option

    def fill_control(self, option):
        option.click()

    def get_available_options(self):
        options = self.control.find_elements_by_tagname("option")
        return options


class Button(BaseControl):
    """
    Represents button control
    """

    def fill_control(self, data):
        self.control.click()

    def prepare_data(self, data=None):
        pass

    def __init__(self, control, name):
        """
        Creates button control object with defined arguments:
        :param control: web_element that is associated with this control
        :param name: name of control that is passed through mapper
        :return:None
        """
        super(Button, self).__init__(control, name)


class RadioButton(BaseControl):
    """
    Represents radio button control
    """
    def __init__(self, control, name):
        """
        Creates radio-button control object with defined arguments:
        :param control: web_element that is associated with this control
        :param name: name of control that is passed through mapper
        :return:None
        """
        super(RadioButton, self).__init__(control, name)

    def do_action(self, data=None):
        return super(RadioButton, self).do_action(data=data)


class Checkbox(BaseControl):
    """
    Represents drop-down control
    """
    def __init__(self, control, name):
        """
        Creates radio-button control object with defined arguments:
        :param control: web_element that is associated with this control
        :param name: name of control that is passed through mapper
        :return:None
        """
        super(Checkbox, self).__init__(control, name)

    def do_action(self, data=None):
        return super(Checkbox, self).do_action(data=data)
