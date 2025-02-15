package ae.pegasus.framework.model;

import ae.pegasus.framework.data_for_entity.annotations.DataIgnore;
import ae.pegasus.framework.data_for_entity.annotations.WithFieldDataType;
import ae.pegasus.framework.data_for_entity.data_types.FieldDataType;
import ae.pegasus.framework.model.information_managment.ModifiedBy;

import java.util.Date;

public class BaseEntity extends AbstractEntity {

    @WithFieldDataType(FieldDataType.DATE)
    private Date createdAt;
    @DataIgnore
    private ModifiedBy createdBy;
    @DataIgnore
    private Date modifiedAt;
    @DataIgnore
    private ModifiedBy modifiedBy;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public ModifiedBy getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(ModifiedBy createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public ModifiedBy getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(ModifiedBy modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
