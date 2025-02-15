package ae.pegasus.framework.pages.special.IM_requests;

import ae.pegasus.framework.constants.special.IM.IMAction;
import ae.pegasus.framework.pages.basic_pages.api.BasePage;
import com.codeborne.selenide.SelenideElement;

import static ae.pegasus.framework.constants.CommonXPaths.INTERNAL_LOADING_XPATH;
import static com.codeborne.selenide.Selenide.$x;

public abstract class BaseIMPage extends BasePage {

    @Override
    public boolean isPageDisplayed() {
        return false;
    }

    protected SelenideElement getReportOrRequestBase() {
        SelenideElement result = $x("//ux-dialog");
        if (result.isDisplayed()) {
            return result;
        }
        return $x("//router-view");
    }

    protected SelenideElement getFieldByLabel(String fieldLabel) {
        SelenideElement field = getReportOrRequestBase().$x(".//pg-field[@label='" + fieldLabel + "']");
        scrollWindowTo(field);
        return field;
    }

    protected SelenideElement getReportOrRequestHeader() {
        return getReportOrRequestBase().$x(".//div[@class='im-document__header']");
    }

    protected SelenideElement getMasterReportOriginatingReportsBlock() {
        return getReportOrRequestBase().$x(".//div[@slot ='document-records']");
    }



    protected SelenideElement getReportOrRequestStaticInformationBlock() {
        return getReportOrRequestHeader().$x(".//div[contains(@class,'media-body')]");
    }

    protected SelenideElement getMoreActionsDropdown() {
        return getReportOrRequestHeader().$x(".//div[@class='cb-detail__actions']/pg-dropdown");
    }

    @Override
    protected SelenideElement getPageLoading() {
        return getReportOrRequestBase().$x(INTERNAL_LOADING_XPATH);
    }

    public void performAction(IMAction action) {
        if (action.isInMoreAction()) {
            getMoreActionsDropdown().click();
            if (action.getParentAction() != null) {
                SelenideElement parentAction = getActionFromDropDownMenu(action.getParentAction().getActionName());
                getActionFromDropDownSubMenu(parentAction, action.getActionName()).click();
            } else {
                getActionFromDropDownMenu(action.getActionName()).click();
            }
        } else {
            for (SelenideElement button : getReportOrRequestHeader().$$x(".//pg-btn//span/span[1]")) {
                if(button.getText().trim().equalsIgnoreCase(action.getActionName())) {
                    button.click();
                    return;
                }
            }
            throw new IllegalArgumentException("Action '" + action.getActionName() + "' was not found");
        }
    }

    public String getReportOrRequestNumber() {
        return getReportOrRequestStaticInformationBlock().$x(".//h4").getText().trim();
    }
}
