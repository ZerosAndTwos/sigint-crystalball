package ae.pegasus.framework.verification;

import ae.pegasus.framework.model.*;
import ae.pegasus.framework.utils.ObjectUtils;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ae.pegasus.framework.json.JsonConverter.toJsonString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchFiltersVerification {

    protected Logger log = Logger.getLogger(SearchFiltersVerification.class);

    public SearchFiltersVerification() {
    }

    public void verify(SearchFilters searchFilters, SearchResult<SearchEntity>[] searchResults) {

        Map<String, Object> filtersMap = ObjectUtils.fieldsValueToMap(searchFilters);
        filtersMap = ObjectUtils.mapWithoutNullValues(filtersMap);

        for (SearchResult<SearchEntity> searchResult : searchResults) {
            for (Map.Entry<String, Object> filter : filtersMap.entrySet()) {
                SearchFilterName searchFilterName = SearchFilterName.valueOf(filter.getKey());
                for (SearchEntity entity : searchResult.getData()) {

                    String errorMessage = "filter " + searchFilterName + ":" + toJsonString(filter.getValue()) +
                            ", entity: " + toJsonString(entity);

                    switch (searchFilterName) {
                        case eventFeed:
                            DataSourceCategory filterDataSourceCategory = DataSourceCategory.valueOf((String) filter.getValue());

                            assertEquals(filterDataSourceCategory, entity.getEventFeed());
                            break;
                        case type:
                            typeVerification(searchFilterName, filter.getValue(), entity);
                            break;
                        case objectType:
                            SearchObjectType filterObjectType = (SearchObjectType) filter.getValue();

                            assertEquals(filterObjectType, ((SearchRecord) entity).getObjectType());
                            break;
                        case dataSource:
                            List<DataSourceType> filterDataSourceTypes = (List<DataSourceType>) filter.getValue();

                            filterDataSourceTypes.forEach(
                                    dataSourceType -> {
                                        // sourceType, sources
                                        assertEquals(dataSourceType, DataSourceType.valueOf(entity.getSourceType()));
                                        entity.getSources().forEach(source ->
                                                assertEquals(errorMessage, dataSourceType, source));
                                    }
                            );

                            break;
                        case eventTime:
                            SearchFilters.EventTime filterTime = (SearchFilters.EventTime) filter.getValue();

                            assertTrue(errorMessage, ((SearchRecord) entity).getEventTime().after(filterTime.getFrom()));
                            assertTrue(errorMessage, ((SearchRecord) entity).getEventTime().before(filterTime.getTo()));
                            break;
                        default:
                            log.error("Verification not implemented for search filter: " + searchFilterName);
                            break;
                    }
                }
            }
        }
    }

    private void typeVerification(SearchFilterName filterName, Object filterValue, SearchEntity entity) {
        boolean isCorrect = false;
        List<RecordType> recordTypes;
        List<EventType> eventTypes = ((List<String>) filterValue).stream()
                .map(EventType::valueOf)
                .collect(Collectors.toList());

        for (EventType eventType : eventTypes) {
            switch (eventType) {
                case TEXTING:
                    recordTypes = Stream.of(RecordType.SMS, RecordType.MMS, RecordType.VSMS, RecordType.SIP_VIDEO)
                            .collect(Collectors.toList());
                    isCorrect = recordTypes.contains(RecordType.valueOf(entity.getRecordType()));
                    break;
                case CALL:
                    recordTypes = Stream.of(RecordType.CALL).collect(Collectors.toList());
                    isCorrect = recordTypes.contains(RecordType.valueOf(entity.getRecordType()));
                    break;
                case VOIP:
                    recordTypes = Stream.of(RecordType.VOIP).collect(Collectors.toList());
                    isCorrect = recordTypes.contains(RecordType.valueOf(entity.getRecordType()));
                    break;
                default:
                    throw new AssertionError("Unknown event type: " + eventType);
            }
            if (isCorrect) break;
        }

        assertTrue("Search filter: " + filterName + ", value: " + toJsonString(filterValue)
                        + " return entity:" + toJsonString(entity),
                isCorrect);
    }
}
