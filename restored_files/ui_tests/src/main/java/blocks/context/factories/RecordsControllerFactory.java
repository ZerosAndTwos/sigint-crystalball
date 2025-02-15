package blocks.context.factories;

import controllers.PageController;
import controllers.PageControllerFactory;
import controllers.records.RecordsTableController;
import controllers.records.RecordsToolbarController;

public interface  RecordsControllerFactory extends PageControllerFactory {

    @Override
    RecordsTableController getTableController();

    @Override
    RecordsToolbarController getToolbarController();

    @Override
    PageController getSearchController();
}
