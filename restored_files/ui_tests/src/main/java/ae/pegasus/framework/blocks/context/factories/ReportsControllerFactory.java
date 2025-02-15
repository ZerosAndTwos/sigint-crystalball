package ae.pegasus.framework.blocks.context.factories;

import ae.pegasus.framework.controllers.PageControllerFactory;
import ae.pegasus.framework.controllers.reports.ReportsTableController;
import ae.pegasus.framework.controllers.reports.ReportsToolbarController;

public interface ReportsControllerFactory extends PageControllerFactory {


    @Override
    ReportsTableController getTableController();

    @Override
    ReportsToolbarController getToolbarController();
}
