package ae.pegasus.framework.pages.basic_pages.api;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public abstract class BaseSpecialPage extends BasePage {

    protected SelenideElement getSpecialPageBase() {
        return $x("//body/div[contains(@class, 'pg-drop__content')]");
    }
}
