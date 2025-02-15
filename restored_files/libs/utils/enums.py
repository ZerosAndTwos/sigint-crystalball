class Selectors:
    input = ["input", "textarea"]
    select = ["select"]
    button = ["button"]
    disabled_controls = ["td[data-name]"]
    input_types = input + select
    button_type = button
    types = input_types + button_type
    name_selectors = ['id', 'name', 'class', 'value', 'type', 'tagname', 'au-target-id']

class UserRoles:
    allowed = ['user', 'admin', 'analyst', 'approver', 'tasker']
