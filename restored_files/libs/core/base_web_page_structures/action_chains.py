from libs.core.web_elements.controls_types.controls_element import TextField, \
    Dropdown, Checkbox, RadioButton


class ActionChains(object):
    """
    Use action chains class to perform actions with controls in *Steps classes
    """

    @staticmethod
    def fill_field(field, data):
        """
        fill single field with passed data
        :param field: web_element that should be filled. Should have type from
        ../proc_base_structures/controls.py
        :param data: Data that should be entered to field.
        :return:
        """
        allowed_types = (TextField, Dropdown, Checkbox, RadioButton)

        if not isinstance(field, allowed_types):
            raise AttributeError("Incorrect type of passed control. Should be"
                                 "in {}".format(allowed_types))
        entered_data = field.do_action(data)
        return entered_data

    @staticmethod
    def fill_fields(controls, data=None):
        """
        fill passed fields with data if passed, random values used otherwise
        :param controls: list of controls objects
        :param data: data that should be entered in control. Should be repr.
        in format {'control_name':'data_to_enter'}
        :return:
        """
        fields_data = dict()
        for control in controls:
            data
            entered_data = ActionChains.fill_field(control, data=data)
            if entered_data:
                fields_data.update(entered_data)
        return fields_data

    @staticmethod
    def click_button(controls, btn_name):
        """
        clicks on passed button
        :param controls: web element buttons to search button on
        :param btn_name: temp argument that is required by current do_action
        signature
        :return:
        """
        for control in controls:
            if control.get_name().lower() == btn_name.lower():
                control.do_action(btn_name)
                return
        raise AssertionError("There is no button with name {} not found!"
                             "".format(btn_name))
