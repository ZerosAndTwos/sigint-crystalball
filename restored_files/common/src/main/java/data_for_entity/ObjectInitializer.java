package data_for_entity;

import data_for_entity.data_providers.DependencyData;
import data_for_entity.data_providers.DependencyDataProvider;
import data_for_entity.data_providers.EntityDataProvider;
import data_for_entity.instance_managers.DefaultInstanceManager;
import data_for_entity.instance_managers.InstanceManager;
import error_reporter.ErrorReporter;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.List;
import java.util.Map;


class ObjectInitializer {
    
    private Logger logger = Logger.getLogger(ObjectInitializer.class);
    private TypeManager typeManager = new TypeManager();
    private InstanceManager instanceManager = new DefaultInstanceManager();
    
    void setInstanceManager(InstanceManager manager) {
        this.instanceManager = manager;
    }
    
    
    
    Object generateObjectFields(Class<?> objectType) {
        typeManager.setObjectType(objectType);
        Object instance = instanceManager.createInstance(objectType);
        logger.debug("Searching for required fields");
        List<ObjectField> requiredFields = typeManager.createFieldsFilter().filterByRequired(typeManager.getAllFields());
        if (requiredFields == null) {
            logger.debug("There are no required fields for entity: " + instance +
                    "Instance will be returned as it is");
            return instance;
        }
        TasksExecutor executor = new TasksExecutor();
        for (ObjectField objectField:requiredFields) {
            FieldPopulator fieldPopulator = new FieldPopulator(objectField, instance);
            executor.submitTask(fieldPopulator);
            
        }
        Boolean tasksCompleted = executor.waitForCompletion();
        if (!tasksCompleted) {
            ErrorReporter.raiseError("Not all fields are generated! View debug logs for details");
        }
        logger.info("Object is created with filled fields");
        return instance;
    }
    
    
    private class FieldPopulator implements Runnable {
        
        private final ObjectField field;
        private Object instance;
        private DataOptionsManager fieldOptions;
        
        FieldPopulator(ObjectField objectField, Object instance) {
            this.field = objectField;
            this.instance = instance;
            this.fieldOptions = new DataOptionsManager(objectField.getField());
        }
    
        private  DependencyData createDependencyData(List<ObjectField> dependencyFields, Object instance) {
            DependencyData dependencyData = new DependencyData();
            for(ObjectField field: dependencyFields) {
                dependencyData.insertData(field.getName(), field.getValue(instance));
            }
            return dependencyData;
        }
        
        
        
        
        @Override
        public void run() {
            logger.debug("Generating value for required field:" + field.getName());
            synchronized (field) {
                if (field.getValue(instance) != null) {
                    logger.debug("Field: " + field.getName() + " already has value. Skipping...");
                    return;
                }
                //if field has dependencies, first those fields will initialized with values
                if (fieldOptions.getDependencies()!=null) {
                    List<ObjectField> dependenceFields = typeManager.createFieldsFilter().filterByDependend(field);
                    if (dependenceFields == null) {
                        logger.debug("Cannot fill in dependencies as fields are not found!");
                        logger.debug(String.format("Field %s is skipped", field.getName()));
                        return;
                    }
                    for (ObjectField field : dependenceFields) {
                        new FieldPopulator(field, instance).run();
                        
                    }
                    
                }
                Class<?> classType = field.getField().getType();
                FieldData dataGenerator;
                if (Helpers.isCollection(classType)) {
                    dataGenerator = new CollectionFieldData();
                } else if (Helpers.isMap(classType)) {
                    dataGenerator = new MapFieldData();
                } else  {
                    dataGenerator = new FieldData();
                }
                
                Object value = dataGenerator.generateData();
                field.setValue(instance, value);
    
                }
            }
        
        
        private class FieldData {
            
            Object generateSingleData(Class<?> classType) {
                EntityDataProvider provider;
                if (fieldOptions.getDependencies()!=null) {
                    DependencyDataProvider dependencyProvider = fieldOptions.getDependencies().getProvider();
                    List<ObjectField> dependencyFields = typeManager.createFieldsFilter().filterByDependend(field);
                    dependencyProvider.setDependencyData(createDependencyData(dependencyFields, instance));
                    provider = dependencyProvider;
                } else {
                    provider = fieldOptions.getDataProvider();
                }
                Object value;
                int dataLength = fieldOptions.getDataSize();
                if (provider != null) {
                        value = provider.generate(dataLength);
                    } else {
                        value = generateObjectFields(classType);
                    }
                
                return value;
            }
            
            Object generateData() {
                return generateSingleData(field.getField().getType());
            }
            
        }
        
        private class CollectionFieldData extends FieldData {
    
            @Override
            Object generateData() {
                Collection collection;
                Class<?> objectClass = field.getField().getType();
                try {
                    
                    collection = (Collection) instanceManager.createInstance(objectClass);
                } catch (Error er) {
                    logger.debug("Error occurred when creating instance of a collection. Might collection" +
                            " is abstract or is an interface. Examine logs for further details." +
                            "You must declare exact implementation class as a type!");
                    return null;
                }
                int collectionSize = fieldOptions.getCollectionSize();
                Class<?> collectionType = Helpers.getCollectionType(field.getField());
                for (int i=1; i <= collectionSize; i++) {
                    collection.add(super.generateSingleData(collectionType));
                }
                return objectClass.cast(collection);
            }
        }
        
        private class MapFieldData extends FieldData {
            
            @Override
            Object generateData() {
                Map map;
                Class<?> objectClass = field.getField().getType();
                try {
                    map = (Map) instanceManager.createInstance(objectClass);
                } catch (Error er) {
                    logger.debug("Error occurred during creation of Map instance");
                    return null;
                }
                int collectionSize = fieldOptions.getCollectionSize();
                Class<?>[] typeClasses = Helpers.getMapTypes(field.getField());
                for (int i=1;i<=collectionSize;i++) {
                    Object key = super.generateSingleData(typeClasses[0]);
                    Object value = super.generateSingleData(typeClasses[1]);
                    map.put(key, value);
                }
                return objectClass.cast(map);
                        
            }
            
        }
    }
    
        
    
}
