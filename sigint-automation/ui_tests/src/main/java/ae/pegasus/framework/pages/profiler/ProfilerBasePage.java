package ae.pegasus.framework.pages.profiler;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.constants.cbfinder.TargetAction;
import ae.pegasus.framework.constants.profiler.ProfilerTab;
import ae.pegasus.framework.constants.profiler.ProfilerWidget;
import ae.pegasus.framework.pages.Pages;
import ae.pegasus.framework.pages.basic_pages.api.BasePage;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static ae.pegasus.framework.conditions.AttributesConditions.attributeContainsValue;
import static ae.pegasus.framework.constants.special.modal_dialog.ModalDialogButton.YES;

public class ProfilerBasePage extends BasePage {
    @Override
    public boolean isPageDisplayed() {
        return getPageTitle().equalsIgnoreCase("Profiler");
    }

    private ElementsCollection getTabs() {
        return $$x("//div[@class='pg-nav-tabs__tabs']/div");
    }

    private SelenideElement getTab(ProfilerTab tab) {
        for(SelenideElement tabElement : getTabs()) {
            if(tabElement.$x("./span").getText().trim().equalsIgnoreCase(tab.getTabName())) {
                return tabElement;
            }
        }
        throw new IllegalArgumentException("Profiler's tab with name '" + tab.getTabName() + "' was not found");
    }

    public boolean isTabSelected(ProfilerTab tab) {
        return getTab(tab).has(attributeContainsValue("class", "pg-nav-tabs__tab--active"));
    }

    public  void openTab(ProfilerTab tab) {
        getTab(tab).click();
    }

    protected SelenideElement getBaseWidget(ProfilerWidget widget) {
        if (!isTabSelected(widget.getParentTab())) {
            openTab(widget.getParentTab());
        }
        return $x(widget.getBaseXPath());
    }



    public void performActionForWidget(ProfilerWidget widget, TargetAction action) {
        getBaseWidget(widget).$x(".//div[@class='pg-panel__actions']/pg-dropdown").click();
        getActionFromDropDownMenu(action.getActionName()).click();
        Pages.modalDialogPage().clickButtonIfDialogWithTitleAppeared("Confirm!", YES, 300);
    }

    public boolean isTabDisplayed(ProfilerTab tab)
    {
        try {
            return getTab(tab).isDisplayed();
        } catch (Throwable e) {
            return false;
        }
    }
}
