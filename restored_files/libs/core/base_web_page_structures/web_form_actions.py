from libs.core.base_web_page_structures.action_chains import ActionChains
from libs.core.base_web_page_structures.web_form_with_controls import \
    WebFormWithControls


class WebFormBaseActions(object):
    """
    Should be a super class for classes that implements business logic on
    the form. Uses link to 'section' to interact with a form: get controls
    and enter data, get current data from control
    """

    def __init__(self, model):
        self.model = model
        super(WebFormBaseActions, self).__init__()

    def fill_fields(self, data=None):
        """
        Fill input fields that are collected from the current section
        :param data: user data to fill up fields with. Should be in format
        {'field_name':'input_data'}.If none, random value will be entered.
        In order not to enter data into the field pass into data dict
        {'field_name':'None'}
        :return: pairs {'field_name':'entered_value'}
        """
        return ActionChains.fill_fields(self.model.get_inputs(),
                                        data=data)

    def fill_field(self, field, data):
        """
        Fills a single field by it's name
        :param field: name of the field to be filled
        :param data: user data to fill up field with
        :return: {'field_name':'entered_data'}
        """
        return ActionChains.fill_field(self.model.get_control(field), data)

    def click_button(self, btn_name):
        """
        Clicks on a button if exists
        :param btn_name: name of the button to click on
        :return: boolean weather button was clicked or not
        """
        return ActionChains.click_button(self.model.get_buttons(), btn_name)

    def get_value_from_field(self, field_name):
        """
        get value from the field on a form by field's name
        :param field_name: Name of the field to get value from
        :return: current value of the field
        """
        control = self.model.get_control(field_name)
        if control:
            return control.get_value()
        raise AssertionError("There is no such field {} on form".format(
            field_name))

    def get_value_from_fields(self):
        """
        get current values from all input fields on form
        :return: pair {'field_name':'value'}
        """
        result = dict()
        controls = self.model.get_inputs()
        for control in controls:
            result.update({control.get_name(): control.get_value()})
        return result

    def set_section(self, section):
        """
        Change current active section on the page. Can be used i to switch to
        another section if there are more than 1 and interact with controls
        on that section.
        :param section: section object. Should have type 'WebFormWithControls'
        :return: None
        """
        if not isinstance(section, WebFormWithControls):
            raise TypeError("Incorrect type for section!. Should be {}"
                            .format(WebFormWithControls))
        self.section = section
