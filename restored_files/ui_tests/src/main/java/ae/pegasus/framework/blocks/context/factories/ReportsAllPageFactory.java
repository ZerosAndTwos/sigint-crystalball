package ae.pegasus.framework.blocks.context.factories;

import ae.pegasus.framework.blocks.context.ContextPageFactory;
import ae.pegasus.framework.blocks.context.tables.reports.ReportsAllTable;
import ae.pegasus.framework.blocks.context.tables.EntityTable;
import ae.pegasus.framework.blocks.context.toolbars.EntityToolbar;
import ae.pegasus.framework.blocks.context.toolbars.SearchToolbar;
import ae.pegasus.framework.blocks.context.toolbars.reports.ReportsToolbar;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.page;


public class ReportsAllPageFactory implements ContextPageFactory {

    @Override
    public EntityTable createTable() {
        return Selenide.page(ReportsAllTable.class);
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
