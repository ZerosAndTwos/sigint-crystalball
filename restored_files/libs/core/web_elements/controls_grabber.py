import behave

from libs.core.web_elements.controls_types.controls_element import RadioButton, \
    Checkbox, TextField, Button, Dropdown
from libs.utils.enums import Selectors
from libs.utils.selenium_extensions import collect_elements_from_parent


class Grabber(object):
    """
    Base class for Controls grabbers
    """
    def __init__(self, context):
        self.context = context

    def _collect_controls(self, form_locator):
        """
        Collect all controls on the form based on form_locator.
        :param form_locator: locator of the form
        :return: list of controls that are found on form with 'form_locator'
        based on control type selectors. Available class selectors are listed
        in module utils.enums -> Selectors
        """
        controls = collect_elements_from_parent(self.context.driver, form_locator,
                                                *self.__class__.selector)
        return controls

    def get_controls(self, form_locator, mapping):
        """
        creates a control object and return it as generator object
        :param form_locator: locator of the form to find controls on
        :param mapping: dict that is used to find control's name. If name is
        not found, control will not be created. If mapping is None, all
        control will be created with name 'default'.
        :return:
        """
        controls = self._collect_controls(form_locator)
        if not controls:
            return
            yield
        for control in controls:
            name = self._define_name(control, mapping)
            if name is None:
                continue
            yield self.__class__.class_type(control, name)

    @staticmethod
    def _define_name(control, mapping):
        """
        Finds name of the control based on mapping table
        :param control: control to assign name for
        :return: name of the control
        """
        if mapping is None:
            return 'default'
        attributes = [control.get_attribute(selector)
                      for selector in Selectors.name_selectors]
        for attribute in attributes:
            if attribute in mapping.keys():
                return mapping[attribute]
        return None


class InputGrabber(Grabber):
    """
    Grabber for input controls. Tag names of controls that will be searched
    are defined in 'selector' attribute. Control type (text_field, drop_down,
    check_box etc) that will be created is defined in mapper.
    """
    default_type = 'text'
    selector = Selectors.input_types
    mapper = {'radio':  RadioButton, 'checkbox': Checkbox, 'text': TextField,
              'button': Button, 'textarea': TextField, 'number': TextField,
              'select': Dropdown}

    def __init__(self, context):
        super(InputGrabber, self).__init__(context)

    def get_controls(self, form, mapping):
        """
        creates a control object and return it as generator object
        :param form_locator: locator of the form to find controls on
        :param mapping: dict that is used to find control's name. If name is
        not found, control will not be created. If mapping is None, all
        control will be created with name 'default'.
        :return:
        """
        controls = self._collect_controls(form)
        if not controls:
            return
            yield
        for control in controls:
            name = self._define_name(control, mapping)
            if name is None:
                continue
            if control.tag_name == 'select':
                ctrl_type = 'select'
            else:
                ctrl_type = control.get_attribute("type")
            if ctrl_type not in InputGrabber.mapper.keys():
                ctrl_type = InputGrabber.default_type
            class_type = InputGrabber.mapper[ctrl_type]
            yield class_type(control, name)


class ButtonGrabber(Grabber):
    """
    Grabber button controls. Tag names of controls that will be searched
    are defined in 'selector' attribute. Control type is defined in
    'class_type' attribute that will be created is defined in mapper.
    """
    selector = Selectors.button
    class_type = Button

    def __init__(self, context):
        super(ButtonGrabber, self).__init__(context)


class ControlGrabbers(object):
    """
    This factory is used to define specific control grabber based on the
    control type
    """
    def __init__(self):
        pass

    @staticmethod
    def select_grabber(control_type, context):
        """
        Defines a specific grabber factory based on the passed control_type
        :param control_type: type of controls to grab for.
        :return: controls grabber factory
        """
        control_type = control_type.lower()
        if control_type == 'input':
            return InputGrabber(context)
        elif control_type in 'button':
            return ButtonGrabber(context)
        else:
            raise AssertionError("Incorrect selector!.Available selectors:{}"
                                 "".format(Selectors.selectors))



