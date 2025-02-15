package ae.pegasus.framework.model;

import ae.pegasus.framework.utils.DateHelper;

import java.util.Date;

/**
 * old G4 API
 * for Profiler API see {@link TargetGroupSearchFilter}
 */
public class TargetGroupFilter extends SearchFilter<TargetGroup> {

    private boolean includeDeleted;
    private Date updatedAfter = DateHelper.yesterday();

    public Date getUpdatedAfter() {
        return updatedAfter;
    }

    public void setUpdatedAfter(Date updatedAfter) {
        this.updatedAfter = updatedAfter;
    }

    public boolean getIncludeDeleted() {
        return includeDeleted;
    }

    public void setIncludeDeleted(boolean includeDeleted) {
        this.includeDeleted = includeDeleted;
    }


    @Override
    public boolean isAppliedToEntity(TargetGroup entity) {
        return activeFilter.isAppliedToEntity(entity);
    }

    private class DeletedFilter extends SearchFilter<TargetGroup> {

        DeletedFilter(Boolean value) {
            includeDeleted = value;
        }

        @Override
        public boolean isAppliedToEntity(TargetGroup entity) {
            return entity.isDeleted() == includeDeleted;
        }
    }

    private class EmptyFilter extends SearchFilter<TargetGroup> {

        EmptyFilter() {
            updatedAfter = null;
        }

        @Override
        public boolean isAppliedToEntity(TargetGroup entity) {
            return true;
        }
    }

    /**
     * Init or update targetGroup filter.
     * Filter is used in TargetGroupService for receive list of targetGroups.
     *
     * @param criteria field filter
     * @param value value of filter
     * @return filter entity for targetGroups
     */
    public TargetGroupFilter filterBy(String criteria, String value) {
        switch (criteria.toLowerCase()) {
            case "includedeleted":
                this.setActiveFilter(this.new DeletedFilter(Boolean.valueOf(value)));
                break;
            case "empty":
                this.setActiveFilter(this.new EmptyFilter());
                break;
            default:
                throw new AssertionError("Unknown isAppliedToEntity type");
        }
        return this;
    }

}
