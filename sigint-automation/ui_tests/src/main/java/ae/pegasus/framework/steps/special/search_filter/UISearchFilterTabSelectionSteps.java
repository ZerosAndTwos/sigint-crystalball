package ae.pegasus.framework.steps.special.search_filter;

import ae.pegasus.framework.constants.special.search_filter.FilterObjectType;
import ae.pegasus.framework.constants.special.search_filter.FilterTab;
import ae.pegasus.framework.pages.Pages;
import org.jbehave.core.annotations.Given;

import static ae.pegasus.framework.constants.special.search_filter.FilterSetting.OBJECT_TYPE;

public class UISearchFilterTabSelectionSteps {

    private void setObjectType(FilterObjectType objectType) {
        Pages.searchFilterPage().setSingleValueSetting(OBJECT_TYPE, objectType.getObjectName());
    }

    @Given("I set Search Tab to General on the Search Filter page")
    public void iSetSearchTabToGeneral() {
        Pages.searchFilterPage().selectTab(FilterTab.GENERAL);
    }

    @Given("I set Search Tab to Files on the Search Filter page")
    public void iSetSearchTabToFiles() {
        Pages.searchFilterPage().selectTab(FilterTab.FILES);
    }


    @Given("I set Search Tab to Record Assessment on the Search Filter page")
    public void iSetSearchTabToRecordAssessment() {
        Pages.searchFilterPage().selectTab(FilterTab.RECORD_ASSESSMENT);
    }

}
