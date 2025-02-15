package ae.pegasus.framework.blocks.context.toolbars.reports;

import ae.pegasus.framework.blocks.context.toolbars.EntityToolbar;
import ae.pegasus.framework.blocks.context.toolbars.reports.panels.ReportsActionPanel;
import ae.pegasus.framework.blocks.context.toolbars.TableToolbar;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.page;

public class ReportsToolbar extends EntityToolbar {
    @Override
    public TableToolbar getActions() {
        return Selenide.page(ReportsActionPanel.class);
    }

    @Override
    public TableToolbar getFilters() {
        return null;
    }
}
