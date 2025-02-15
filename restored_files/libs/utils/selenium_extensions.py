import itertools
from selenium.webdriver.remote.webelement import WebElement
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


def collect_elements_from_parent(driver, parent_id, *args):
    if isinstance(parent_id, WebElement):
        form = parent_id
    else:
        form = wait_element(driver, parent_id)
    if not form:
        return
    controls_list = list(itertools.chain(*map(
        form.find_elements_by_css_selector, args)))
    return [control for control in controls_list if control.is_displayed()]


def wait_element(driver, locator, BY=By.CSS_SELECTOR, timeout=20):
    element = \
        WebDriverWait(driver, timeout).until(
            EC.presence_of_element_located((BY, locator)))

    return element


