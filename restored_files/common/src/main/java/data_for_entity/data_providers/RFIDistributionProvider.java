package data_for_entity.data_providers;

import model.InformationRequestDistribution;

public class RFIDistributionProvider implements EntityDataProvider {
    @Override
    public Object generate(int length) {
        return InformationRequestDistribution.random();
    }
}
