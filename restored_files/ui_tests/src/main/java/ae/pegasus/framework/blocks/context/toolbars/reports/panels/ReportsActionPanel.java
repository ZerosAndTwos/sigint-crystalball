package ae.pegasus.framework.blocks.context.toolbars.reports.panels;

import ae.pegasus.framework.blocks.context.toolbars.TableToolbar;
import org.openqa.selenium.By;

public class ReportsActionPanel extends TableToolbar {

    public void clickCreateManualReportBtn() {
        base().$(By.xpath("//button[@click.trigger='manualReportCreationHandler()']")).click();

    }

}
