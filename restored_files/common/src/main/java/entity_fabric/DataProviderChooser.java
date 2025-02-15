package entity_fabric;

import entity_fabric.data_providers.EntityDataProvider;
import entity_fabric.data_providers.IntegerDataProvider;
import entity_fabric.data_providers.StringDataProvider;
import entity_fabric.data_providers.UserDefinedDataProvider;

class DataProviderChooser {
    
    static EntityDataProvider getDataProviderBasedFieldType(Class<?> classType) {
        if (classType.equals(Integer.class)) {
            return new IntegerDataProvider();
        } else if (classType.equals(String.class)) {
            return new StringDataProvider();
        } else {
            return new UserDefinedDataProvider(classType);
        }
    }
}
