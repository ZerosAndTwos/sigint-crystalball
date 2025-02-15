package ae.pegasus.framework.pages.profiler.tabs;

import ae.pegasus.framework.utils.TimeUtils;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import ae.pegasus.framework.constants.profiler.TargetSummaryParameter;
import ae.pegasus.framework.pages.profiler.ProfilerBasePage;
import ae.pegasus.framework.utils.PageUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static ae.pegasus.framework.constants.CommonXPaths.LOADING_BASE_XPATH;
import static ae.pegasus.framework.constants.profiler.ProfilerTab.SUMMARY;
import static ae.pegasus.framework.constants.profiler.ProfilerWidget.*;
import static ae.pegasus.framework.constants.profiler.TargetSummaryParameter.PHOTO;
import static ae.pegasus.framework.constants.profiler.TargetSummaryParameter.TARGET_STATUS;

public class ProfilerSummaryTab extends ProfilerBasePage {


    private final int IDENTIFIER_COL_NUMBER = 1;
    private final int TYPE_COL_NUMBER = 2;
    private final int HITS_COL_NUMBER = 3;
    private final int MENTIONS_COL_NUMBER = 4;

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

    private SelenideElement getSummaryParameterColon(TargetSummaryParameter parameter) {
        return getBaseWidget(TARGET_SUMMARY).$x(".//pg-row/pg-col[" + parameter.getSummaryPanelColonNumber() + "]");
    }

    private SelenideElement getSummaryParameterBaseElement(TargetSummaryParameter parameter) {
        SelenideElement colon = getSummaryParameterColon(parameter);
        switch (parameter) {
            case PHOTO:
                return colon.$x(".//pg-avatar");
            case TARGET_NAME:
                return colon.$x("./div");
            case DESCRIPTION:
                return colon.$x(".//pg-ellipsis/div/span");
            case FILES:
                return colon.$x(".//cb-file-tags");
            case CATEGORY:
            case VOICE_ID:
            case THREAT_SCORE:
            case TARGET_STATUS:
            case CLASSIFICATION:
            case CRIMINAL_RECORD:
                return colon.$x(".//span[text()='" + parameter.getSummaryParameterName() + "']/../span[2]");
            default:
                throw new IllegalArgumentException("Unexpected Summary Tab's Parameter '" + parameter.getSummaryParameterName() + "'");
        }
    }

    public boolean isPhotoDisplayed() {
        return getSummaryParameterBaseElement(PHOTO).isDisplayed();
    }

    public String getSingleValueParameter(TargetSummaryParameter parameter) {
        SelenideElement baseElement = getSummaryParameterBaseElement(parameter);
        switch (parameter) {
            case TARGET_NAME:
            case DESCRIPTION:
            case CATEGORY:
            case CLASSIFICATION:
            case THREAT_SCORE:
            case CRIMINAL_RECORD:
            case TARGET_STATUS:
                return baseElement.getText();
            case VOICE_ID:
                return baseElement.getText().replace(baseElement.$x(".//small").getText(), "").trim();
            default:
                throw new IllegalArgumentException("Summary Tab's Parameter '" + parameter.getSummaryParameterName() + "' is not a single value parameter");
        }
    }

    public List<String> getMultiValueParameter(TargetSummaryParameter parameter) {
        SelenideElement baseElement = getSummaryParameterBaseElement(parameter);
        switch (parameter) {
            case FILES:
                return PageUtils.convertElementsCollectionToStringList(baseElement.$$x(".//cb-file-tag"), true);
            default:
                throw new IllegalArgumentException("Summary Tab's Parameter '" + parameter.getSummaryParameterName() + "' is not a multi-value parameter");
        }
    }

    public LocalDateTime getExpiresOn() {
        String baseText = getSummaryParameterBaseElement(TARGET_STATUS).$x("./..").getText();
        String startOfDateIdent = "Expires on";
        return TimeUtils.convertToLocalDateTime(
                baseText.substring(baseText.indexOf(startOfDateIdent) + startOfDateIdent.length()).trim(),
                "dd/MM/yyyy");
    }

    private SelenideElement getLastSeenMap() {
        return getBaseWidget(LAST_SEEN).$x(".//pg-map");
    }

    public boolean isLastSeenMapDisplayed() {
        return getLastSeenMap().isDisplayed();
    }

    private ElementsCollection getIdentifiers() {
        return getBaseWidget(IDENTIFIERS).$$x(".//div[@class='pg-tbody']/div");
    }

    private SelenideElement getCell (SelenideElement identifierRow, int columnNumber) {
        return identifierRow.$x("./div[" + columnNumber + "]");
    }

    private String getIdentifierValue(SelenideElement identifierRow) {
        return getCell(identifierRow, IDENTIFIER_COL_NUMBER).$x(".//cb-identifier-value").getText().trim();
    }

    private String getIdentifierType(SelenideElement identifierRow) {
        return getCell(identifierRow, TYPE_COL_NUMBER).getText().trim();
    }

    private int getIdentifierHits(SelenideElement identifierRow) {
        return Integer.parseInt(getCell(identifierRow, HITS_COL_NUMBER).getText().trim());
    }

    private int getIdentifierMentions(SelenideElement identifierRow) {
        return Integer.parseInt(getCell(identifierRow, MENTIONS_COL_NUMBER).getText().trim());
    }

    public void openTab() {
        openTab(SUMMARY);
        waitForPageLoading();
    }

    public boolean isIdentifierDisplayed(String value, String type, Integer hits, Integer mentions) {
        for (SelenideElement identifierRow : getIdentifiers()) {
            if (getIdentifierValue(identifierRow).equals(value)) {
                if (getIdentifierType(identifierRow).equalsIgnoreCase(type)) {
                    scrollWindowTo(identifierRow);
                    if (hits == null || getIdentifierHits(identifierRow) == hits) {
                        if (mentions == null || getIdentifierMentions(identifierRow) == mentions) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
