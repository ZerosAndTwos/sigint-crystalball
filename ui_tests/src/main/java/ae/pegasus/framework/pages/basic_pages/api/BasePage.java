package ae.pegasus.framework.pages.basic_pages.api;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import ae.pegasus.framework.constants.controls.ControlType;
import org.apache.commons.lang.NotImplementedException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Locatable;
import ae.pegasus.framework.utils.PageUtils;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static ae.pegasus.framework.constants.CommonXPaths.PAGE_LOADING_XPATH;

public abstract class BasePage {

    private final int PAGE_APPEARANCE_WAIT_INTERVAL = 10;
    private final int PAGE_APPEARANCE_WAIT_LIMIT = 1000;

    protected int getYCenterPageOffset() {
        return 0;
    }
    protected int getXCenterPageOffset() {
        return 600;
    }

    public String getPageTitle() {
        return $x("//div[@class='pg-header']//ul[@class='pg-breadcrumb']//span").getText();
    }

    abstract public boolean isPageDisplayed();

    protected SelenideElement getPageLoading() {
        if($$x(PAGE_LOADING_XPATH).size()==1)
            return $x(PAGE_LOADING_XPATH);
        else
            return $$x(PAGE_LOADING_XPATH).get(1);


    }

    public void waitUntilPageAppeared() {
        int loopCount = 0;
        while(!isPageDisplayed()) {
            Selenide.sleep(PAGE_APPEARANCE_WAIT_INTERVAL);
            loopCount++;
            if (loopCount > PAGE_APPEARANCE_WAIT_LIMIT) {
                throw new IllegalStateException(
                        "Page '" + this.getClass().getName() + "' is not appeared within '" + PAGE_APPEARANCE_WAIT_INTERVAL * PAGE_APPEARANCE_WAIT_LIMIT + "' ms");
            }
        }
    }



    public final void waitForPageLoading() {
        waitForPageLoading(0);
    }






    public void waitForPageLoading(long delayForLoadingAppearInMS) {
        try {
            getPageLoading().waitUntil(visible, 1000 + delayForLoadingAppearInMS, 10);
            System.out.println("Page's loading appears");
        } catch (UIAssertionError e) {
            System.out.println("Page's loading does not appear");
            //Do nothing since element can be absent
        }

        getPageLoading().shouldBe(hidden);
    }

    protected SelenideElement getBasicDataElement(ControlType controlType, SelenideElement baseElement) {
        switch (controlType) {
            case INPUT:
                return baseElement.$x(".//input");
            case TEXT_AREA:
                return baseElement.$x(".//textarea");
            case SIMPLE_DROPDOWN:
            case DROPDOWN_WITH_SEARCH:
                return baseElement.$x(".//pg-select");

            case FILE_SELECTOR:
                return baseElement.$x(".//cb-file-tree-input");
            case RADIO_BUTTON_GROUP:
                return baseElement.$x(PageUtils.bindXPaths(
                        ".//pg-radio-btn-group",
                        ".//pg-radio-group"));
            case DATE_TIME_SELECTOR:
                return baseElement.$x(".//pg-date");
            case DATE_TIME_RANGE_SELECTOR:
                return baseElement.$x(".//pg-date-range");
            case CHECKBOX:
                return baseElement.$x(".//pg-checkbox");
            default:
                throw new NotImplementedException();
        }
    }

    protected ElementsCollection getRadioButtons(SelenideElement radioButtonGroup) {
        return radioButtonGroup.$$x(".//label/span");
    }

    //Drop-down menu actions
    private String getNameOfDropDownAction(SelenideElement item) {
        SelenideElement nameOfItem = item.$x(".//pg-menu-item-content");
        if (nameOfItem.isDisplayed()) {
            return nameOfItem.getText().trim();
        }
        return "";
    }

    private SelenideElement getActionFromDropDown(ElementsCollection actionItems, String actionName) {
        for (SelenideElement actionItem : actionItems) {
            if (getNameOfDropDownAction(actionItem).trim().equalsIgnoreCase(actionName)) {
                return actionItem;
            }
        }
        throw new IllegalArgumentException("Drop down menu action with name '" + actionName + "' was not found");
    }

    protected SelenideElement getActionFromDropDownMenu(String actionName) {
        return getActionFromDropDown($$x(".//pg-menu-item").shouldBe(sizeGreaterThanOrEqual(1)), actionName);
    }

    protected SelenideElement getActionFromDropDownSubMenu(SelenideElement subMenu, String actionName) {
        subMenu.hover();
        return getActionFromDropDownMenu(actionName);
    }


    //Other stuff
    public void scrollWindowTo(SelenideElement elem) {
        Point coordinatesInViewPort = ((Locatable) elem.getWrappedElement()).getCoordinates().inViewPort();
        Point coordinatesOnPage = ((Locatable) elem.getWrappedElement()).getCoordinates().onPage();
        coordinatesOnPage.y = calculateCoordinateWithOffset(coordinatesInViewPort.y, coordinatesOnPage.y, getYCenterPageOffset());
        coordinatesOnPage.x = calculateCoordinateWithOffset(coordinatesInViewPort.x, coordinatesOnPage.x, getXCenterPageOffset());
        Selenide.executeJavaScript("window.scroll" + coordinatesOnPage.toString());
    }

    private int calculateCoordinateWithOffset(int coordinateInViewPort, int coordinateOnPage, int pageOffset) {
        int resultCoordinate = 0;
        if (coordinateOnPage > pageOffset) {
            if (coordinateInViewPort < 0) {
                resultCoordinate = coordinateOnPage + pageOffset;
            } else {
                resultCoordinate = coordinateOnPage - pageOffset;
            }
        }
        return resultCoordinate;
    }

    private SelenideElement getPopUpOfAnyKind() {
        return $x("//div[@class='noty_layout']");
    }

    protected void closeAllPopUps() {
        SelenideElement popUp = getPopUpOfAnyKind();
        while(popUp.isDisplayed()) {
            try {
                WebElement closeButton = popUp.$x(".//div[@class='noty_close_button']").waitUntil(visible, 10).getWrappedElement();
                closeButton.click();
                popUp.shouldBe(hidden);
            } catch (UIAssertionError | WebDriverException e) {
                //Do nothing since element can disappear at any time
            }
            popUp = getPopUpOfAnyKind();
        }
    }

    protected SelenideElement getWarningPopUp() {
        return $x("//div[contains(@class,'pg-toast--warning')]");
    }

    protected SelenideElement getSuccessPopUp() {
        return $x("//div[contains(@class,'pg-toast--success')]");
    }
}
