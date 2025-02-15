package html_email;

import html_email.html_elements.ElementsFabric;
import html_email.html_elements.HtmlBuilder;
import html_email.html_elements.HtmlElement;
import html_email.html_elements.Style;
import model.AppContext;
import org.apache.log4j.Logger;
import reporter.ReportResults;
import reporter.TestCase;

import java.util.Properties;

public class HtmlEmail {

    private static Properties connection = AppContext.getContext().getJiraConnection();
    private static Logger log = Logger.getLogger(HtmlEmail.class);

    public String buildHtml(ReportResults results) {
        log.debug("Start building email...");
        HtmlBuilder builder = new HtmlBuilder();
        HtmlElement tableStatistic = ElementsFabric.table(new Style().setBorder("2"));
        HtmlElement tableRowStatus = ElementsFabric.tableRow();
        tableRowStatus.addChild(ElementsFabric.tableColumn("", new Style().setAlign("right")).
                       addChild(ElementsFabric.image(connection.getProperty("jenkins")+"/static/e59dfe28/images/32x32/red.gif")));
        tableRowStatus.addChild(ElementsFabric.tableColumn("", new Style().setValign("center")).
                       addChild(ElementsFabric.bold("BUILD FAILURE", new Style().setFontSize("200%"))));
        tableStatistic.addChild(tableRowStatus);
        HtmlElement tableRowTotal = ElementsFabric.tableRow();
        tableRowTotal.addChild(ElementsFabric.tableColumn("Total tests executed:", null));
        tableRowTotal.addChild(ElementsFabric.tableColumn(Integer.toString(results.getTotalRun()), null));
        tableStatistic.addChild(tableRowTotal);
        HtmlElement tableRowPassed = ElementsFabric.tableRow();
        tableRowPassed.addChild(ElementsFabric.tableColumn("Total tests passed:", null));
        tableRowPassed.addChild(ElementsFabric.tableColumn(Integer.toString(results.getPassed().size()), null));
        tableStatistic.addChild(tableRowPassed);
        HtmlElement tableRowFailed = ElementsFabric.tableRow();
        tableRowFailed.addChild(ElementsFabric.tableColumn("Total tests failed:", null));
        tableRowFailed.addChild(ElementsFabric.tableColumn(Integer.toString(results.getFailed().size()), null));
        tableStatistic.addChild(tableRowFailed);
        HtmlElement tableRowReportLink = ElementsFabric.tableRow();
        tableRowReportLink.addChild(ElementsFabric.tableColumn("View detailed report:", null));
        tableRowReportLink.addChild(ElementsFabric.tableColumn("", null).addChild(
                ElementsFabric.link(connection.getProperty("jenkins")+"/job/Tests/allure", "report link")));
        tableStatistic.addChild(tableRowReportLink);
        builder.addHtmlElement(tableStatistic);
        HtmlElement failedTitle = ElementsFabric.heading("Failed tests:", new Style().setFontSize("200%"));
        builder.addHtmlElement(failedTitle);
        HtmlElement failedTable = ElementsFabric.table(null);
        for (TestCase failedTest:results.getFailed()) {
            HtmlElement titleRow = ElementsFabric.tableRow();
            titleRow.addChild(ElementsFabric.tableColumn(failedTest.getTitle(), new Style().setFontWeight("bold")));
            titleRow.addChild(ElementsFabric.tableColumn("", null).addChild(ElementsFabric.link(failedTest.getUrl(),
                    "Open scenario")));
            HtmlElement passedStepsRow = ElementsFabric.tableRow();
            passedStepsRow.addChild(ElementsFabric.tableColumn(failedTest.getStepsNames(failedTest.getPassedSteps()),
                    new Style().setColor("green")));
            HtmlElement failedStepsRow = ElementsFabric.tableRow();
            failedStepsRow.addChild(ElementsFabric.tableColumn(failedTest.getStepsNames(failedTest.getFailedSteps()),
                    new Style().setColor("red")));
            failedTable.addChild(titleRow);
            failedTable.addChild(passedStepsRow);
            failedTable.addChild(failedStepsRow);
        }
        builder.addHtmlElement(failedTable);

        return builder.build();
    }
}
