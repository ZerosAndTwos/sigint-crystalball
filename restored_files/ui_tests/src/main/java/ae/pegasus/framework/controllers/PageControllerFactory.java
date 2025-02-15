package ae.pegasus.framework.controllers;

import ae.pegasus.framework.pages.SigintPage;

public interface PageControllerFactory{

    SigintPage getPage();

    TableController getTableController();

    ToolbarController getToolbarController();

    PageController getSearchController();

}


