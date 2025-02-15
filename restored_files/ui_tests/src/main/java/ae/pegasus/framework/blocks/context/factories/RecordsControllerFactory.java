package ae.pegasus.framework.blocks.context.factories;

import ae.pegasus.framework.controllers.PageControllerFactory;
import ae.pegasus.framework.controllers.records.RecordsTableController;
import ae.pegasus.framework.controllers.records.RecordsToolbarController;
import ae.pegasus.framework.controllers.PageController;

public interface  RecordsControllerFactory extends PageControllerFactory {

    @Override
    RecordsTableController getTableController();

    @Override
    RecordsToolbarController getToolbarController();

    @Override
    PageController getSearchController();
}
