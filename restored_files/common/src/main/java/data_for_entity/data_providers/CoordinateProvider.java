package data_for_entity.data_providers;

public class CoordinateProvider implements EntityDataProvider {
    @Override
    public Object generate(int length) {
        return Math.random() * 360 - 180;
    }
}
