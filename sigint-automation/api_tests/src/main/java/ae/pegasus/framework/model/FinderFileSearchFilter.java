package ae.pegasus.framework.model;

/**
 * used in CB Finder API:
 * GET /api/file-system/files/root
 */
public class FinderFileSearchFilter extends SearchFilter<FinderFile> {

    private String query;

    @Override
    public boolean isAppliedToEntity(FinderFile entity) {
        return false;
    }

    public String getQuery() {
        return query;
    }

    public FinderFileSearchFilter setQuery(String query) {
        this.query = query;
        return this;
    }
}
