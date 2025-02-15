package model;

import data_for_entity.annotations.DataIgnore;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Objects;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public abstract class AbstractEntity implements Comparable {

    @DataIgnore
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public <T extends AbstractEntity> Boolean equals(T object) {

        for (Field field : object.getClass().getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            String originalValue;
            String requestValue;
            try {
                originalValue = BeanUtils.getProperty(this, field.getName());
                requestValue = BeanUtils.getProperty(object, field.getName());
            } catch (Exception e) {
                throw new AssertionError(e.getMessage(), e);
            }
            if (originalValue == null && requestValue == null) {
                continue;
            }
            if (!Objects.equals(originalValue, requestValue)) {
                return FALSE;
            }
        }
        return TRUE;
    }

    @Override
    public int compareTo(Object obj) {
        AbstractEntity compareWith = (AbstractEntity) obj;
        return this.getId().compareTo(compareWith.getId());
    }
}
