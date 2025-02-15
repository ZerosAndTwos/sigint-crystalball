package ae.pegasus.framework.pages.cbfinder;

import ae.pegasus.framework.assertion.Asserter;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import ae.pegasus.framework.constants.cbfinder.CreateAction;
import ae.pegasus.framework.pages.basic_pages.api.BasePage;
import ae.pegasus.framework.utils.PageUtils;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static ae.pegasus.framework.conditions.AttributesConditions.attributeContainsValue;
import static ae.pegasus.framework.constants.CommonXPaths.INTERNAL_LOADING_XPATH;

public class CBFinderBasePage extends BasePage {

    @Override
    public boolean isPageDisplayed() {
        return getPageTitle().equalsIgnoreCase("CB Finder");
    }

    protected SelenideElement getCBFinderSidePanel() {
        return $x("//pg-col/pg-panel");
    }

    protected SelenideElement getRightHalfOfHeader() {
        return getDialogForSelectedItem().$x(".//div[@class='cb-detail__header-right']");
    }

    private SelenideElement getSearchBlock() {
        return getCBFinderSidePanel().$x(".//div[contains(@class,'pg-search-group')]");
    }

    private SelenideElement getSearchInput() {
        return getSearchBlock().$x("./input");
    }

    private SelenideElement getSearchButton() {
        return getSearchBlock().$x("./span");
    }

    private SelenideElement getSidePanelLoading() {
        return getCBFinderSidePanel().$x(INTERNAL_LOADING_XPATH);
    }

    public void waitSidePanelLoading() {
        try {
            getSidePanelLoading().waitUntil(visible, 100, 10);
        } catch (UIAssertionError e) {
            //Do nothing since element can be absent
        }
        getSidePanelLoading().shouldBe(hidden);
    }

    public void performAction(CreateAction action) {
        getCBFinderSidePanel().$x(".//pg-col[1]//pg-dropdown").click();
        getActionFromDropDownSubMenu(getActionFromDropDownMenu("Create new"), action.getActionNameInSidePanel()).click();
    }

    protected SelenideElement getDialogForSelectedItem() {
        return $x("//router-view");
    }

    protected SelenideElement getHeaderOfDialogForSelectedItem() {
        return getDialogForSelectedItem().$x(".//cb-file-detail//div[@class='media-body']/h4");
    }

    private SelenideElement getSelectedItemPanelLoading() {
        return getDialogForSelectedItem().$x(INTERNAL_LOADING_XPATH);
    }

    public void waitSelectedItemLoading() {
        try {
            getSelectedItemPanelLoading().waitUntil(visible, 100, 10);
        } catch (UIAssertionError e) {
            //Do nothing since element can be absent
        }
        getSelectedItemPanelLoading().shouldBe(hidden);
    }

    private static final String FILE_SELECTION_ATTR = "class";
    private static final String FILE_SELECTION_ATTR_VALUE = "cb-file-tree-node--selected";

    private SelenideElement getFileSelectionIndicatorElement(SelenideElement file) {
        return file.$x("./div");
    }

    private void waitForFileDeselection(SelenideElement file) {
        getFileSelectionIndicatorElement(file).shouldNotHave(attributeContainsValue(FILE_SELECTION_ATTR, FILE_SELECTION_ATTR_VALUE));
    }

    private boolean isFileSelected(SelenideElement file) {
        return getFileSelectionIndicatorElement(file).getAttribute(FILE_SELECTION_ATTR).contains(FILE_SELECTION_ATTR_VALUE);
    }

    private final int MAX_TRIES = 6;
    private SelenideElement getFile(String fileName) {
        int tryCount = 0;
        while (tryCount <= MAX_TRIES) {
            searchFile(fileName);
            for (SelenideElement item : $$x("//cb-file-tree//cb-file-tree-node")) {
                if (item.$x(".//text-highlight-decorator/span").getText().equals(fileName)) {
                    return item;
                }
            }
            //FIXME This is patch for problem with file search
            searchFile("");
            Asserter.getAsserter().softAssertTrue(false, "", "Search file patch was applied");
            tryCount++;
        }
        throw new IllegalArgumentException("File '" + fileName + "' was not found");
    }

    private void searchFile(String fileName) {
        PageUtils.clearAndType(getSearchInput(), fileName);
        getSearchButton().click();
        waitSidePanelLoading();
    }

    public boolean isFileSelected(String fileName) {
        return isFileSelected(getFile(fileName));
    }

    public void selectFile(String fileName) {
        SelenideElement file = getFile(fileName);
        //TODO Think if check for file section is not required
//        if (!isFileSelected(file)) {
//            file.click();
//            waitSelectedItemLoading();
//        }
        file.click();
        waitSelectedItemLoading();
    }
    public void selectCase(String caseName) {
        selectFile(caseName);
    }
}