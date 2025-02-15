package ae.pegasus.framework.model.entities;

import ae.pegasus.framework.model.User;

import java.util.Objects;


class UsersList extends EntityList<User> {

    @Override
    public void addOrUpdateEntity(User entity) {
        for (User oldEntity : entities) {
            if (Objects.equals(oldEntity.getName(), entity.getName())) {
                // keep user roles if necessary
                if (!oldEntity.getRoles().isEmpty() && entity.getRoles().isEmpty()) {
                    entity.setRoles(oldEntity.getRoles());
                }
                // keep user password if necessary
                if (oldEntity.getPassword() != null && entity.getPassword() == null) {
                    entity.setPassword(oldEntity.getPassword());
                }
                updateEntity(oldEntity, entity);
                return;
            }
        }
        entities.add(entity);
    }
}
