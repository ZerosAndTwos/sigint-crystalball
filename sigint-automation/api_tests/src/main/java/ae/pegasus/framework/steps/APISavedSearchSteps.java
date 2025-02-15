package ae.pegasus.framework.steps;

import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.json.JsonConverter;
import ae.pegasus.framework.model.SavedSearch;
import ae.pegasus.framework.model.SavedSearchFilter;
import ae.pegasus.framework.model.SearchFilter;
import ae.pegasus.framework.services.SavedSearchService;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static ae.pegasus.framework.utils.StepHelper.compareByCriteria;

public class APISavedSearchSteps extends APISteps {

    private SavedSearchService service = new SavedSearchService();

    @When("I send get savedSearches request: page:$page, pageSize:$pageSize, sortKey:$sortKey, sortOrder:$sortOrder")
    public void searchSavedSearch(String page, String pageSize, String sortKey, String sortOrder) {
        SearchFilter filter = new SavedSearchFilter();
        filter.setPage(Integer.valueOf(page));
        filter.setPageSize(Integer.valueOf(pageSize));
        filter.setSortField(sortKey);
        filter.setSortDirection(sortOrder.equals("ASC"));

        OperationResult<List<SavedSearch>> savedSearches = service.search(filter);
        context.put("savedSearchResults", savedSearches.getEntity());
    }

    @SuppressWarnings("unchecked")
    @Then("SavedSearch result list size $criteria $size")
    public void savedSearchListSizeShouldBe(String criteria, String size) {
        List<SavedSearch> searches = context.get("savedSearchResults", List.class);

        int expectedCount = Integer.valueOf(size);
        boolean condition = compareByCriteria(criteria, searches.size(), expectedCount);
        Assert.assertTrue(
                "Expected savedSearch results count " + criteria + " " + size + ", but was: " + searches.size(),
                condition);
    }

    @SuppressWarnings("unchecked")
    @Then("Sort by $sortKey order $sortOrder is valid")
    public void sortByFieldIsValid(String sortKey, String sortOrder) {
        List<SavedSearch> searches = context.get("savedSearchResults", List.class);

        if (searches.size() > 1) {
            for (int i = 0; i < searches.size() - 1; i++) {
                SavedSearch curr = searches.get(i);
                SavedSearch next = searches.get(i + 1);
                Integer compare;

                switch (sortKey) {
                    case "name":
                        compare = curr.getName().toLowerCase().compareTo(next.getName().toLowerCase());
                        break;
                    case "assignmentPriority":
                        compare = curr.getAssignmentPriority().compareTo(next.getAssignmentPriority());
                        break;
                    case "createdAt":
                        compare = curr.getCreatedAt().compareTo(next.getCreatedAt());
                        break;
                    case "expirationDate":
                        Date currDate = curr.getExpirationDate();
                        Date nextDate = next.getExpirationDate();

                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.YEAR, 100);

                        if (currDate == null) {
                            currDate = calendar.getTime();
                        }
                        if (nextDate == null) {
                            nextDate = calendar.getTime();
                        }

                        compare = currDate.compareTo(nextDate);
                        break;
                    default:
                        throw new AssertionError("Unknown sortKey value:" + sortKey);
                }

                Assert.assertTrue("compare value:" + compare
                                + ", sortKey:" + sortKey + ", sortOrder:" + sortOrder
                                + "\ncurr:" + JsonConverter.toJsonString(curr)
                                + "\nnext:" + JsonConverter.toJsonString(next),
                        sortOrder.equals("ASC") ? compare <= 0 : compare >= 0);
            }
        }
    }
}