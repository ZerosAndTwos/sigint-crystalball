from libs.core.base_web_page_structures.web_form_actions import \
    WebFormBaseActions
from libs.core.base_web_page_structures.web_form_with_controls import \
    WebFormWithControls
from libs.utils.enums import UserRoles


class SignInPage(object):

    def __init__(self, context):
        self.context = context
        self.actions = WebFormBaseActions(SignInModel(context))
        super(SignInPage, self).__init__()

    def sign_in(self, user_account):
        if user_account.lower() not in UserRoles.allowed:
            raise AssertionError("Not allowed user roles")
        user = self.context.users.get_user(user_account)
        self.context.logger.debug("Start filling Login form")
        self.actions.fill_field("Email", user.email)
        self.actions.fill_field("Password", user.password)
        self.context.logger.debug("Form is filled")
        self.actions.click_button("Submit")
        user.is_logined = True


class SignInModel(WebFormWithControls):

    mapping = {

        'inputEmail': 'Email',
        'inputPassword': 'Password',
        "158": 'Submit'
    }

    def __init__(self, context):
        super(SignInModel, self).__init__("form.form-signin",
                                          self.mapping, context)
