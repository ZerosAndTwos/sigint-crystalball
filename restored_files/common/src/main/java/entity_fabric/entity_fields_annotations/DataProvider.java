package entity_fabric.entity_fields_annotations;

import entity_fabric.data_providers.EntityDataProvider;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DataProvider {
    
    Class<? extends EntityDataProvider>  providerClass();
    int length() default 10;
}
