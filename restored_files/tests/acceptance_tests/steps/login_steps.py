from behave import *

from libs.pages.sign_in_page import SignInPage


@when('I login as "{user_account}"')
def login_as_user(context, user_account):
    sign_in_page = SignInPage(context)
    sign_in_page.sign_in(user_account)


@then("User is logged in")
def is_logged_in(context):
    pass
