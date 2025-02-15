package ae.pegasus.framework.utils;

import ae.pegasus.framework.model.TimeRange;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SearchQueryBuilder {

    public static String recordStatusToQuery(String recordStatus) {
        String query;
        switch (recordStatus.toLowerCase()) {
            case "unassigned":
                query = RecordStatusFilterToQuery.unassigned.getQuery();
                break;
            case "unprocessed":
                query = RecordStatusFilterToQuery.unprocessed.getQuery();
                break;
            case "unprocessed unassigned":
                query = RecordStatusFilterToQuery.unprocessed_unassigned.getQuery();
                break;
            case "reported":
                query = RecordStatusFilterToQuery.reported.getQuery();
                break;
            case "reported unassigned":
                query = RecordStatusFilterToQuery.reported_unassigned.getQuery();
                break;
            case "unimportant":
                query = RecordStatusFilterToQuery.unimportant.getQuery();
                break;
            case "unimportant unassigned":
                query = RecordStatusFilterToQuery.unimportant_unassigned.getQuery();
                break;
            default:
                throw new AssertionError("Unknown recordStatus filter value: " + recordStatus);
        }
        return query + " AND eventTime:[$NOW-90d..$NOW]";
    }

    public static String timeRangeToQuery(TimeRange timeRange) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return String.format("eventTime:[\"%s\"..\"%s\"]",
                dateFormat.format(timeRange.getStartDate()), dateFormat.format(timeRange.getEndDate()));
    }
}
