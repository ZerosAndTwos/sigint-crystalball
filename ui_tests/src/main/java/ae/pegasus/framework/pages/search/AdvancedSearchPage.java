package ae.pegasus.framework.pages.search;

import ae.pegasus.framework.constants.search.advanced.TypeOfAdvancedSearch;
import ae.pegasus.framework.utils.PageUtils;
import com.codeborne.selenide.SelenideElement;

import static ae.pegasus.framework.constants.search.advanced.TypeOfAdvancedSearch.*;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class AdvancedSearchPage extends SearchPage {

    @Override
    public boolean isPageDisplayed() {
        return getPageTitle().equalsIgnoreCase("Advanced Search")
                && getSearchTypeTab(KEYWORDS).isDisplayed()
                && getSearchTypeTab(FUZZY).isDisplayed()
                && getSearchTypeTab(PROXIMITY).isDisplayed()
                && getSearchTypeTab(BOOST_KEYWORD).isDisplayed()
                && getSearchTypeTab(FIELD_SEARCH).isDisplayed();
    }
private SelenideElement getAdvanceSearchPage()
{
    return  $x("//div[@class ='pg-advanced-search__input-panel']");
}

    private SelenideElement getAdvanceSearchInputField()
    {
        return   getAdvanceSearchPage().$x(".//pg-field[@label ='All these words']//input");
    }

    private SelenideElement getAdvanceSearchQueryPage()
    {
        return  $x("//div[@class ='pg-advanced-search__editor-panel']");
    }


    private SelenideElement getSearchTypeTab(TypeOfAdvancedSearch searchType) {
        for (SelenideElement tab : $$x("//div[@class='pg-nav-tabs__tabs']/div")) {
            if (tab.getText().trim().equalsIgnoreCase(searchType.getDisplayName())) {
                return tab;
            }
        }
        throw new IllegalArgumentException("Tab '" + searchType.getDisplayName() + "' was not found");
    }

    public void clickBuildAdvanceQuery() {
        getAdvanceSearchPage().$x(".//button[@type ='submit']").click();
    }

    public void enterAdvanceSearchCriteria(String searchCriteria) {
        getAdvanceSearchInputField().shouldBe(enabled).click();
        PageUtils.clearAndType(getAdvanceSearchInputField(), searchCriteria);
    }
    public void startAdvanceSearch() {
        getAdvanceSearchQueryPage().$x(".//button[@class ='btn btn-info pg-advanced-search-editor__submit au-target']").click();
        sleep(3000);}

}
