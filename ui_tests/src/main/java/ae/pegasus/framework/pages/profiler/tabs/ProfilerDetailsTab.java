package ae.pegasus.framework.pages.profiler.tabs;

import ae.pegasus.framework.constants.profiler.ProfilerWidget;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;

import static ae.pegasus.framework.constants.CommonXPaths.LOADING_BASE_XPATH;
import static ae.pegasus.framework.constants.profiler.ProfilerWidget.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ProfilerDetailsTab extends ProfilerSummaryTab {


    private final int IDENTIFIER_HEADING = 0;
    private final int IDENTIFIER_VALUE = 1;


    protected ElementsCollection getAllLoadings() {
        return $$x(LOADING_BASE_XPATH);
    }

    protected SelenideElement get1stLoading() {
        return $x(LOADING_BASE_XPATH);
    }

    @Override
    public void waitForPageLoading(long delayForLoadingAppearInMS) {
        try {
            get1stLoading().waitUntil(visible, 100 + delayForLoadingAppearInMS, 10);
            System.out.println("Tab's loading(s) appear");
        } catch (UIAssertionError e) {
            System.out.println("Tab's loading(s) do not appear");
            //Do nothing since element can be absent
        }
        getAllLoadings().shouldHaveSize(0);
    }

    private String getWidgetHeading(ProfilerWidget widget) {
        return getBaseWidget(widget).$x(".//span").getText();
    }


    public boolean isWidgetHeadingCorrect(ProfilerWidget widget) {
        return (getBaseWidget(widget).isDisplayed() && getWidgetHeading(widget).equals(widget.getWidgetName()));
    }

    private SelenideElement getEIDheader() {
        return getBaseWidget(EID_INFORMATION).$x(".//div[@class='pg-card__header']");
    }

    private SelenideElement getEIDPersonName(String name) {
        return getEIDheader().$x(".//div[text() ='" + name + "']");
    }

    public boolean isEIDPersonNameDisplayed(String name) {
        return getEIDPersonName(name).getText().equals(name);
    }


    private SelenideElement getEIDAttributeByValue(String value) {
        return getBaseWidget(EID_INFORMATION).$x(".//span[text() ='" + value + "']");
    }


    public boolean checkEIDValueIsDiaplyed(String value) {
        return getEIDAttributeByValue(value).getText().equals(value);
    }

    public boolean isThreadScoreCorrect(String score) {
        return getBaseWidget(THREAT_SCORE).$x("//table//td[@class='pg-threat-matrix-square pg-threat-matrix-square--50 active']").getAttribute("data-value").toString().equals(score);
    }


    private ElementsCollection getIdentifiers() {
        return getBaseWidget(KEY_ATTRIBUTES).$$x(".//tr");
    }

    public ElementsCollection getIdentifiersValues(SelenideElement identifierRow) {
        return identifierRow.$$x(".//td");
    }

    public boolean isAttributeDisplayed(String parameter, String value) {
        for (SelenideElement identifierRow : getIdentifiers()) {
            if (getIdentifiersValues(identifierRow).get(IDENTIFIER_HEADING).getText().trim().equals(parameter)
                    && getIdentifiersValues(identifierRow).get(IDENTIFIER_VALUE).getText().trim().equals(value)) {

                return true;

            }
        }
        return false;
    }
}


//profile-biographic-other-attributes-widget//div[@class='pg-card__header']//div