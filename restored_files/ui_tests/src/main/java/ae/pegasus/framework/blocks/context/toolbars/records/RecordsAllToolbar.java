package ae.pegasus.framework.blocks.context.toolbars.records;

import ae.pegasus.framework.blocks.context.toolbars.EntityToolbar;
import ae.pegasus.framework.blocks.context.toolbars.records.panels.RecordsActionPanel;
import ae.pegasus.framework.blocks.context.toolbars.records.panels.RecordsFilterPanel;

import static com.codeborne.selenide.Selenide.page;

public class RecordsAllToolbar extends EntityToolbar {

    @Override
    public RecordsActionPanel getActions() {
        return page(RecordsActionPanel.class);
    }

    @Override
    public RecordsFilterPanel getFilters() {
        return page(RecordsFilterPanel.class);
    }
}
