package blocks.context.factories;

import blocks.context.ContextPageFactory;
import blocks.context.tables.EntityTable;
import blocks.context.tables.reports.ReportsTable;
import blocks.context.toolbars.EntityToolbar;
import blocks.context.toolbars.SearchToolbar;
import blocks.context.toolbars.reports.ReportsToolbar;

public class ReportsReadyPageFactory implements ContextPageFactory {
    @Override
    public EntityTable createTable() {
        return new ReportsTable();
    }

    @Override
    public EntityToolbar createToolBar() {
        return new ReportsToolbar();
    }

    @Override
    public SearchToolbar createSearchPanel() {
        return null;
    }
}
