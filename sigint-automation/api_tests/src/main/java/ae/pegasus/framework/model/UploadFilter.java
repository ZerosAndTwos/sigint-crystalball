package ae.pegasus.framework.model;

import java.util.Date;

public class UploadFilter extends SearchFilter<Process> {

    private Date minCreatedDate;
    private Date maxCreatedDate;
    private boolean aggregate;

    public Date getMinCreatedDate() {
        return minCreatedDate;
    }

    public UploadFilter setMinCreatedDate(Date minCreatedDate) {
        this.minCreatedDate = minCreatedDate;
        return this;
    }

    public Date getMaxCreatedDate() {
        return maxCreatedDate;
    }

    public UploadFilter setMaxCreatedDate(Date maxCreatedDate) {
        this.maxCreatedDate = maxCreatedDate;
        return this;
    }

    public boolean isAggregate() {
        return aggregate;
    }

    public UploadFilter setAggregate(boolean aggregate) {
        this.aggregate = aggregate;
        return this;
    }

    @Override
    public boolean isAppliedToEntity(Process entity) {
        return false;
    }
}
