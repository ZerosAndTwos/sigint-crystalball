package ae.pegasus.framework.blocks.context.toolbars;

import ae.pegasus.framework.blocks.context.Context;
import ae.pegasus.framework.pages.BaseSection;

public abstract class TableToolbar extends BaseSection {
    private static final String toolBar = Context.baseSelector+ " div.pg-toolbar";

    public TableToolbar() {
        super(toolBar);
    }
}
