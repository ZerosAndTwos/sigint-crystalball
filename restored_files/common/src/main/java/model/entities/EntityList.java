package model.entities;

import model.AbstractEntity;
import org.apache.commons.lang.math.RandomUtils;

import java.util.*;

public abstract class EntityList<T extends AbstractEntity> implements Iterable<T> {

    protected List<T> entities;

    public EntityList(List<T> entities) {
        this.entities = new ArrayList<>(entities);
    }

    public EntityList() {
        this.entities = new ArrayList<>();
    }

    public Integer size() {
        return entities.size();
    }

    public List<T> getEntities() {
        return Collections.unmodifiableList(entities);
    }

    public void setEntities(List<T> entities) {
        this.entities = entities;
    }

    public Iterator<T> iterator() {
        return entities.iterator();
    }

    public void addOrUpdateEntity(T entity) {
        for (T oldEntity : entities) {
            if (Objects.equals(oldEntity.getId(), entity.getId())) {
                updateEntity(oldEntity, entity);
                return;
            }
        }
        entities.add(entity);
    }

    public boolean contains(T searchEntity) {
        Boolean exist = false;
        for (T entity : entities) {
            if (Objects.equals(entity.getId(), searchEntity.getId())) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    public void updateEntity(T oldEntity, T newEntity) {
        int index = entities.indexOf(oldEntity);
        entities.set(index, newEntity);
    }

    public void removeEntity(T entity) {
        for (T exEntity : entities) {
            if (Objects.equals(exEntity.getId(), entity.getId())) {
                entities.remove(exEntity);
                return;
            }
        }
    }

    public void removeEntity(String id) {
        for (T exEntity : entities) {
            if (Objects.equals(exEntity.getId(), id)) {
                entities.remove(exEntity);
                return;
            }
        }
    }

    public T getEntity(String param) {
        for (T entity : entities) {
            if (entity.getId().equals(param)) {
                return entity;
            }
        }
        return null;
    }

    public T getLatest() {
        if (entities.size() == 0) {
            return null;
        }
        return entities.get(entities.size() - 1);
    }

    public T random() {
        if (entities.size() == 0) {
            throw new AssertionError("There are no entities in current list");
        }
        if (entities.size() > 1) {
            return entities.get(RandomUtils.nextInt(entities.size() - 1));
        }
        return entities.get(0);
    }

}

