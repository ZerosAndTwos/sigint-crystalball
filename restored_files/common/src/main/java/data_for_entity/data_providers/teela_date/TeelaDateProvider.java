package data_for_entity.data_providers.teela_date;

import data_for_entity.data_providers.EntityDataProvider;
import utils.TeelaDate;

public class TeelaDateProvider implements EntityDataProvider {
    @Override
    public Object generate(int length) {
        return new TeelaDate();
    }
}
