package data_for_entity.data_providers;

public class StaticData implements EntityDataProvider {
    
    private Object value;
    
    public StaticData(Object value) {
        this.value = value;
    }
    
    @Override
    public Object generate(int length) {
        return value;
    }
}
