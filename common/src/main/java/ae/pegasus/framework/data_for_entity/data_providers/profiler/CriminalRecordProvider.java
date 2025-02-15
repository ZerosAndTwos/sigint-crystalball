package ae.pegasus.framework.data_for_entity.data_providers.profiler;

import ae.pegasus.framework.data_for_entity.data_providers.EntityDataProvider;
import ae.pegasus.framework.model.CriminalRecord;

public class CriminalRecordProvider implements EntityDataProvider {

    @Override
    public CriminalRecord generate(int length) {
        return CriminalRecord.random();
    }
}
