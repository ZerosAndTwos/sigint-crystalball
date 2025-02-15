package ae.pegasus.framework.steps.cbfinder;

import ae.pegasus.framework.utils.ParametersHelper;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.model.ExamplesTable;
import ae.pegasus.framework.pages.Pages;

import static ae.pegasus.framework.constants.cbfinder.CBFinderEntityField.*;

public class UICBFinderCaseSteps {

    @Given("I set Name ($name) for new case in the CB Finder")
    public void iSetNameOfNewCase(String name) {
        Pages.cbFinderNewCasePage().setSingleValueData(NAME, name);
    }

    @Given("I set Description ($description) for new case in the CB Finder")
    public void iSetDescriptionOfNewCase(ExamplesTable description) {
        Pages.cbFinderNewCasePage().setMultiValueData(DESCRIPTION, ParametersHelper.processExampleTable(description));
    }

    @Given("I set Classification ($classification) for new case in the CB Finder")
    public void iSetClassificationOfNewCase(String classification) {
        Pages.cbFinderNewCasePage().setSingleValueData(CLASSIFICATION, classification);
    }

    @Given("I set Organization Units ($orgUnit) for new case in the CB Finder")
    public void iSetOrgUnitsOfNewCase(ExamplesTable orgUnit) {
        Pages.cbFinderNewCasePage().setMultiValueData(ORGANIZATION_UNITS, ParametersHelper.processExampleTable(orgUnit));
    }
}
