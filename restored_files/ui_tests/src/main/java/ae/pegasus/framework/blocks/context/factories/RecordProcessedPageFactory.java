package ae.pegasus.framework.blocks.context.factories;

import ae.pegasus.framework.blocks.context.ContextPageFactory;
import ae.pegasus.framework.blocks.context.tables.EntityTable;
import ae.pegasus.framework.blocks.context.tables.records.RecordsProcessedTable;
import ae.pegasus.framework.blocks.context.toolbars.EntityToolbar;
import ae.pegasus.framework.blocks.context.toolbars.SearchToolbar;
import ae.pegasus.framework.blocks.context.toolbars.records.RecordsProcessedToolbar;
import ae.pegasus.framework.blocks.context.toolbars.records.RecordsSearchBar;

import static com.codeborne.selenide.Selenide.page;

public class RecordProcessedPageFactory implements ContextPageFactory {

    @Override
    public EntityTable createTable() {
        return page(RecordsProcessedTable.class);
    }

    @Override
    public EntityToolbar createToolBar() {
        return page(RecordsProcessedToolbar.class);
    }

    @Override
    public SearchToolbar createSearchPanel() {
        return page(RecordsSearchBar.class);
    }
}
