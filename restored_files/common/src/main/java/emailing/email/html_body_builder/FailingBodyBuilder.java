package emailing.email.html_body_builder;


import emailing.html_elements.ElementsFabric;
import emailing.html_elements.HtmlBuilder;
import emailing.html_elements.HtmlElement;
import emailing.html_elements.Style;
import reporter.ReportResults;
import reporter.TestCase;

public class FailingBodyBuilder extends  EmailBodyBuilder {
    
    @Override
    protected HtmlElement buildStatusHeader() {
        HtmlElement tableRowStatus = ElementsFabric.tableRow();
        tableRowStatus.addChild(ElementsFabric.tableColumn("", new Style().setAlign("right")).
                addChild(ElementsFabric.image(connection.getProperty("jenkins")+"/static/e59dfe28/images/32x32/red.gif")));
        tableRowStatus.addChild(ElementsFabric.tableColumn("", new Style().setValign("center")).
                addChild(ElementsFabric.bold("BUILD FAILURE", new Style().setFontSize("200%"))));
        return tableRowStatus;
    }
    
    @Override
    protected void buildBody(HtmlBuilder builder, ReportResults results) {
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
    }
}
