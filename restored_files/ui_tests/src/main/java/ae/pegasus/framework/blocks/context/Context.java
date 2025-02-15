package ae.pegasus.framework.blocks.context;

import ae.pegasus.framework.pages.BaseSection;
import ae.pegasus.framework.blocks.context.tables.EntityTable;
import ae.pegasus.framework.blocks.context.toolbars.EntityToolbar;
import ae.pegasus.framework.blocks.context.toolbars.SearchToolbar;

import static com.codeborne.selenide.Selenide.page;

public abstract class Context extends BaseSection {

    public static final String baseSelector = "div.pg-table";

    public ContextPageFactory getPageFactory() {
        return pageFactory;
    }

    public void setPageFactory(ContextPageFactory pageFactory) {
        this.pageFactory = pageFactory;
    }

    protected ContextPageFactory pageFactory;

    public Context(ContextPageFactory factory) {
        super(baseSelector);
        this.pageFactory = factory;
    }

    public EntityToolbar getToolbar() {return page(pageFactory.createToolBar());}

    public SearchToolbar getSearchPanel() {return page(pageFactory.createSearchPanel());}

    public EntityTable getTable() {return page(pageFactory.createTable());}

}
