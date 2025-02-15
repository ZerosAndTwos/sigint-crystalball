package blocks.context.factories;

import blocks.context.ContextPageFactory;
import blocks.context.tables.EntityTable;
import blocks.context.tables.records.RecordsProcessedTable;
import blocks.context.toolbars.EntityToolbar;
import blocks.context.toolbars.SearchToolbar;
import blocks.context.toolbars.records.RecordsProcessedToolbar;
import blocks.context.toolbars.records.RecordsSearchBar;

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
