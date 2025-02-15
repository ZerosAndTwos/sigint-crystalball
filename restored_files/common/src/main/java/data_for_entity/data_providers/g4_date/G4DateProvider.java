package data_for_entity.data_providers.g4_date;

import data_for_entity.data_providers.EntityDataProvider;
import utils.G4Date;

public class G4DateProvider implements EntityDataProvider {
    @Override
    public Object generate(int length) {
        return new G4Date();
    }
}
