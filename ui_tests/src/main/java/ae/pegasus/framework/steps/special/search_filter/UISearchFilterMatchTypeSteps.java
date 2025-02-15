package ae.pegasus.framework.steps.special.search_filter;

import ae.pegasus.framework.constants.special.search_filter.FilterMatchType;
import org.jbehave.core.annotations.Given;
import ae.pegasus.framework.pages.Pages;

import static ae.pegasus.framework.constants.special.search_filter.FilterSetting.MATCH_TYPE;

public class UISearchFilterMatchTypeSteps {

    private void setMatchType(FilterMatchType objectType) {
        Pages.searchFilterPage().setSingleValueSetting(MATCH_TYPE, objectType.getMatchType());
    }

    @Given("I set Match Type to All on the Search Filter page")
    public void iSetMatchTypeToAll() {
        setMatchType(FilterMatchType.ALL);
    }

    @Given("I set Match Type to Hits Only on the Search Filter page")
    public void iSetMatchTypeToHitsOnly() {
        setMatchType(FilterMatchType.HITS_ONLY);
    }

    @Given("I set Match Type to Mentions Only on the Search Filter page")
    public void iSetMatchTypeToMentionsOnly() {
        setMatchType(FilterMatchType.MENTIONS_ONLY);
    }
}
