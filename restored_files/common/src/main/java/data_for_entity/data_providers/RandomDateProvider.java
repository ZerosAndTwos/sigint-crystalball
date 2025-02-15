package data_for_entity.data_providers;

import utils.RandomGenerator;

import java.util.Date;

public class RandomDateProvider implements EntityDataProvider {

    @Override
    public Date generate(int length) {
        try {
            return RandomGenerator.randomMonthRangeDate();
        } catch (Exception e) {
            log.error(e);
            throw e;
        }
    }
}
