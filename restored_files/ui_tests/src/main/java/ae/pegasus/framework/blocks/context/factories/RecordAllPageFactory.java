package ae.pegasus.framework.blocks.context.factories;

import ae.pegasus.framework.blocks.context.ContextPageFactory;
import ae.pegasus.framework.blocks.context.tables.EntityTable;
import ae.pegasus.framework.blocks.context.toolbars.EntityToolbar;

import ae.pegasus.framework.blocks.context.toolbars.SearchToolbar;
import ae.pegasus.framework.blocks.context.toolbars.records.RecordsSearchBar;
import ae.pegasus.framework.blocks.context.tables.records.RecordsAllTable;
import ae.pegasus.framework.blocks.context.toolbars.records.RecordsAllToolbar;

public class RecordAllPageFactory implements ContextPageFactory {

    @Override
    public EntityTable createTable() {
        return new RecordsAllTable();
    }

    @Override
    public EntityToolbar createToolBar() {
        return new RecordsAllToolbar();
    }

    @Override
    public SearchToolbar createSearchPanel() {
        return new RecordsSearchBar();
    }
}
