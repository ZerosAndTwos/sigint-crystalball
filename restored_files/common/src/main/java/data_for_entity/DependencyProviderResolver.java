package data_for_entity;

import data_for_entity.data_providers.DependencyData;
import data_for_entity.data_providers.DependencyDataProvider;
import data_for_entity.data_providers.EntityDataProvider;
import data_for_entity.provider_resolver.ProviderResolver;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

class DependencyProviderResolver extends ProviderResolver {

    private Object instance;
    private Field field;
    private Map<String, Task> taskMap;

    DependencyProviderResolver(Field field, Object instance, Map<String, Task> taskMap) {
        this.field = field;
        this.instance = instance;
        this.taskMap = taskMap;
    }

    private DataDependencies readDataDependency() {
        FieldOptionsManager optionsManager = new FieldOptionsManager(field);
        return optionsManager.getDependencies();
    }

    /**
     * Helps to create {@link DependencyData} object.
     *
     * @param dependencyFields List of fields that current field is depend on.
     * @return {@link DependencyData} instance.
     */
    private DependencyData createDependencyData(List<ObjectField> dependencyFields) {
        DependencyData dependencyData = new DependencyData();
        for (ObjectField field : dependencyFields) {
            taskMap.get(field.getName()).getResult();
            dependencyData.insertData(field.getName(), field.getValue(instance));
        }
        return dependencyData;
    }


    @Override
    protected EntityDataProvider getInternalProvider() {
        DataDependencies dataDependencies = readDataDependency();
        if (dataDependencies == null) {
            return null;
        }
        FieldsCollection fieldsCollection = new FieldsCollection();
        fieldsCollection.collectFieldsByType(instance.getClass());
        List<ObjectField> dependencyFields =
                fieldsCollection.createFieldsFilter().filterByDependentFields(dataDependencies.getFields());
        DependencyDataProvider provider = dataDependencies.getProvider();
        provider.setDependencyData(createDependencyData(dependencyFields));
        return provider;
    }
}
