package entity_fabric;

import entity_fabric.data_providers.EntityDataProvider;

class FieldDataGenerator {
    
    private EntityDataProvider dataProvider;
    private int dataLength;
    
    FieldDataGenerator(EntityDataProvider dataProvider, int dataLength) {
        this.dataProvider = dataProvider;
        this.dataLength = dataLength;
    }
    
    public Object generateData() {
        return dataProvider.generate(dataLength);
    }
}
