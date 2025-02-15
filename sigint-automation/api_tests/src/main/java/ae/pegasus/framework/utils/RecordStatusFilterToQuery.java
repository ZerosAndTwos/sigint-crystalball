package ae.pegasus.framework.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

enum RecordStatusFilterToQuery {

    unassigned("workflow.ownerId:$MISSING"),

    unprocessed("report.reportIds:$MISSING AND NOT workflow.importance:-1"),
    unprocessed_unassigned("report.reportIds:$MISSING AND NOT workflow.importance:-1 AND workflow.ownerId:$MISSING"),

    reported("report.reportIds:$EXISTS"),
    reported_unassigned("report.reportIds:$EXISTS AND workflow.ownerId:$MISSING"),

    unimportant("workflow.importance:-1"),
    unimportant_unassigned("workflow.importance:-1 AND workflow.ownerId:$MISSING");

    private final String query;

    RecordStatusFilterToQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    private static final List<RecordStatusFilterToQuery> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static Random RANDOM = new Random();

    public static RecordStatusFilterToQuery random() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}
