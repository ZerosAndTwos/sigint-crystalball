package ae.pegasus.framework.steps;

import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.json.JsonConverter;
import ae.pegasus.framework.model.Tag;
import ae.pegasus.framework.model.TagFilter;
import ae.pegasus.framework.services.TagService;
import org.apache.log4j.Logger;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import java.util.List;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class APITagSteps extends APISteps {

    private Logger log = Logger.getLogger(APITagSteps.class);
    private TagService service = new TagService();

    private static Tag createRandomTag() {
        return objectInitializer.randomEntity(Tag.class);
    }

    @When("I send create new tag request")
    public void createTag() {
        Tag tag = createRandomTag();
        context.put("tag", tag);

        service.add(tag);
    }

    @When("I send get list of tags request")
    public void getListOfTags() {
        OperationResult<List<Tag>> operationResult = service.list();

        context.put("tagList", operationResult.getEntity());
    }

    @Then("Tags list size more than $size")
    public void taglistSizeShouldBeMoreThan(String size) {
        List<Tag> tags = context.get("tagList", List.class);
        Assert.assertTrue(tags.size() > Integer.valueOf(size));
    }

    @When("I send search tags by $criteria with $value request")
    public void searchTags(String criteria, String value) {
        Tag tag = context.get("tag", Tag.class);

        switch (criteria.toLowerCase()) {
            case "name":
                value = value.equals("random") ? tag.getName() : value;
                break;
            case "empty":
                log.debug("Search without filter..");
                break;
            default:
                throw new AssertionError("Unknown filter type");
        }

        TagFilter filter = new TagFilter().filterBy(criteria, value);
        OperationResult<List<Tag>> operationResult = service.search(filter);

        context.put("tagList", operationResult.getEntity());
        context.put("tagFilter", filter);
    }

    @When("I send delete tag request")
    public void deleteTag() {
        Tag tag = context.get("tag", Tag.class);
        service.remove(tag);
    }

    @When("I get created tag from list of tags")
    public void getRandomtaFromList() {
        List<Tag> tags = context.get("tagList", List.class);
        Tag tag = context.get("tag", Tag.class);

        for (Tag entity : tags) {
            if (Objects.equals(entity.getName(), tag.getName())) {
                context.put("tag", entity);
                return;
            }
        }
        throw new AssertionError("Tag this name: " + tag.getName() + " not founded in tag list");
    }

    @Then("tags search result are correct")
    public void tagsSearchResultShouldBeCorrect() {
        log.info("Checking if search targets result is correct");
        TagFilter searchFilter = context.get("tagFilter", TagFilter.class);
        List<Tag> searchResult = context.get("tagList", List.class);

        if (searchResult.size() == 0) {
            log.warn("Search result can be incorrect. There are not records in it");
        } else {
            log.info("Search result size: " + searchResult.size());
        }

        for (Tag tag : searchResult) {
            Assert.assertTrue(
                    String.format("Tag: %s should not match to filter %s", tag, JsonConverter.toJsonString(searchFilter)),
                    searchFilter.isAppliedToEntity(tag)
            );
        }
    }
}
