package entity_fabric.data_providers;

public interface EntityDataProvider {
    
    int dataLength = 10;
    
    Object generate(int length);
}
