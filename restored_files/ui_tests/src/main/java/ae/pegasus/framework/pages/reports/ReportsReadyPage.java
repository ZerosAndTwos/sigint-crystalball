package ae.pegasus.framework.pages.reports;

import ae.pegasus.framework.pages.SigintPage;
import ae.pegasus.framework.blocks.context.Context;
import ae.pegasus.framework.blocks.context.factories.ReportsReadyPageFactory;
import ae.pegasus.framework.pages.ContextPage;

public class ReportsReadyPage extends SigintPage implements ContextPage {

    private Context context;

    @Override
    public Context context() {
        if (context == null) {
            context = new ReportsReadyContext();
        }
        return context;
    }

    private class ReportsReadyContext extends Context {
        public ReportsReadyContext() {
            super(new ReportsReadyPageFactory());
        }
    }
}
