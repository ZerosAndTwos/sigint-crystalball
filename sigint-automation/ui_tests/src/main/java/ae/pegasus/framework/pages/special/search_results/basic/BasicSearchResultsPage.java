package ae.pegasus.framework.pages.special.search_results.basic;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.pages.basic_pages.api.BasePage;

import static com.codeborne.selenide.Selenide.$x;

public abstract class BasicSearchResultsPage extends BasePage {

    protected final String GRID_ROW_XPATH = "//div[@class='pg-data-table2__viewport']//div[contains(@class, 'pg-data-table2__col--no-select') or contains(@class, 'pg-data-table2__col--select')]";
    protected final String GRID_TABLE_HEADER_XPATH = "//div[@class='pg-data-table2__head']//div[contains(@class, 'pg-data-table2__col--head-label')]";
    protected final String CARD_XPATH = "//cb-card";

    @Override
    public boolean isPageDisplayed() {
        return false;
    }

    protected SelenideElement getLoadMoreButton() {
        return $x("//span[text()='Load more']");
    }

    protected SelenideElement getSearchResultsSetupContainer() {
        return Selenide.$x("//pg-view/pg-row[2]");
    }

    protected SelenideElement getSearchResultsViewSwitcher() {
        return getSearchResultsSetupContainer().$x(".//pg-radio-btn-group[@options.one-way='viewOptions']");
    }

    protected SelenideElement getSearchResultsRepresentationSwitcher() {
        return getSearchResultsSetupContainer().$x(".//span[@class='m-r-md']/pg-radio-btn-group");
    }

    protected SelenideElement getSearchResultsTabsContainer() {
        return getSearchResultsSetupContainer().$x(".//div[contains(@class,'pg-nav-tabs__tabs')]");
    }

    protected boolean isElementSelected(SelenideElement elementToCheck) {
        return elementToCheck.getAttribute("class").contains("pg-btn-info");
    }
}
