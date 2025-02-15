package ae.pegasus.framework.pages.reports;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class ReportRecordRow {

    private SelenideElement reportRecordRow;


    public ReportRecordRow(SelenideElement reportRecordRow)  {
        this.reportRecordRow = reportRecordRow;
    }

    public SelenideElement getReportRecordRow() {
        return reportRecordRow;
    }

    public String getToNumber() {
        try {
            return getReportRecordRow()
                    .$(By.xpath(".//div[@textcontent.bind='record.toNumber']")).text();
        } catch (Exception e) {
            return "unknown";
        }
    }

    public String getFromNumber() {
        try {
            return getReportRecordRow()
                    .$(By.xpath(".//div[@textcontent.bind='record.fromNumber']")).text();
        } catch (Exception e) {
            return "unknown";
        }
    }

    public String getDetails() {
        return getReportRecordRow().$("div.sms-text pre").text();
    }

}
