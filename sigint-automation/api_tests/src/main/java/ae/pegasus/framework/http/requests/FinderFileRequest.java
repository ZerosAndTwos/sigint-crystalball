package ae.pegasus.framework.http.requests;

import ae.pegasus.framework.http.HttpMethod;
import ae.pegasus.framework.model.FinderFile;
import ae.pegasus.framework.model.FinderFileSearchFilter;
import ae.pegasus.framework.model.SearchFilter;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class FinderFileRequest extends HttpRequest {

    private static final String URI = "/api/file-system/files";
    private static final Logger log = Logger.getLogger(FinderFileRequest.class);

    public FinderFileRequest() {
        super(URI);
    }

    public FinderFileRequest add(FinderFile finderFile) {
        this
                .setURI(URI)
                .setHttpMethod(HttpMethod.POST)
                .setPayload(finderFile);
        return this;
    }

    public FinderFileRequest get(String id) {
        this
                .setURI(URI + "/" + id)
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

    public FinderFileRequest delete(String id) {
        this
                .setURI(URI + "/" + id)
                .setHttpMethod(HttpMethod.DELETE);
        return this;
    }

    public FinderFileRequest update(FinderFile finderFile) {
        this
                .setURI(URI + "/" + finderFile.getId())
                .setHttpMethod(HttpMethod.PUT)
                .setPayload(finderFile);
        return this;
    }

    public FinderFileRequest root(SearchFilter filter) {
        String params = "/root?" +
                "hasContents=true" +
                "&parentChain=true" +
                "&query=" +
                "&page=" + filter.getPage() +
                "&pageSize=" + filter.getPageSize() +
                "&sortKey=" + filter.getSortField() +
                "&sortOrder=" + (filter.isSortDirection() ? "ASC" : "DESC");
        this
                .setURI(URI + params)
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

    public FinderFileRequest search(SearchFilter searchFilter) {
        FinderFileSearchFilter filter = (FinderFileSearchFilter) searchFilter;
        String params;
        try {
            params = "/search?" +
                    "hasContents=true" +
                    "&page=" + filter.getPage() +
                    "&pageSize=" + filter.getPageSize() +
                    (filter.getQuery().isEmpty() ? "" : "&query=" + URLEncoder.encode(filter.getQuery(), "UTF-8")) +
                    "&sortKey=" + filter.getSortField() +
                    "&sortOrder=" + (filter.isSortDirection() ? "ASC" : "DESC");
        } catch (UnsupportedEncodingException e) {
            log.error(e);
            throw new AssertionError(e);
        }
        this
                .setURI(URI + params)
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

    public FinderFileRequest cbFinderSearch(SearchFilter searchFilter) {
        FinderFileSearchFilter filter = (FinderFileSearchFilter) searchFilter;
        String params;
        try {
            params = "/search?" +
                    "page=" + filter.getPage() +
                    "&pageSize=" + filter.getPageSize() +
                    "&parentChain=true" +
                    (filter.getQuery().isEmpty() ? "" :
                            "&query=" + URLEncoder.encode(filter.getQuery(), "UTF-8"))
                            .replaceAll("\\+", "%20") +
                    "&sortKey=" + filter.getSortField() +
                    "&sortOrder=" + (filter.isSortDirection() ? "ASC" : "DESC");
        } catch (UnsupportedEncodingException e) {
            log.error(e);
            throw new AssertionError(e);
        }
        this
                .setURI("/api/fileinfo/fileinfo" + params)
                .setHttpMethod(HttpMethod.GET);
        return this;
    }

    public FinderFileRequest getContent(String fileID) {
        this
                .setURI(URI + "/" + fileID + "/contents")
                .setHttpMethod(HttpMethod.GET);
        return this;
    }
}
