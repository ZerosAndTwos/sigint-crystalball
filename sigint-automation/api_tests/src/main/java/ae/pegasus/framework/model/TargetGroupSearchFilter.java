package ae.pegasus.framework.model;

/**
 * used in Profiler API:
 * GET /targetGroups/root
 */
public class TargetGroupSearchFilter extends SearchFilter<TargetGroup> {

    private String query;

    @Override
    public boolean isAppliedToEntity(TargetGroup entity) {
        return false;
    }

    public String getQuery() {
        return query;
    }

    public TargetGroupSearchFilter setQuery(String query) {
        this.query = query;
        return this;
    }
}
