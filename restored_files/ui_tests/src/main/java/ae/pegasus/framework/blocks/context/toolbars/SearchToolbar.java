package ae.pegasus.framework.blocks.context.toolbars;

import ae.pegasus.framework.blocks.context.Context;
import ae.pegasus.framework.pages.BaseSection;

public abstract class SearchToolbar extends BaseSection {
    private static final String toolBar = Context.baseSelector+ " search-form";
    public SearchToolbar() {
        super(toolBar);
    }
}
