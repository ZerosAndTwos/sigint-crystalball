package ae.pegasus.framework.pages.reports;

import ae.pegasus.framework.blocks.context.Context;
import ae.pegasus.framework.blocks.context.factories.ReportsAllPageFactory;
import ae.pegasus.framework.pages.SigintPage;
import ae.pegasus.framework.pages.ContextPage;

public class ReportsAllPage extends SigintPage implements ContextPage {

    private Context context;


    @Override
    public Context context() {
        if (context == null) {
            context = new ReportsAllContext();
        }
        return context;
    }


    private class ReportsAllContext extends Context {
        public ReportsAllContext() {
            super(new ReportsAllPageFactory());
        }
    }
}
