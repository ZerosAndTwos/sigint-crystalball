package ae.pegasus.framework.elements.controls.dropdown;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import ae.pegasus.framework.utils.PageUtils;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static ae.pegasus.framework.constants.CommonXPaths.DROP_CONTENT_BASE_XPATH;
import static ae.pegasus.framework.constants.CommonXPaths.INTERNAL_LOADING_XPATH;

public class FileSelector extends CommonDropDown {

    public FileSelector(SelenideElement fileSelectorBase) {
        super(fileSelectorBase);
    }

    private SelenideElement getExpandedFileSelectorBase() {
        return $x(DROP_CONTENT_BASE_XPATH + "//cb-file-tree-select");
    }

    private void fileSelectorOpenClose() {
        $x(".//span/pg-btn").click();
    }

    @Override
    public void expand() {
        if(!getExpandedFileSelectorBase().isDisplayed()) {
            performExpand();
        }
    }

    @Override
    public void collapse() {
        if(getExpandedFileSelectorBase().isDisplayed()) {
            performCollapse();
        }
    }

    @Override
    protected void performExpand() {
        fileSelectorOpenClose();
    }

    @Override
    protected void performCollapse() {
        fileSelectorOpenClose();
    }

    private SelenideElement getFileSelectorLoading() {
        return getExpandedFileSelectorBase().$x(INTERNAL_LOADING_XPATH);
    }

    private void waitFileSelectorLoading() {
        try {
            getFileSelectorLoading().waitUntil(Condition.visible, 100, 10);
            System.out.println("File selector's loading appears");
        } catch (UIAssertionError e) {
            System.out.println("File selector's loading does not appear");
            //Do nothing since element can be absent
        }
        getFileSelectorLoading().shouldBe(Condition.hidden);
    }

    private String getFileSelectorItemText(SelenideElement item) {
        return getDropDownItemText(item);
    }

    private void selectOneItem(String itemToSelect) {
        for (SelenideElement itemElements : getExpandedFileSelectorBase().$$x(".//cb-file-tree-node").shouldBe(sizeGreaterThanOrEqual(1))) {
            if (getFileSelectorItemText(itemElements).equalsIgnoreCase(itemToSelect)) {
                itemElements.click();
                return;
            }
        }
        throw new IllegalArgumentException("File Selector's item '" + itemToSelect + "' was not found");
    }


    public void selectItems(String...items) {
        expand();
        for (String item : items) {
            PageUtils.clearAndType(getExpandedFileSelectorBase().$x(".//input"), item);
            waitFileSelectorLoading();
            selectOneItem(item);
        }
        collapse();
    }

    private SelenideElement getNextAlreadySelectedFile() {
        return $x(".//pg-badge");
    }

    public void removeAllSelectedItems() {
        SelenideElement alreadySelectedFile = getNextAlreadySelectedFile();
        while(alreadySelectedFile.isDisplayed()) {
            alreadySelectedFile.$x(".//button").click();
            alreadySelectedFile = getNextAlreadySelectedFile();
        }
    }

}
