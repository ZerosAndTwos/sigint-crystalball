package ae.pegasus.framework.controllers.reports;

import ae.pegasus.framework.blocks.context.toolbars.reports.panels.ReportsActionPanel;
import ae.pegasus.framework.controllers.ToolbarController;
import ae.pegasus.framework.pages.SigintPage;

public class ReportsToolbarController extends ToolbarController {


    private ReportsActionPanel actionPanel;

    public ReportsToolbarController(SigintPage page) {
        super(page);
    }

    @Override
    protected void initFilters() {

    }
    @Override
    protected void initActions() {
        actionPanel = (ReportsActionPanel)getPage().context().getToolbar().getActions();
    }

    public void openCreateReportForm() {
        actionPanel.clickCreateManualReportBtn();
        
    }

}
