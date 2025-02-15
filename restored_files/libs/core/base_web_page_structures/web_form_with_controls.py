from libs.core.web_elements.controls import Controls


class WebFormWithControls(object):
    """
    Should be super class for forms where input controls exists.
    Allows to grab controls objects.
    Instance should be created only when appropriate web page is opened
    as controls are grabbed at that moment.
    """

    def __init__(self, form_locator, mapping, context):
        self.context = context
        super(WebFormWithControls, self).__init__()
        self._controls = Controls(form_locator, mapping, context)

    def get_inputs(self):
        """
        gets all input controls on current form: drop-downs, text area and
        fields, combo-box, check-box items
        :return: list of inputs elements
        """
        return self._controls.get_input_controls()

    def get_buttons(self):
        """
        gets all buttons on the form
        :return: list of button controls
        """
        return self._controls.get_button_controls()

    def get_control(self, name):
        """
        return control object
        :param name: name of the control
        :return: control object
        """
        return self._controls.get_control_by_name(name)
