from libs.core.web_elements.controls_grabber import ControlGrabbers


class Controls(object):

    def __init__(self, form_locator, mapping, context):
        """
        Creates an instance Controls collection
        :return: None
        :arg controls: list of controls that are located on the form
        :arg mapping: mapping dictionary that is used to assign name to a
        control
        """
        super(Controls, self).__init__()
        self.context = context
        self.form_locator = form_locator
        self.mapping = mapping

    def _collect_controls(self, selector):
        """
        This method collect all available controls as web elements.
        Refer to ../controls_grabber.py for collecting controls logic based
        on passed selector.
        :param selector: type of control to collect. Available: input, button
        :return: generator object over controls.
        """

        grabber = ControlGrabbers.select_grabber(selector, self.context)
        return grabber.get_controls(self.form_locator, self.mapping)

    def get_control_by_name(self, name):
        """
        Gets control by it's name
        :param name: name of the control
        :return: control obj if found, otherwise None is returned
        """
        for control in self.get_input_controls():
            if control.get_name().lower() == name.lower():
                return control
        return None

    def get_input_controls(self):
        """
        return all input controls. ControlsGrabber object is used to collect
        controls.
        :return: generator over input controls
        """
        return self._collect_controls('input')

    def get_button_controls(self):
        """
        return all button controls. ControlsGrabber object is used to collect
        controls.
        :return: generator over input controls
        """
        return self._collect_controls('button')
