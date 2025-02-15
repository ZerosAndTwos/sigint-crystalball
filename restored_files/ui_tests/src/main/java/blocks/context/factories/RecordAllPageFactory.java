package blocks.context.factories;

import blocks.context.ContextPageFactory;
import blocks.context.tables.EntityTable;
import blocks.context.toolbars.EntityToolbar;

import blocks.context.toolbars.SearchToolbar;
import blocks.context.toolbars.records.RecordsSearchBar;
import blocks.context.tables.records.RecordsAllTable;
import blocks.context.toolbars.records.RecordsAllToolbar;

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
