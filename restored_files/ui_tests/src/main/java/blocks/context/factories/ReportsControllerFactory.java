package blocks.context.factories;

import controllers.PageControllerFactory;
import controllers.reports.ReportsTableController;
import controllers.reports.ReportsToolbarController;

public interface ReportsControllerFactory extends PageControllerFactory {


    @Override
    ReportsTableController getTableController();

    @Override
    ReportsToolbarController getToolbarController();
}
