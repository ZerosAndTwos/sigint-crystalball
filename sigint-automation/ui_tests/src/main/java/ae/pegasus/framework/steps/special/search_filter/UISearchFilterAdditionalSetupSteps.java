package ae.pegasus.framework.steps.special.search_filter;

import ae.pegasus.framework.utils.ParametersHelper;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.model.ExamplesTable;
import ae.pegasus.framework.pages.Pages;

import static ae.pegasus.framework.constants.special.search_filter.FilterSetting.*;

public class UISearchFilterAdditionalSetupSteps {

    @Given("I set Phone Number to ($settingValue) on the Search Filter page")
    public void iSetPhoneNumber(String settingValue) {
        Pages.searchFilterPage().setSingleValueSetting(PHONE_NUMBER, settingValue);
    }

    @Given("I set From Phone Number to ($settingValue) on the Search Filter page")
    public void iSetFromPhoneNumber(String settingValue) {
        Pages.searchFilterPage().setSingleValueSetting(FROM_PHONE_NUMBER, settingValue);
    }

    @Given("I set To Phone Number to ($settingValue) on the Search Filter page")
    public void iSetToPhoneNumber(String settingValue) {
        Pages.searchFilterPage().setSingleValueSetting(TO_PHONE_NUMBER, settingValue);
    }

    @Given("I set IMSI to ($settingValue) on the Search Filter page")
    public void iSetIMSI(String settingValue) {
        Pages.searchFilterPage().setSingleValueSetting(IMSI, settingValue);
    }

    @Given("I set From IMSI to ($settingValue) on the Search Filter page")
    public void iSetFromIMSI(String settingValue) {
        Pages.searchFilterPage().setSingleValueSetting(FROM_IMSI, settingValue);
    }

    @Given("I set To IMSI to ($settingValue) on the Search Filter page")
    public void iSetToIMSI(String settingValue) {
        Pages.searchFilterPage().setSingleValueSetting(TO_IMSI, settingValue);
    }

    @Given("I select Has Event Location on the Search Filter page")
    public void iSelectHasEventLocation() {
        Pages.searchFilterPage().setNoValueSetting(HAS_EVENT_LOCATION);
    }

    @Given("I deselect Has Event Location on the Search Filter page")
    public void iDeselectHasEventLocation() {
        Pages.searchFilterPage().clearSetting(HAS_EVENT_LOCATION);
    }

    @Given("I set Language to ($settingsValues) on the Search Filter page")
    public void iSetLanguage(ExamplesTable settingsValues) {
        Pages.searchFilterPage().setMultiValueSetting(LANGUAGE, ParametersHelper.processExampleTable(settingsValues));
    }

    @Given("I set From Country to ($settingsValues) on the Search Filter page")
    public void iSetFromCountry(ExamplesTable settingsValues) {
        Pages.searchFilterPage().setMultiValueSetting(FROM_COUNTRY, ParametersHelper.processExampleTable(settingsValues));
    }

    @Given("I set To Country to ($settingsValues) on the Search Filter page")
    public void iSetToCountry(ExamplesTable settingsValues) {
        Pages.searchFilterPage().setMultiValueSetting(TO_COUNTRY, ParametersHelper.processExampleTable(settingsValues));
    }

    @Given("I set File to ($settingsValues) on the Search Filter page")
    public void iSetFile(ExamplesTable settingsValues) {
        Pages.searchFilterPage().setMultiValueSetting(FILE, ParametersHelper.processExampleTable(settingsValues));
    }
}