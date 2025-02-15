package blocks.context.factories;

import blocks.context.ContextPageFactory;
import blocks.context.tables.EntityTable;
import blocks.context.tables.reports.ReportsAllTable;
import blocks.context.toolbars.EntityToolbar;
import blocks.context.toolbars.SearchToolbar;
import blocks.context.toolbars.reports.ReportsToolbar;

import static com.codeborne.selenide.Selenide.page;


public class ReportsAllPageFactory implements ContextPageFactory {

    @Override
    public EntityTable createTable() {
        return page(ReportsAllTable.class);
    }

    @Override
    public EntityToolbar createToolBar() {
        return page(ReportsToolbar.class);
    }

    @Override
    public SearchToolbar createSearchPanel() {
        return null;
    }
}
