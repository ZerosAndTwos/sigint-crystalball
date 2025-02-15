package ae.pegasus.framework.blocks.context.toolbars.records;

import ae.pegasus.framework.blocks.context.toolbars.records.panels.RecordsProcessedActionPanel;
import ae.pegasus.framework.blocks.context.toolbars.EntityToolbar;
import ae.pegasus.framework.blocks.context.toolbars.records.panels.RecordsFilterPanel;

import static com.codeborne.selenide.Selenide.page;

public class RecordsProcessedToolbar extends EntityToolbar {

    @Override
    public RecordsProcessedActionPanel getActions() {
        return page(RecordsProcessedActionPanel.class);
    }

    @Override
    public RecordsFilterPanel getFilters() {
        return page(RecordsFilterPanel.class);
    }
}
