package ae.pegasus.framework.steps.cbfinder;

import ae.pegasus.framework.utils.ParametersHelper;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.model.ExamplesTable;
import ae.pegasus.framework.pages.Pages;

import static ae.pegasus.framework.constants.cbfinder.CBFinderEntityField.*;

public class UICBFinderFileSteps {

    @Given("I set Name ($name) for new file in the CB Finder")
    public void iSetNameOfNewFile(String name) {
        Pages.cbFinderNewFilePage().setSingleValueData(NAME, name);
    }

    @Given("I set Description ($description) for new file in the CB Finder")
    public void iSetDescriptionOfNewFile(ExamplesTable description) {
        Pages.cbFinderNewFilePage().setMultiValueData(DESCRIPTION, ParametersHelper.processExampleTable(description));
    }

    @Given("I set Classification ($classification) for new file in the CB Finder")
    public void iSetClassificationOfNewFile(String classification) {
        Pages.cbFinderNewFilePage().setSingleValueData(CLASSIFICATION, classification);
    }

    @Given("I set Organization Units ($orgUnit) for new file in the CB Finder")
    public void iSetOrgUnitsOfNewFile(ExamplesTable orgUnit) {
        Pages.cbFinderNewFilePage().setMultiValueData(ORGANIZATION_UNITS, ParametersHelper.processExampleTable(orgUnit));
    }
}
