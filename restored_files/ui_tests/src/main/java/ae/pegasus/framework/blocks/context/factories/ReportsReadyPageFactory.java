package ae.pegasus.framework.blocks.context.factories;

import ae.pegasus.framework.blocks.context.ContextPageFactory;
import ae.pegasus.framework.blocks.context.tables.reports.ReportsTable;
import ae.pegasus.framework.blocks.context.tables.EntityTable;
import ae.pegasus.framework.blocks.context.toolbars.EntityToolbar;
import ae.pegasus.framework.blocks.context.toolbars.SearchToolbar;
import ae.pegasus.framework.blocks.context.toolbars.reports.ReportsToolbar;

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
