package ae.pegasus.framework.steps;

import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.model.SourceType;
import ae.pegasus.framework.services.DictionaryService;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;

@SuppressWarnings("unchecked")
public class APIDictionariesSteps extends APISteps {

    @When("I send get list of dictionary sources request")
    public void getAllSources() {
        OperationResult<List<SourceType>> operationResult = DictionaryService.getAllSources();

        context.put("dictionarySources", operationResult.getEntity());
    }

    @When("I send get list of manual dictionary sources request")
    public void getAllManualSources() {
        OperationResult<List<SourceType>> operationResult = DictionaryService.getManualSources();

        context.put("dictionarySources", operationResult.getEntity());
    }

    @Then("Dictionary source list size more than $size")
    public void dictionarySourcesListMoreThan(String size) {
        List<SourceType> sources = context.get("dictionarySources", List.class);

        assertThat(sources.size(), greaterThan(Integer.valueOf(size)));
    }

    @Then("List of manual sources is correct")
    public void manualSourcesDictionaryIsCorrect() {
        List<SourceType> sources = context.get("dictionarySources", List.class);

        List<SourceType> expectedSources = Arrays.asList(
                new SourceType("SIGINT", "T", "T"),
                new SourceType("SIGINT", "F", "SMS"),
                new SourceType("SIGINT", "S", "SMS"),
                new SourceType("SIGINT", "H", null)
        );

        assertThat(sources, containsInAnyOrder(expectedSources.toArray()));
    }
}
