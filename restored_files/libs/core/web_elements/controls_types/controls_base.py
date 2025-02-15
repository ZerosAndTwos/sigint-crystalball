from abc import ABCMeta, abstractmethod


class BaseControl(object):
    """
    Base method for all controls, ie. for Drop-down, Text field etc.
    """

    __metaclass__ = ABCMeta

    def __init__(self, control, name):
        """
        Creates a control instance with the specified arguments:
        :param control: web element object. All actions that are passed to
        control are executed on web element object.
        :param name: name of the control that is passed through mappers
        :return:None
        """
        self.control = control
        self.name = name

    def do_action(self, data=None):
        """
        This method should be implemented by every control
        :param data: data that should be entered in control
        :return:
        """
        data = self.prepare_data(data=data)
        self.wait_for_control_to_appear()
        self.wait_for_control_to_enable()
        self.fill_control(data)

        return {self.name: data}

    @abstractmethod
    def prepare_data(self, data=None):
        raise NotImplementedError("Should be implemented in sub-class")

    @abstractmethod
    def fill_control(self, data):
        raise NotImplementedError("Should be implemented in sub-class")

    def get_attribute(self, attribute):
        """
        Gets passed attribute from web element. Attribute is any available
        atribute within DOM element( i.e. tag, id, value, etc.)
        :param attribute: attribute to search for
        :return: value of searched attribute.
        """
        attr = self.control.get_attribute(attribute)
        return attr

    def is_displayed(self):
        """
        Checks if current control is visible
        :return: True if control is visible and False otherwise.
        """
        return self.control.is_displayed()

    def wait_for_control_to_appear(self):
        if not self.control.is_displayed():
            raise AssertionError("Control {} is not visible!"
                                 "".format(self.name))

    def wait_for_control_to_enable(self, timeout=30):
        if not self.control.is_enabled():
            raise AssertionError("Control {} is not enabled!"
                                 "".format(self.name))

    def is_read_only(self, data=None):
        ctrl_id = self.get_attribute('id')
        is_editable = self.s2l.execute_javascript(
            "return document.getElementById('{0}').isContentEditable;"
            "".format(ctrl_id))
        return is_editable

    def set_focus(self):
        mapping = {"id": "getElementById('{locator}')",
                   "name": "getElementsByName('{locator}')[0]"}
        for e_type, method in mapping.iteritems():
            e_type_exst = self.get_attribute(e_type)
            if e_type_exst:
                script = "document.{}.focus();".format(
                    method.format(locator=e_type_exst))
                self.s2l.execute_javascript(script)
                break

    def get_value(self):
        """
        Get current value of the control
        :return: Value of the control
        """
        return self.control.get_attribute("value")

    def get_name(self):
        """
        Gets control name
        :return: name of the control
        """
        return self.name
