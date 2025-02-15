package ae.pegasus.framework.pages.reports;

import ae.pegasus.framework.blocks.context.Context;
import ae.pegasus.framework.pages.SigintPage;
import ae.pegasus.framework.blocks.context.factories.ReportsDraftPageFactory;
import ae.pegasus.framework.pages.ContextPage;

public class ReportsDraftPage extends SigintPage implements ContextPage {

    private Context context;

    @Override
    public Context context() {
        if (context == null) {
            context = new ReportsDraftContext();
        }
        return context;
    }

    private class ReportsDraftContext extends Context {
        ReportsDraftContext() {
            super(new ReportsDraftPageFactory());
        }
    }
}
