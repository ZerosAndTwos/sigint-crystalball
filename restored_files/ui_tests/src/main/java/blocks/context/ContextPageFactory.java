package blocks.context;

import blocks.context.tables.EntityTable;
import blocks.context.toolbars.EntityToolbar;
import blocks.context.toolbars.SearchToolbar;

public interface ContextPageFactory {

    EntityTable createTable();
    EntityToolbar createToolBar();
    SearchToolbar createSearchPanel();
}
