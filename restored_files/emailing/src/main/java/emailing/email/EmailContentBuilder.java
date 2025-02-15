package emailing.email;

import ae.pegasus.framework.app_context.properties.G4Properties;
import ae.pegasus.framework.reporter.ReportResults;
import app_context.properties.JenkinsProperties;
import emailing.html_elements.ElementsFabric;
import emailing.html_elements.HtmlBuilder;
import emailing.html_elements.HtmlElement;
import emailing.html_elements.Style;
import failure_strategy.Statistic;
import org.apache.log4j.Logger;

import java.net.URI;
import java.net.URISyntaxException;

public abstract class EmailContentBuilder {
    
    private ReportResults results = new Statistic().getResults();
    protected static JenkinsProperties connection = G4Properties.getJenkinsProperties();
    private String stand;
    private Logger logger = Logger.getLogger(EmailContentBuilder.class);
    
    public EmailContentBuilder(String stand) {
        this.stand = stand;
    }
    
    public String getStand() {
        return stand;
    }
    
    protected abstract HtmlElement buildStatusHeader();
    protected abstract void buildBody(HtmlBuilder builder, ReportResults results);
    protected abstract String buildSubject();
    
    private HtmlElement buildStatistic(HtmlElement tableStatistic, ReportResults results) {
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
        String reportUrl = null;
        try {
            URI uri = new URI(connection.getHost(), String.format("/job/%s/allure", getStand()), null);
            reportUrl = uri.toASCIIString();
        } catch (URISyntaxException e) {
            logger.debug("Error occurred while encoding URL string. Empty url will be used");
        }
        tableRowReportLink.addChild(ElementsFabric.tableColumn("", null).addChild(
                ElementsFabric.link(reportUrl, "report link")));
        tableStatistic.addChild(tableRowReportLink);
        return null;
    }
    
    public String buildBody() {
        HtmlBuilder builder = new HtmlBuilder();
        HtmlElement tableStatistic = ElementsFabric.table(new Style().setBorder("2"));
        tableStatistic.addChild(buildStatusHeader());
        buildStatistic(tableStatistic, results);
        builder.addHtmlElement(tableStatistic);
        buildBody(builder, results);
        return builder.build();
        
    }
}
