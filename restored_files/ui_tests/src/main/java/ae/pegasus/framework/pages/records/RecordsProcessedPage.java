package ae.pegasus.framework.pages.records;

import ae.pegasus.framework.blocks.context.Context;
import ae.pegasus.framework.blocks.context.factories.RecordProcessedPageFactory;
import ae.pegasus.framework.pages.SigintPage;
import ae.pegasus.framework.pages.ContextPage;

public class RecordsProcessedPage extends SigintPage implements ContextPage{

    private Context context;

    @Override
    public Context context() {
        if (context == null) {
            context = new RecordsProcessedContext();
        }
        return context;
    }

    private class RecordsProcessedContext extends Context {
        RecordsProcessedContext() {
            super(new RecordProcessedPageFactory());
        }
    }
}
