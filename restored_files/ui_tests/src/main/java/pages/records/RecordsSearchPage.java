package pages.records;

import model.AppContext;
import pages.SigintPage;
import pages.blocks.tables.RecordsTable;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.page;

public class RecordsSearchPage extends SigintPage {

    public static final String url = String.format("%s/#/app/records/search",
            AppContext.getContext().environment().getSigintHost());

    private RecordsTable table = page(RecordsTable.class);


    public RecordsTable getTable() {
        return table;
    }


    public RecordsSearchPage load() {
        if (getSidebar().getSubMenuItemByName("Search").isDisplayed()) {
            getSidebar().getSubMenuItemByName("Search").click();
        } else {
            getSidebar().getSubMenuItemByName("Records").click();
            getSidebar().getSubMenuItemByName("Search").click();
        }
        getHeader().getBreadcrumb().getCurrentPath().shouldHave(attribute("href", RecordsSearchPage.url));
        return this;
    }
}
