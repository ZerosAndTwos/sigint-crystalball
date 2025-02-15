package ae.pegasus.framework.elements.controls.dropdown;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import org.openqa.selenium.Keys;
import ae.pegasus.framework.utils.PageUtils;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static ae.pegasus.framework.constants.CommonXPaths.DROP_CONTENT_BASE_XPATH;
import static ae.pegasus.framework.constants.CommonXPaths.INTERNAL_LOADING_XPATH;

public class DropDown extends CommonDropDown {

    private final boolean hasLoading;

    public DropDown(SelenideElement dropDownBase, boolean hasLoading) {
        super(dropDownBase);
        this.hasLoading = hasLoading;
    }

    private void dropDownOpenClose() {
        $x(".//div[contains(@class, 'pg-select__select')]").click();
    }

    @Override
    public void expand() {
        if(!getDropdownBase().isDisplayed()) {
            performExpand();
        }
    }

    @Override
    public void collapse() {
        if(getDropdownBase().isDisplayed()) {
            performCollapse();
        }
    }

    @Override
    protected void performExpand() {
        dropDownOpenClose();
        if (hasLoading) {
            waitDropDownLoading();
        }
    }

    @Override
    protected void performCollapse() {
        dropDownOpenClose();
    }

    private SelenideElement getDropDownInput() {
        return $x(".//input");
    }

    public void searchAndSelectItems(String...items) {
        expand();
        for (String item : items) {
            PageUtils.clearAndType(getDropDownInput(), item);
            if (hasLoading) {
                waitDropDownLoading();
            }
            selectOneItem(item);
        }
        collapse();
    }

    private SelenideElement getDropdownBase() {
        return $x(DROP_CONTENT_BASE_XPATH + "/ul[contains(@class, 'pg-select__content')]");
    }

    private ElementsCollection getDropDownElements() {
        return getDropdownBase().$$x("./li[contains(@class, 'pg-select__element')]");
    }

    private SelenideElement getDropDownLoading() {
        return getDropdownBase().$x(INTERNAL_LOADING_XPATH);
    }

    public void waitDropDownLoading() {
        try {
            getDropDownLoading().waitUntil(visible, 100, 10);
            System.out.println("Drop down's loading appears");
        } catch (UIAssertionError e) {
            System.out.println("Drop down's loading does not appear");
            //Do nothing since element can be absent
        }
        getDropDownLoading().shouldBe(hidden);
    }

    private void selectOneItem(String searchItem) {
        for (SelenideElement item : getDropDownElements().shouldBe(sizeGreaterThanOrEqual(1))) {
            if (getDropDownItemText(item).equalsIgnoreCase(searchItem)) {
                item.click();
                return;
            }
        }
        throw new IllegalArgumentException("Drop down item '" + searchItem + "' was not found");
    }

    public void selectItems(String...items) {
        expand();
        for (String item : items) {
            selectOneItem(item);
        }
        collapse();
    }

    private ElementsCollection getItemsAlreadySelectedInDropDown() {
        return $$x(".//div[@ref='tagsElement']/span");
    }

    public void removeAllSelectedItems() {
        while(!getItemsAlreadySelectedInDropDown().isEmpty()) {
            getDropDownInput().sendKeys(Keys.BACK_SPACE);
        }
    }
}
