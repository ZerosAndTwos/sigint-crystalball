package ae.pegasus.framework.steps;

import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.http.OperationsResults;
import ae.pegasus.framework.json.JsonConverter;
import ae.pegasus.framework.model.*;
import ae.pegasus.framework.services.SQMService;
import ae.pegasus.framework.services.SearchService;
import ae.pegasus.framework.utils.DateHelper;
import ae.pegasus.framework.utils.StringUtils;
import ae.pegasus.framework.verification.SearchFiltersVerification;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.junit.Assert;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static ae.pegasus.framework.error_reporter.ErrorReporter.raiseError;
import static ae.pegasus.framework.json.JsonConverter.jsonToObject;
import static ae.pegasus.framework.json.JsonConverter.toJsonString;
import static ae.pegasus.framework.utils.DateHelper.*;
import static ae.pegasus.framework.utils.RandomGenerator.getRandomItemFromList;
import static ae.pegasus.framework.utils.SearchQueryBuilder.recordStatusToQuery;
import static ae.pegasus.framework.utils.SearchQueryBuilder.timeRangeToQuery;
import static ae.pegasus.framework.utils.StepHelper.compareByCriteria;
import static ae.pegasus.framework.utils.StringUtils.extractStringsInQuotes;
import static org.apache.commons.lang3.StringUtils.getLevenshteinDistance;
import static org.junit.Assert.*;

@SuppressWarnings("unchecked")
public class APISearchSteps extends APISteps {

    private static SearchService service = new SearchService();
    private static SQMService sqmService = new SQMService();

    private static final String processIdQuery = "pid:";

    /**
     * Example:
     * g?t: matches "git", "get", "got", etc., but doesn't match "gate", "great", etc
     * g*t: matches "git", "get', "got", "great", etc., but doesn't match "guilty", "gets", etc
     * good\?: matches "good?", but not "goods", since ? is escaped and taken literally
     * red\*: matched "red*", but not "reddish" for the same reason
     **/
    private String cbSearchQueryToRegex(String query) {
        StringBuilder sb = new StringBuilder().append(".*");

        char[] chars = query.toCharArray();

        for (int i = 0; i < query.length(); i++) {
            sb.append(chars[i]);
            if ((chars[i] == '?' || chars[i] == '*') && (i == 0 || chars[i - 1] != '\\')) {
                switch (chars[i]) {
                    case '?':
                        sb.replace(sb.length() - 1, sb.length(), ".");
                        break;
                    case '*':
                        sb.replace(sb.length() - 1, sb.length(), ".*");
                        break;
                }
            }
        }

        return sb.append(".*").toString().replace(".*.*", ".*");
    }

    @When("I send CB search request - query:$query, eventFeed:$eventFeed, objectType:$objectType, pageNumber:$pageNumber, pageSize:$pageSize")
    public void recordSearch(String query, String eventFeed, String objectType, String pageNumber, String pageSize) {

        CBSearchFilter filter = new CBSearchFilter(eventFeed, objectType, query, pageSize, pageNumber);

        OperationResult<List<SearchEntity>> operationResult = service.search(filter);
        List<SearchEntity> searchResults = operationResult.getEntity();
        log.info("search results: " + searchResults.size());

        context.put("searchEntities", searchResults);
        context.put("searchQuery", query);
        context.put("cbSearchFilter", filter);
    }

    @When("Excel Driven Search ($excelpath)")
    public void ExcelDrivenSearch(String excelpath) {
        CBSearchExcel searchex = new CBSearchExcel(excelpath);
        OperationResult<List<SearchEntity>> operationResult = null;
        String date = "n/a";
        SearchResult<SearchEntity> searchResult = null;
        String query = "";

        for (int i = 0; i < searchex.rows.size(); i++) {
            String apierror = "";
            Boolean EventEntity = true;
            try {

                if(searchex.rowdata.get("objectType").get(i).equals("event"))
                    query = searchex.rowdata.get("query").get(i) + " " + searchex.rowdata.get("eventTime").get(i);

                basicSQMSearch(query ,"" ,searchex.rowdata.get("Source Type").get(i),
                        searchex.rowdata.get("objectType").get(i),searchex.rowdata.get("pageNumber").get(i), searchex.rowdata.get("pageSize").get(i),"eventTime");

                SQMSearchCompleted();
                SearchQueue searchQueue = context.get("searchQueue", SearchQueue.class);

                String eventFeed  =  searchex.rowdata.get("Source Type").get(i);
                String objectType =  searchex.rowdata.get("objectType").get(i);
                String pageNumber =  searchex.rowdata.get("pageNumber").get(i);
                String pageSize   =  searchex.rowdata.get("pageSize").get(i);

                 searchResult = sqmService.result(searchQueue.getUuid(),
                        DataSourceCategory.valueOf(eventFeed), SearchObjectType.valueOf(objectType),
                        Integer.valueOf(pageNumber), Integer.valueOf(pageSize)).getEntity();




                CBSearchFilter filter = new CBSearchFilter(searchex.rowdata.get("Source Type").get(i), searchex.rowdata.get("objectType").get(i),
                        searchex.rowdata.get("query").get(i),
                        searchex.rowdata.get("pageSize").get(i), searchex.rowdata.get("pageNumber").get(i));


                operationResult = service.search(filter);

                /*List<SearchEntity> searchResults = operationResult.getEntity();
                context.put("searchEntities", searchResults);
                context.put("cbSearchFilter", filter);*/

              //  cbSearchResultsContainsOnlySourceTypeAndObjectTypeRecords(searchex.rowdata.get("Source Type").get(i), searchex.rowdata.get("EventOREntity").get(i));

                if(objectType.toString().equals("entity") || searchResult.getTotalCount() == 0)
                    date = "n/a";
                else
                    date =  ((SearchRecord) searchResult.getData().get(0)).getEventTime().toString();

                searchex.getRequiredResult(searchex.rows.get(i), operationResult.getCode(),searchResult.getTotalCount(),
                        apierror, i,date);

            } catch (AssertionError e) {
                apierror = e.getMessage();
                if (apierror.contains("eventreturn") || apierror.contains("entityreturn")) {
                    apierror = "the data should contian only" + searchex.rowdata.get("EventOREntity").get(i) + "but thats not the case";
                    EventEntity = false;
                    e.printStackTrace();
                    searchex.getRequiredResult(searchex.rows.get(i), operationResult.getCode(), searchResult.getTotalCount(), apierror, i, EventEntity);
                } else {
                    e.printStackTrace();
                    searchex.getRequiredResult(searchex.rows.get(i), operationResult.getCode(),
                            Integer.parseInt(operationResult.getMessage().toString().split(",")[0].split(":")[1]), apierror, i,date);

                }
            } catch (Exception e) {
                apierror = e.getMessage();
                e.printStackTrace();
                searchex.getRequiredResult(searchex.rows.get(i), -1,
                        -1, apierror, i,date);
            }

        }
        searchex.writeOutputExcel();
        try {
            searchex.CopySheet();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void searchIngestedRecords(String objectType, String criteria, String size, String additionalQuery) {
        int expectedCount = Integer.valueOf(size);

        Source source = context.get("source", Source.class);
        FileMeta meta = context.get("meta", FileMeta.class);
        String searchQuery = processIdQuery + meta.getMeta().getProperties().getProcessId();
        if (additionalQuery != null) {
            searchQuery = searchQuery + " " + additionalQuery;
        }

        CBSearchFilter filter = new CBSearchFilter(
                "SIGINT",
                objectType,
                searchQuery,
                "1000",
                "0"
        );

        int actualCount = 0;
        int delay = 10;  // in seconds
        for (int i = 0; i < 5; i++) {
            // 5 attempts with 10, 20, 40, 80, 160 seconds delay
            waitSeveralseconds(String.valueOf(delay));
            OperationResult<List<SearchEntity>> operationResult = service.search(filter);
            List<SearchEntity> searchResults = operationResult.getEntity();
            actualCount = searchResults.size();

            if (compareByCriteria(criteria, actualCount, expectedCount)) {
                context.put("searchEntities", searchResults);
                context.put("searchQuery", searchQuery);
                return;
            }
            delay = delay * 2;
        }
        String errorMsg = String.format(
                "Found %d %s-%s %s records in search, expected %s %d",
                actualCount, source.getType(), source.getRecordType(), objectType, criteria, expectedCount);
        raiseError(errorMsg);
    }

    @Then("Number of ingested $objectType records in CB $criteria $size, additional query string: $additional")
    public void searchIngestedRecordsByProcessIdWithOptions(String objectType, String criteria, String size, String additional) {
        searchIngestedRecords(objectType, criteria, size, additional);
    }

    @Then("Number of ingested $objectType records in CB $criteria $size")
    public void searchIngestedRecordsByProcessId(String objectType, String criteria, String size) {
        searchIngestedRecords(objectType, criteria, size, null);
    }

    @Then("CB search result list size $criteria $size")
    public void CBSearchListSizeShouldBe(String criteria, String size) {
        List<SearchRecord> entities = context.get("searchEntities", List.class);
        String query = context.get("searchQuery", String.class);

        int expectedCount = Integer.valueOf(size);
        boolean condition = compareByCriteria(criteria, entities.size(), expectedCount);
        assertTrue("Search by query:" + query +
                        "\nExpected search results count " + criteria + " " + size + ", but was: " + entities.size(),
                condition);
    }

    @Then("All events have default designation")
    public void checkDefaultDesignation() {
        String defaultDesignation = "Undesignated";

        log.info("Check that all records have default designation: " + defaultDesignation);
        context.put("searchQuery", defaultDesignation);
        verifyCBSearch("contains");
    }

    @Then("Designated events have correct designations")
    public void designationsArePresentedInCBSearch() {
        List<DesignationMapping> designationMappings = context.get("designationMappingList", List.class);
        FileMeta meta = context.get("meta", FileMeta.class);
        String pid = processIdQuery + meta.getMeta().getProperties().getProcessId();

        int designatedEvents = 0;
        for (DesignationMapping designationMapping : designationMappings) {
            List<String> designations = designationMapping.getDesignations();
            assertNotNull("Designation-mapping with null designations: " + designationMapping.getId(), designations);

            String identifier = designationMapping.getIdentifier();
            recordSearch(
                    "senderphone:" + identifier + " AND includeSpam:true AND " + pid,
                    "SIGINT",
                    "event",
                    "0",
                    "100"
            );
            List<SearchRecord> entities = context.get("searchEntities", List.class);
            if (entities.isEmpty()) {
                // skip not designated records
                continue;
            }
            designatedEvents++; // increment if search result isn't empty
            for (String designation : designations) {
                log.info("Check '" + designation + "' designation in response");
                for (SearchRecord entity : entities) {
                    String json = toJsonString(entity);
                    if (!json.contains(designation)) {
                        raiseError("Event doesn't have '" + designation + "' designation:\n" + json);
                    }
                }
            }
        }
        if (designatedEvents < 1) {
            raiseError("Records aren't designated");
        }
    }

    @Then("Whitelisted identifiers are not searchable")
    public void whitelistedCBSearch() {
        List<Whitelist> filteredWhitelists = context.get("whitelistEntitiesList", List.class);

        log.info("Check that identifiers are filtered from CB search by pid");
        for (Whitelist filteredWhitelist : filteredWhitelists) {
            String identifier = filteredWhitelist.getIdentifier();
            context.put("searchQuery", identifier);
            verifyCBSearch("doesn't contain");
        }
        log.info("Check that identifier entities are not created");
        for (Whitelist filteredWhitelist : filteredWhitelists) {
            String identifier = filteredWhitelist.getIdentifier();
            recordSearch(identifier, "SIGINT", "entity", "0", "100");
            CBSearchListSizeShouldBe("==", "0");
        }
    }

    @Then("CB search $criteria results for query")
    public void verifyCBSearch(String criteria) {
        log.info("CB search result validation");

        boolean searchable = false;
        if (criteria.equalsIgnoreCase("contains")) {
            searchable = true;
        }
        List<SearchEntity> entities = context.get("searchEntities", List.class);
        String query = context.get("searchQuery", String.class);


        String regex = cbSearchQueryToRegex(query);
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE | Pattern.UNIX_LINES);

        for (SearchEntity entity : entities) {
            String json = toJsonString(entity).replaceAll("(\\r\\n\\t|\\r\\n|\\n)", " ").trim();

            Boolean matches;
            if (query.contains("~")) {
                matches = fuzzySearch(json, query);
            } else {
                matches = pattern.matcher(json).matches();
            }
            assertTrue("Search " + criteria + " results for query: " + query + " in response:\n" + json,
                    matches == searchable);
        }
    }

    @Then("pageSize size in response $criteria $size")
    public void pageSizeInResponseShouldBe(String criteria, String size) {

        OperationResult result = OperationsResults.getResult();
        SearchResult searchResult = jsonToObject(result.getMessage(), SearchResult.class);
        Integer pageSize = searchResult.getPageSize();

        int expectedCount = Integer.valueOf(size);
        boolean condition = compareByCriteria(criteria, pageSize, expectedCount);

        assertTrue("Expected pageSize size " + criteria + " " + size + ", but was: " + pageSize, condition);
    }

    private boolean fuzzySearch(String json, String query) {

        if (query.contains("~")) {
            Integer tildaIndex = query.indexOf("~");
            String q = query.substring(0, tildaIndex);

            Integer maxDistance = 3;
            String suffix = query.substring(tildaIndex + 1);
            if (!suffix.isEmpty()) {
                maxDistance = Integer.valueOf(suffix);
            }

            Map<String, Integer> distances = new HashMap<>();
            Map<String, String> flattenJSON = new JsonConverter().flattenJsonMap(json);

            for (String value : flattenJSON.values()) {
                String[] strings = value.split("\\s+|\\n+|\\W+");
                for (String s : strings) {
                    Integer distance = getLevenshteinDistance(s.toLowerCase(), q.toLowerCase());
                    distances.put(value.toLowerCase(), distance);
                    if (distance <= maxDistance)
                        return true;
                }
            }

            log.error("Pattern not founded, LevenshteinDistances:" + distances + " maxDistance:" + maxDistance);
        } else {
            log.error("Query string isn't fuzzy");
        }

        return false;
    }

    @When("I send workflow search request: record status:$recordStatus, source:$source, objectType:$objectType, pageNumber:$pageNumber, pageSize:$pageSize")
    public void searchWorkflowFiltersRecordStatus(String recordStatus, String source, String objectType, String pageNumber, String pageSize) {
        String query = recordStatusToQuery(recordStatus);
        CBSearchFilter filter = new CBSearchFilter(source, objectType, query, pageSize, pageNumber);

        OperationResult<List<SearchEntity>> operationResult = service.search(filter);
        List<SearchEntity> searchResults = operationResult.getEntity();

        context.put("searchEntities", searchResults);
        context.put("recordStatusFilter", recordStatus);
    }

    @Then("CB search results match the recordStatus filters")
    public void matchSearchResultsWithFilters() {
        List<SearchRecord> entities = context.get("searchEntities", List.class);
        String[] recordStatuses = context.get("recordStatusFilter", String.class).split("\\s+");

        for (SearchRecord entity : entities) {
            for (String recordStatus : recordStatuses) {
                switch (recordStatus.toLowerCase()) {
                    case "unassigned":
                        List<String> ownerId = entity.getAssignments().getOwnerId();
                        assertTrue("Search by RecordStatus:Unassigned, return assigned record:" + toJsonString(entity),
                                ownerId == null || ownerId.isEmpty());
                        break;
                    case "unprocessed":
                        assertTrue("Search by RecordStatus::Unprocessed return record with reportIds:" + toJsonString(entity),
                                entity.getReports() == null || entity.getReports().getReportIds().isEmpty());
                        assertFalse("Search by RecordStatus:Unprocessed return unimportance record:" + toJsonString(entity),
                                Objects.equals(entity.getAssignments().getImportance(), -1));
                        break;
                    case "reported":
                        assertFalse("Search by RecordStatus:Reported return record without reportIds:" + toJsonString(entity),
                                entity.getReports().getReportIds().isEmpty());
                        break;
                    case "unimportant":
                        assertTrue("Search by RecordStatus:Unimportant return NOT unimportance record:" + toJsonString(entity),
                                Objects.equals(entity.getAssignments().getImportance(), -1));
                        break;
                    default:
                        throw new AssertionError("Unknown recordStatus filter value: " + recordStatus);
                }
            }
        }
    }

    @Then("CB search results contains only eventFeed:$eventFeed and type:$resultType records")
    public void cbSearchResultsContainsOnlySourceTypeAndObjectTypeRecords(String eventFeed, String resultType) {
        List<SearchEntity> entities = context.get("searchEntities", List.class);
        CBSearchFilter filter = context.get("cbSearchFilter", CBSearchFilter.class);

        SearchEntity entity = entities.stream()
                .filter(cbEntity -> !Objects.equals(cbEntity.getEventFeed(), DataSourceCategory.valueOf(eventFeed)))
                .findAny().orElse(null);
        assertNull("Search by eventFeed:" + eventFeed + ", return:\n" + toJsonString(entity), entity);

        if (!eventFeed.equals("PROFILER")) {
            entity = entities.stream()
                    .filter(cbEntity -> !cbEntity.getType().equals(resultType))
                    .findAny().orElse(null);
            assertNull("Search by object type:" + filter.getObjectType() + "return:" + toJsonString(entity), entity);
        }
    }

    @Then("CB search results contains only sourceType from query")
    public void cbSearchResultsContainsOnlyDataSourceTypeFromQuery() {
        String query = context.get("searchQuery", String.class);
        String dataSourceType = extractStringsInQuotes(query).get(0);

        List<SearchRecord> entities = context.get("searchEntities", List.class);

        SearchRecord wrongEntity = entities.stream()
                .filter(cbEntity -> !cbEntity.getSources().contains(DataSourceType.valueOf(dataSourceType)))
                .findAny().orElse(null);

        assertNull("Search by:" + query + " return:" + toJsonString(wrongEntity), wrongEntity);
    }

    @Then("CB search results contains only subSource from query")
    public void cbSearchResultsContainsOnlyDataSubSourceFromQuery() {
        String query = context.get("searchQuery", String.class);
        String subSource = extractStringsInQuotes(query).get(0);

        List<SearchRecord> entities = context.get("searchEntities", List.class);

        SearchRecord wrongEntity = entities.stream()
                .filter(cbEntity -> !cbEntity.getSubSourceType().contains(subSource))
                .findAny().orElse(null);

        assertNull("Search by:" + query + " return:" + toJsonString(wrongEntity), wrongEntity);
    }

    @Then("CB search results contains only recordType from query")
    public void cbSearchResultsContainsOnlyRecordTypeFromQuery() {
        String query = context.get("searchQuery", String.class);
        String recordType = extractStringsInQuotes(query).get(0);

        List<SearchRecord> entities = context.get("searchEntities", List.class);

        SearchRecord wrongEntity = entities.stream()
                .filter(cbEntity -> !cbEntity.getRecordType().contains(recordType))
                .findAny().orElse(null);

        assertNull("Search by:" + query + " return:" + toJsonString(wrongEntity), wrongEntity);
    }

    @When("I send CB search request - eventTime:$eventTime, source:$source, objectType:$objectType, pageNumber:$pageNumber, pageSize:$pageSize")
    public void searchByEventTime(String eventTime, String source, String objectType, String pageNumber, String pageSize) {

        TimeRange timeRange = stringToTimeRange(eventTime);
        String query = timeRangeToQuery(timeRange);

        CBSearchFilter filter = new CBSearchFilter(source, objectType, query, pageSize, pageNumber);

        OperationResult<List<SearchEntity>> operationResult = service.search(filter);
        List<SearchEntity> searchResults = operationResult.getEntity();

        context.put("searchEntities", searchResults);
        context.put("timeRange", timeRange);
        context.put("query", query);
    }

    @Then("CB search results contains only eventTime from query")
    public void cbSearchResultsContainsOnlyEventTimeFromQuery() {
        List<SearchRecord> entities = context.get("searchEntities", List.class);
        TimeRange timeRange = context.get("timeRange", TimeRange.class);
        String query = context.get("query", String.class);

        SearchRecord wrongEntity = entities.stream()
                .filter(cbEntity -> !inRange(cbEntity.getEventTime(), timeRange))
                .findAny().orElse(null);

        assertNull("Search by:" + query + " return:" + toJsonString(wrongEntity), wrongEntity);
    }

    @When("I send CB search count request - query:$query, objectType:$objectType, sources:$source")
    public void cbSearchResultCount(String query, String objectType, String sources) {
        List<DataSourceCategory> sourceCategories = new ArrayList<>();
        Arrays.stream(StringUtils.splitToArray(sources))
                .forEach(s -> sourceCategories.add(DataSourceCategory.valueOf(s)));
        CBSearchFilter filter = new CBSearchFilter(sourceCategories, objectType, query);

        OperationResult<SearchResult[]> result = service.count(filter);

        context.put("searchResults", result.getEntity());
        context.put("searchQuery", query);
    }

    @Then("TotalCount's in search results $criteria $size")
    public void SearchResultTolalCountShouldBe(String criteria, String size) {
        SearchResult[] searchResults = context.get("searchResults", SearchResult[].class);

        int expectedCount = Integer.valueOf(size);
        List<SearchResult> wrongResults = Arrays.stream(searchResults)
                .filter(result -> !compareByCriteria(criteria, result.getData().size(), expectedCount))
                .collect(Collectors.toList());

        if (!wrongResults.isEmpty()) {
            wrongResults.forEach(searchResult -> log.error(searchResult.getEventFeed() + " Expected search results count " + criteria + " " + size +
                    ", but was: " + toJsonString(searchResult.getTotalCount())));
            throw new AssertionError(wrongResults.get(0).getEventFeed() + " Expected search results count " + criteria + " " + size +
                    ", but was: " + toJsonString(wrongResults.get(0).getData().size()));
        }
    }

    @When("I send SQM search request - query:$query, sourceTypes:$sourceTypes, objectType:$objectType, pageNumber:$pageNumber, pageSize:$pageSize, sortKey:$sortKey")
    public void SQMSearch(String query, String sourceTypes, String objectType, String pageNumber, String pageSize, String sortKey) {
        basicSQMSearch(query, "", sourceTypes, objectType, pageNumber, pageSize, sortKey);
    }

    @When("I send basic SQM search request - query:$query, metadata:$metadata, sourceTypes:$sourceTypes, objectType:$objectType, pageNumber:$pageNumber, pageSize:$pageSize, sortKey:$sortKey")
    public void basicSQMSearch(String query, String metadata, String sourceTypes, String objectType, String pageNumber, String pageSize, String sortKey) {
        SearchQueueRequest request = new SearchQueueRequest();
        request.setSourceType(StringUtils.splitToList(sourceTypes));
        request.setMetadata(metadata);
        request.setQuery(query);
        request.setObjectType(StringUtils.splitToList(objectType));
        request.getPage().setPageNumber(Integer.valueOf(pageNumber));
        request.getPage().setPageSize(Integer.valueOf(pageSize));

        if (Objects.equals(sortKey, "eventTime")) {
            SearchQueueRequest.Sort sort = request.new Sort("eventTime", SortDirection.DESC);
            request.getSort().add(sort);
        }

        OperationResult<String> searchResults = sqmService.search(request);

        context.put("searchQueueId", searchResults.getEntity());
        context.put("searchQuery", query);
    }

    @When("SQM search completed")
    public void SQMSearchCompleted() {
        String searchQueueId = context.get("searchQueueId", String.class);

        int WAITING_TIME = 3 * 60;
        int POLLING_INTERVAL = 3;
        Date deadline = DateHelper.getDateWithShift(WAITING_TIME);

        SearchQueue searchQueue = sqmService.view(searchQueueId).getEntity();

        while (searchQueue.getStatus() != SearchStatus.COMPLETED && !isTimeout(deadline)) {
            DateHelper.waitTime(POLLING_INTERVAL);
            searchQueue = sqmService.view(searchQueueId).getEntity();
        }
        assertSame("Search queue id:" + searchQueueId + " not complete in time " + WAITING_TIME + "s",
                searchQueue.getStatus(), SearchStatus.COMPLETED);

        context.put("searchQueue", searchQueue);
    }

    @When("I get search queue results: $examplesTable")
    public void getSearchQueueResults(ExamplesTable examplesTable) {
        SearchQueue searchQueue = context.get("searchQueue", SearchQueue.class);

        List<SearchResult<SearchEntity>> searchResults = new ArrayList<>();
        for (Map<String, String> row : examplesTable.getRows()) {
            String eventFeed = row.get("eventFeed");
            String objectType = row.get("objectType");
            String pageNumber = row.get("pageNumber");
            String pageSize = row.get("pageSize");

            SearchResult<SearchEntity> searchResult = sqmService.result(searchQueue.getUuid(),
                    DataSourceCategory.valueOf(eventFeed), SearchObjectType.valueOf(objectType),
                    Integer.valueOf(pageNumber), Integer.valueOf(pageSize)).getEntity();
            searchResults.add(searchResult);
        }

        context.put("searchResults", searchResults.toArray(new SearchResult[0]));
    }

    @Then("CB search results matched to filters")
    public void searchResultsMatchedToFilters() {
        SearchQueue searchQueue = context.get("searchQueue", SearchQueue.class);
        SearchResult<SearchEntity>[] searchResults = context.get("searchResults", SearchResult[].class);

        SearchFilters searchFilters = jsonToObject(searchQueue.getMetadata(), SearchFilters.class, "filters");

        SearchFiltersVerification searchFiltersVerification = new SearchFiltersVerification();
        searchFiltersVerification.verify(searchFilters, searchResults);
    }

    @Then("CB search results contains $field field")
    public void searchResultsContainsInterceptRefField(String field) {
        List<SearchRecord> entities = context.get("searchEntities", List.class);
        String INTERCEPT_REF = "interceptRef";

        for (SearchRecord searchRecord : entities) {
            assertTrue("Search result without interceptRef:\n" + toJsonString(searchRecord),
                    searchRecord.getAttributes().containsKey("interceptRef")
            );
            String value = (String) searchRecord.getAttributes().get(INTERCEPT_REF);
            assertTrue(INTERCEPT_REF + " value is empty",
                    !value.isEmpty());
        }
    }

    @When("Get participants from random record in search results")
    public void addPhoneNumberFromRandomRecordToProfile() {
        List<SearchRecord> searchEntities = context.get("searchEntities", List.class);
        SearchRecord searchRecord = getRandomItemFromList(searchEntities);

        List<SearchRecord> participants = new ArrayList<>();
        Assert.assertTrue(
                "Can't get FROM:" + JsonConverter.toJsonString(searchRecord),
                participants.addAll(searchRecord.getEntities().getFrom()));
        Assert.assertTrue(
                "Can't get TO:" + JsonConverter.toJsonString(searchRecord),
                participants.addAll(searchRecord.getEntities().getTo()));

        context.put("participants", participants);
    }
}
