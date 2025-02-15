package blocks.context.factories;

import blocks.context.ContextPageFactory;
import blocks.context.tables.EntityTable;
import blocks.context.tables.reports.ReportsDraftTable;
import blocks.context.toolbars.EntityToolbar;
import blocks.context.toolbars.SearchToolbar;

import static com.codeborne.selenide.Selenide.page;

public class ReportsDraftPageFactory implements ContextPageFactory {

    @Override
    public EntityTable createTable() {
        return page(ReportsDraftTable.class);
    }

    @Override
    public EntityToolbar createToolBar() {
        return null;
    }

    @Override
    public SearchToolbar createSearchPanel() {
        return null;
    }
}
