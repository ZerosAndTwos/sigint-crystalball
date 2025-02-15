package ae.pegasus.framework.pages.records;

import ae.pegasus.framework.pages.ContextPage;
import ae.pegasus.framework.pages.SigintPage;
import ae.pegasus.framework.blocks.context.Context;
import ae.pegasus.framework.blocks.context.factories.RecordAllPageFactory;

public class RecordsAllPage extends SigintPage implements ContextPage {

    private Context context;

    public Context context() {
        if (context == null) {
            context = new RecordsAllContext();
        }
        return context;
    }

    private class RecordsAllContext extends Context {
        RecordsAllContext() {
            super(new RecordAllPageFactory());
        }
    }

}
