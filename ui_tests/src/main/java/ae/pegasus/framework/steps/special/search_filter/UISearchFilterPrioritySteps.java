package ae.pegasus.framework.steps.special.search_filter;

import ae.pegasus.framework.constants.special.search_filter.FilterPriority;
import org.jbehave.core.annotations.Given;
import ae.pegasus.framework.pages.Pages;

import static ae.pegasus.framework.constants.special.search_filter.FilterSetting.RECORD_STATUS;

public class UISearchFilterPrioritySteps {

    private void setPriority(FilterPriority objectType) {
        Pages.searchFilterPage().setSingleValueSetting(RECORD_STATUS, objectType.getPriorityName());
    }

    @Given("I set Priority to All on the Search Filter page")
    public void iSetPriorityToAll() {
        setPriority(FilterPriority.ALL);
    }

    @Given("I set Priority to Regular on the Search Filter page")
    public void iSetPriorityToRegular() {
        setPriority(FilterPriority.REGULAR);
    }

    @Given("I set Priority to High on the Search Filter page")
    public void iSetPriorityToHigh() {
        setPriority(FilterPriority.HIGH);
    }

    @Given("I set Priority to Urgent on the Search Filter page")
    public void iSetPriorityToUrgent() {
        setPriority(FilterPriority.URGENT);
    }
}
