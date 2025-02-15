package data_for_entity.data_providers;

import utils.TeelaDate;

public class TeelaDateProvider implements EntityDataProvider {
    @Override
    public Object generate(int length) {
        return new TeelaDate();
    }
}
