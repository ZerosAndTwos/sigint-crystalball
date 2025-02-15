package ae.pegasus.framework.controllers.records;

import ae.pegasus.framework.app_context.RunContext;
import ae.pegasus.framework.blocks.context.toolbars.records.panels.RecordsActionPanel;
import ae.pegasus.framework.blocks.context.toolbars.records.panels.RecordsFilterPanel;
import ae.pegasus.framework.controllers.ToolbarController;
import ae.pegasus.framework.controllers.reports.form_page.ReportFormFactoryController;
import ae.pegasus.framework.pages.SigintPage;

public class RecordsToolbarController<F extends RecordsFilterPanel, A extends RecordsActionPanel>

extends ToolbarController {

    private F filterPanel;
    private A actionPanel;

    public RecordsToolbarController(SigintPage page) {
        super(page);
    }

    @Override
    protected void initFilters() {
        filterPanel = (F)this.getPage().context().getToolbar().getFilters();

    }

    @Override
    protected void initActions() {
        actionPanel = (A)this.getPage().context().getToolbar().getActions();

    }

    public void searchByDate(String date) {
        filterPanel.setDate(date);
        filterPanel.applySearch();
    }

    public void openCreateRecordForm() {
        actionPanel.clickCreateRecord();
        RunContext.get().put("controller", new RecordAddController());
    }

    public void openCreateReportForm() {
        actionPanel.clickCreateReport();
        RunContext.get().put("controller", new ReportFormFactoryController());
    }
}
