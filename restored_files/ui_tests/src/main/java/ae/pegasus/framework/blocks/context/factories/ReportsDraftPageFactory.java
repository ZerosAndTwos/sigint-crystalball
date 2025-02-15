package ae.pegasus.framework.blocks.context.factories;

import ae.pegasus.framework.blocks.context.ContextPageFactory;
import ae.pegasus.framework.blocks.context.tables.EntityTable;
import ae.pegasus.framework.blocks.context.tables.reports.ReportsDraftTable;
import ae.pegasus.framework.blocks.context.toolbars.EntityToolbar;
import ae.pegasus.framework.blocks.context.toolbars.SearchToolbar;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.page;

public class ReportsDraftPageFactory implements ContextPageFactory {

    @Override
    public EntityTable createTable() {
        return Selenide.page(ReportsDraftTable.class);
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
