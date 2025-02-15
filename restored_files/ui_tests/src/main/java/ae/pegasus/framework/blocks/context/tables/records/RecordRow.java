package ae.pegasus.framework.blocks.context.tables.records;

import ae.pegasus.framework.blocks.context.tables.Row;
import ae.pegasus.framework.pages.records.RecordDetailsDialog;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.page;

public class RecordRow extends Row {

    public RecordRow(SelenideElement row) {
        super(row);
    }

    public SelenideElement getCreateReportButton() {
        return row
                .$(By.xpath(".//button[@title='Create a Report']"));
    }

    public RecordDetailsDialog clickCreateReportButton() {
        getCreateReportButton().hover().click();
        return page(RecordDetailsDialog.class);
    }

    public SelenideElement getRecordDescription() {
        return row
                .$(By.xpath(".//record-description-decorator"));
    }

    public String getDetails() {
        return getCellByColumnName("Details").text();
    }

    public String getDate() {
        return getCellByColumnName("Date").text();
    }

    private String   getTextMatcher(String which, Pattern pattern) {
        getRecordDescription().$("div[ref='mainView']").click();
        String controlText = getRecordDescription().$("div.popover").text();
        getCellByColumnName("TMSI").hover();
        Matcher matcher = pattern.matcher(controlText);
        matcher.find();
        return matcher.group().replace(which, "").replace("\n","");

    }

    private String getTextDescriptionForm(String which, String regexp) {
        Pattern pattern = Pattern.compile(regexp);
        return getTextMatcher(which, pattern);

    }

    private String getTextDescriptionForm(String which) {
        Pattern pattern = Pattern.compile(String.format("%s\n[a-zA-Z'\\s]*", which));
        return getTextMatcher(which, pattern);

    }

    public String getType() {
        return getTextDescriptionForm("Type", "Type\n[a-zA-Z]*");

    }

    public String getFromCountry() {
        return getTextDescriptionForm("From");
    }

    public String getToCountry() {
        return getTextDescriptionForm("To");
    }

    public String getTMSI() {
        return getCellByColumnName("TMSI").text();
    }

    public String getIMSI() {
        return getCellByColumnName("IMSI").text();
    }

    public String getLanguage() {
        return getCellByColumnName("Language").text();
    }

    public String getToNumber() {
        return getRecordDescription()
                .$(By.xpath(".//div[2]/span[@class='au-target fx1']")).text();
    }

    public String getFromNumber() {
        return getRecordDescription()
                .$(By.xpath(".//div[1]/span[@class='au-target fx1']")).text();
    }

    public String getStatus() {
        return getCellByColumnName("Status")
                .$(By.xpath(".//i")).attr("title");
    }

    public String getRecordID() {
        return getCellByColumnName("Record ID").text();
    }

}
