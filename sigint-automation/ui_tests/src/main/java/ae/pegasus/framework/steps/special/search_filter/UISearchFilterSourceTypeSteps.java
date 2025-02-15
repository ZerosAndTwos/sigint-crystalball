package ae.pegasus.framework.steps.special.search_filter;

import ae.pegasus.framework.constants.special.search_filter.FilterSourceType;
import org.jbehave.core.annotations.Given;
import ae.pegasus.framework.pages.Pages;

import static ae.pegasus.framework.constants.special.search_filter.FilterSetting.SOURCE_TYPE;

public class UISearchFilterSourceTypeSteps {

    private void setSourceType(FilterSourceType sourceType) {
        Pages.searchFilterPage().setSingleValueSetting(SOURCE_TYPE, sourceType.getSourceName());
    }

    @Given("I set Source Type to All on the Search Filter page")
    public void iSetSourceTypeToAll() {
        setSourceType(FilterSourceType.ALL);
    }

    @Given("I set Source Type to Profiler on the Search Filter page")
    public void iSetSourceTypeToProfiler() {
        setSourceType(FilterSourceType.PROFILER);
    }

    @Given("I set Source Type to Documents on the Search Filter page")
    public void iSetSourceTypeToDocuments() {
        setSourceType(FilterSourceType.DOCUMENTS);
    }

    @Given("I set Source Type to SIGINT on the Search Filter page")
    public void iSetSourceTypeToSIGINT() {
        setSourceType(FilterSourceType.SIGINT);
    }

    @Given("I set Source Type to EID on the Search Filter page")
    public void iSetSourceTypeToEID() {
        setSourceType(FilterSourceType.EID);
    }

    @Given("I set Source Type to FININT on the Search Filter page")
    public void iSetSourceTypeToFININT() {
        setSourceType(FilterSourceType.FININT);
    }

    @Given("I set Source Type to Traffic on the Search Filter page")
    public void iSetSourceTypeToTraffic() {
        setSourceType(FilterSourceType.TRAFFIC);
    }

    @Given("I set Source Type to GOVINT on the Search Filter page")
    public void iSetSourceTypeToGOVINT() {
        setSourceType(FilterSourceType.GOVINT);
    }

    @Given("I set Source Type to OSINT on the Search Filter page")
    public void iSetSourceTypeToOSINT() {
        setSourceType(FilterSourceType.OSINT);
    }

    @Given("I set Source Type to CIO on the Search Filter page")
    public void iSetSourceTypeToCIO() {
        setSourceType(FilterSourceType.CIO);
    }
}
