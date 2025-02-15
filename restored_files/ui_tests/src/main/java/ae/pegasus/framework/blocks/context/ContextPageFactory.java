package ae.pegasus.framework.blocks.context;

import ae.pegasus.framework.blocks.context.tables.EntityTable;
import ae.pegasus.framework.blocks.context.toolbars.EntityToolbar;
import ae.pegasus.framework.blocks.context.toolbars.SearchToolbar;

public interface ContextPageFactory {

    EntityTable createTable();
    EntityToolbar createToolBar();
    SearchToolbar createSearchPanel();
}
