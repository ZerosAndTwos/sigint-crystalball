package data_for_entity.data_providers.rfi;

import data_for_entity.data_providers.EntityDataProvider;
import model.InformationRequestDistribution;

public class RFIDistributionProvider implements EntityDataProvider {
    @Override
    public Object generate(int length) {
        return InformationRequestDistribution.random();
    }
}
