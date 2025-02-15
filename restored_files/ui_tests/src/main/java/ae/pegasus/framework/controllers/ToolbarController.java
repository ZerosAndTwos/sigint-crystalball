package ae.pegasus.framework.controllers;

import ae.pegasus.framework.pages.SigintPage;

public abstract class ToolbarController extends PageController {

    public ToolbarController(SigintPage page) {
        super(page);
        initActions();
        initFilters();

    }
    protected abstract void initFilters();
    protected abstract void initActions();

}
