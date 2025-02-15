package ae.pegasus.framework.steps.special.search_result;

import ae.pegasus.framework.utils.ParametersHelper;
import ae.pegasus.framework.constants.search.search.SearchActionButton;
import ae.pegasus.framework.constants.special.search_results.SearchResultsEntity;
import ae.pegasus.framework.constants.special.search_results.SearchResultsEvent;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import ae.pegasus.framework.pages.Pages;

import static com.codeborne.selenide.Selenide.sleep;

public class UICardsActionsSteps {
    @Given("I delete all Profiler entity cards on the Search page having identifiers: $identifiers")
    public void iDeleteAllProfilerEntities(ExamplesTable identifiers) {
        Pages.searchResultsAsCardsPage().deleteAllCardsWithIdentifiers(SearchResultsEntity.PROFILER, ParametersHelper.processExampleTable(identifiers));
    }

    @When("I open details of 1-st displayed SIGINT Event")
    public void iOpenDetailsOf1stSIGINTEvent() {
        Pages.searchResultsAsCardsPage().applyActionTo1stDisplayedCard(SearchResultsEvent.SIGINT, SearchActionButton.VIEW_DETAILS);
        Pages.searchResultDetailsPage().waitDialogLoading();
        //FIXME Seems there is no loading when details opened but such loading is required
        sleep(1200);
    }

    @When("I open details of 1-st displayed card")
    public void iOpenDetailsOf1stCard() {
        Pages.searchResultsAsCardsPage().applyActionTo1stDisplayedCard(SearchActionButton.VIEW_DETAILS);
        Pages.searchResultDetailsPage().waitDialogLoading();
        //FIXME Seems there is no loading when details opened but such loading is required
        sleep(1200);
    }

    @When("I open details of 1-st displayed report card")
    public void iOpenDetailsOf1stReportCard() {
        Pages.searchResultsAsCardsPage().applyActionTo1stDisplayedCard(SearchActionButton.VIEW_REPORT);
      //  Pages.searchResultDetailsPage().waitDialogLoading();
        //FIXME Seems there is no loading when details opened but such loading is required
        sleep(1200);
    }

    @Given("I view 1-st displayed Profiler entity in Profiler")
    public void iView1stProfilerEntityInProfiler() {
        Pages.searchResultsAsCardsPage().applyActionTo1stDisplayedCard(SearchResultsEntity.PROFILER, SearchActionButton.VIEW_IN_PROFILER);
        Pages.profilerSummaryTab().waitForPageLoading();
    }
}
