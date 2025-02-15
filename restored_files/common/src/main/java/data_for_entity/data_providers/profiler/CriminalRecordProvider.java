package data_for_entity.data_providers.profiler;

import data_for_entity.data_providers.EntityDataProvider;
import model.CriminalRecord;

public class CriminalRecordProvider implements EntityDataProvider {

    @Override
    public CriminalRecord generate(int length) {
        return CriminalRecord.random();
    }
}
