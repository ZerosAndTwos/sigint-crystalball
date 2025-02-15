from behave import *

@when('I signed in as "{user_type}" user')
def signed_in_as_user(context, user_type):
    context.auth_token = context.auth_manager.get_token(user_type)
