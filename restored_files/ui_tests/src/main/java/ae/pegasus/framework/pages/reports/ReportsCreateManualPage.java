package ae.pegasus.framework.pages.reports;

import ae.pegasus.framework.blocks.context.Context;
import ae.pegasus.framework.app_context.properties.G4Properties;
import ae.pegasus.framework.elements.ReportSelect;
import ae.pegasus.framework.elements.Select;
import org.openqa.selenium.By;

public class ReportsCreateManualPage extends ReportsPage {

    public static final String url = String.format("%s/#/app/reports/create-manual",
            G4Properties.getRunProperties().getApplicationURL());

    @Override
    public Context context() {
        return null;
    }


    public Select getRecordTypeSelect() {
        return new ReportSelect(getForm()
                .$(By.xpath("//select[@value.bind='record.type']")));
    }

    public Select getSourceSelect() {
        return new ReportSelect(getForm()
                .$(By.xpath(".//select[@value.bind='record.sourceId']")));
    }

    public ReportsCreateManualPage selectRecordType(String recordType) {
        getRecordTypeSelect().selectOptionbyValue(recordType);
        return this;
    }

    public ReportsCreateManualPage selectSourceType(String sourceType) {
        getSourceSelect().selectOptionByText(sourceType);
        return this;
    }
}
