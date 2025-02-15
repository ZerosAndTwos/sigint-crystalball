package ae.pegasus.framework.steps.special.search_result;

import ae.pegasus.framework.constants.special.search_results.SearchResultsEntity;
import ae.pegasus.framework.constants.special.search_results.SearchResultsEvent;
import ae.pegasus.framework.pages.Pages;
import ae.pegasus.framework.utils.ParametersHelper;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;

public class UISelectCardsSteps {

    @When("I select 1-st displayed SIGINT event card")
    public void iSelect1stSIGINTEvent() {
        Pages.searchResultsAsCardsPage().select1stDisplayedCard(SearchResultsEvent.SIGINT);
    }

    @When("I select 1-st displayed card")
    public void iSelect1stDisplauedCard() {
        Pages.searchResultsAsCardsPage().select1stDisplayedCard();
    }

    private void selectAllCards(SearchResultsEvent event, ExamplesTable identifiers) {
        Pages.searchResultsAsCardsPage().selectAllCardsWithIdentifiers(event, ParametersHelper.processExampleTable(identifiers));
    }

    @When("I select all SIGINT event cards having identifiers: $identifiers")
    public void iSelectAllSIGINTEvents(ExamplesTable identifiers) {
        selectAllCards(SearchResultsEvent.SIGINT, identifiers);
    }

    @When("I select all FININT event cards having identifiers: $identifiers")
    public void iSelectAllFININTEvents(ExamplesTable identifiers) {
        selectAllCards(SearchResultsEvent.FININT, identifiers);
    }

    @When("I select all TRAFFIC event cards having identifiers: $identifiers")
    public void iSelectAllTRAFFICEvents(ExamplesTable identifiers) {
        selectAllCards(SearchResultsEvent.TRAFFIC, identifiers);
    }

    @When("I select all EID event cards having identifiers: $identifiers")
    public void iSelectAllEIDEvents(ExamplesTable identifiers) {
        selectAllCards(SearchResultsEvent.EID, identifiers);
    }

    @When("I select all GOVINT event cards having identifiers: $identifiers")
    public void iSelectAllGOVINTEvents(ExamplesTable identifiers) {
        selectAllCards(SearchResultsEvent.GOVINT, identifiers);
    }

    @When("I select all OSINT event cards having identifiers: $identifiers")
    public void iSelectAllOSINTEvents(ExamplesTable identifiers) {
        selectAllCards(SearchResultsEvent.OSINT, identifiers);
    }

    @When("I select all CIO event cards having identifiers: $identifiers")
    public void iSelectAllCIOEvents(ExamplesTable identifiers) {
        selectAllCards(SearchResultsEvent.CIO, identifiers);
    }

    private void selectAllCards(SearchResultsEntity entity, ExamplesTable identifiers) {
        Pages.searchResultsAsCardsPage().selectAllCardsWithIdentifiers(entity, ParametersHelper.processExampleTable(identifiers));
    }

    @When("I select all Profiler entity cards having identifiers: $identifiers")
    public void iSelectAllProfilerEntities(ExamplesTable identifiers) {
        selectAllCards(SearchResultsEntity.PROFILER, identifiers);
    }

    @When("I select all SIGINT entity cards having identifiers: $identifiers")
    public void iSelectAllSIGINTEntities(ExamplesTable identifiers) {
        selectAllCards(SearchResultsEntity.SIGINT, identifiers);
    }

    @When("I select all EID entity cards having identifiers: $identifiers")
    public void iSelectAllEIDEntities(ExamplesTable identifiers) {
        selectAllCards(SearchResultsEntity.EID, identifiers);
    }

    @When("I select all GOVINT entity cards having identifiers: $identifiers")
    public void iSelectAllGOVINTEntities(ExamplesTable identifiers) {
        selectAllCards(SearchResultsEntity.GOVINT, identifiers);
    }

    @When("I select all OSINT entity cards having identifiers: $identifiers")
    public void iSelectAllOSINTEntities(ExamplesTable identifiers) {
        selectAllCards(SearchResultsEntity.OSINT, identifiers);
    }

    @When("I select all CIO entity cards having identifiers: $identifiers")
    public void iSelectAllCIOEntities(ExamplesTable identifiers) {
        selectAllCards(SearchResultsEntity.CIO, identifiers);
    }

    @Given("I select all cards on the Search page")
    public void iSelectAllCards() {
        Pages.searchResultsAsCardsPage().selectAllCardsOnSearchPage();
    }

}
