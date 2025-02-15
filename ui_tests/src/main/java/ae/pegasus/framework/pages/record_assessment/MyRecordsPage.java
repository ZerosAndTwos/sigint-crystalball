package ae.pegasus.framework.pages.record_assessment;

import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.pages.basic_pages.api.BasePageWithSearch;

import static com.codeborne.selenide.Selenide.$x;

public class MyRecordsPage extends BasePageWithSearch {

    private final String CREATE_NEW_RECORD_TITLE = "Create a New Record...";

    @Override
    public boolean isPageDisplayed() {
        return getPageTitle().equalsIgnoreCase("My Records")
                && super.isPageDisplayed();
    }

    private SelenideElement getButton(String buttonTitle) {
        return $x("//pg-btn[@pg-tooltip='" + buttonTitle + "']");
    }

    public void startNewRecordCreation() {
        getButton(CREATE_NEW_RECORD_TITLE).click();
    }
}
