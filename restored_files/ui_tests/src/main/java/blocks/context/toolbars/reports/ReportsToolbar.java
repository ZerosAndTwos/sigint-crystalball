package blocks.context.toolbars.reports;

import blocks.context.toolbars.EntityToolbar;
import blocks.context.toolbars.TableToolbar;
import blocks.context.toolbars.reports.panels.ReportsActionPanel;

import static com.codeborne.selenide.Selenide.page;

public class ReportsToolbar extends EntityToolbar {
    @Override
    public TableToolbar getActions() {
        return page(ReportsActionPanel.class);
    }

    @Override
    public TableToolbar getFilters() {
        return null;
    }
}
