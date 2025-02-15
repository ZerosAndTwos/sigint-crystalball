package ae.pegasus.framework.pages.records;

import ae.pegasus.framework.blocks.context.Context;
import ae.pegasus.framework.elements.Select;
import ae.pegasus.framework.pages.SigintPage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class RecordsCreatePage extends SigintPage {

    private SelenideElement form() {
        return $("form.pg-form");
    }

    public SelenideElement getColumnByNumber(int numberCol) {
        return form()
                .$(By.xpath(".//div[@class='col-xs-6'][" + numberCol + "]"));
    }

    public SelenideElement getHeaderTitle() {
        return $(By.xpath("//div[@class='pg-header']" +
                "//div[@class='pg-header__col pg-header__title']"));
    }

    public ElementsCollection getLabels() {
        return form()
                .$$(By.xpath(".//div[@class='col-xs-6']" +
                "//div[contains(@class, 'form-group')]" +
                "//label"));
    }

    public SelenideElement getLabelByText(String text) {
        return form()
                .$(By.xpath(".//div[@class='col-xs-6']" +
                "//div[contains(@class, 'form-group')]" +
                "//label[contains(., '" + text + "')]"));
    }

    public Select getSource() {
        return new Select(form().$(By.xpath("//selector[@source.bind='texts']/div")));
    }


    public RecordsCreatePage selectSource(String source) {
        getSource().selectOptionByText(source);
        return this;
    }


    public SelenideElement getFromNumber() {
        return form()
                .$(By.xpath(".//div[@class='col-xs-6']" +
                        "//div[contains(@class, 'form-group')]" +
                        "//input[@value.two-way='item.fromNumber']"));
    }

    public RecordsCreatePage typeFromNumber(String fromNumber) {
        getFromNumber().val(fromNumber);
        return this;
    }

    public Select getFromCountry() {
        return new Select(form().$(By.xpath(".//country-tagger[@selected.two-way='item.fromCountry']" +
                "/selector/div")));
    }

    public RecordsCreatePage selectFromCountry(String country) {
        getFromCountry().selectOptionByText(country);
        return this;
    }


    public Select getLanguage() {
        return new Select(form()
                .$(By.xpath(".//language-tagger[@selected.two-way='item.language']" +
                        "/selector/div")));

    }

    public RecordsCreatePage selectLanguage(String language) {
        getLanguage().selectOptionByText(language);
        return this;
    }

    public SelenideElement getTmsi() {
        return form()
                .$(By.xpath(".//div[@class='col-xs-6']" +
                "//div[contains(@class, 'form-group')]" +
                "//input[@id='tmsi']"));
    }

    public RecordsCreatePage typeTmsi(String text) {
        getTmsi().val(text);
        return this;
    }

    public SelenideElement getSMSText() {
        return form()
                .$(By.xpath(".//textarea[@value.bind='value']"));
    }

    public RecordsCreatePage typeSMSText(String text) {
        getSMSText().val(text);
        return this;
    }

    public SelenideElement getCreateRecordButton() {
        return form()
                .$(By.xpath(".//button[contains(., 'Create record')]"));
    }

    public void clickCreateRecordButton() {
        getCreateRecordButton().click();
    }

    public SelenideElement getImsi() {
        return form()
                .$(By.xpath(".//div[@class='col-xs-6']" +
                "//div[contains(@class, 'form-group')]" +
                "//input[@id='imsi']"));
    }

    public RecordsCreatePage typeImsi(String text) {
        getImsi().val(text);
        return this;
    }

    public SelenideElement getRecordId() {
        return form()
                .$(By.xpath(".//div[@class='col-xs-6']" +
                "//div[contains(@class, 'form-group')]" +
                "//input[@id='originalId']"));
    }

    public RecordsCreatePage typeRecordId(String text) {
        getRecordId().val(text);
        return this;
    }

    public SelenideElement getToNumber() {
        return form()
                .$(By.xpath(".//div[@class='col-xs-6']" +
                "//div[contains(@class, 'form-group')]" +
                "//input[@value.two-way='item.toNumber']"));
    }

    public RecordsCreatePage typeToNumber(String toNumber) {
        getToNumber().val(toNumber);
        return this;
    }

    public Select getToCountry() {
        return new Select(form()
                .$(By.xpath(".//country-tagger[@selected.two-way='item.toCountry']" +
                        "/selector/div")));
    }

    public RecordsCreatePage selectToCountry(String country) {
        getToCountry().selectOptionByText(country);
        return this;
    }

    public SelenideElement getSMSRecordType() {
        return form()
                .$(By.xpath(".//div[@class='col-xs-6']" +
                "//div[contains(@class, 'form-group')]" +
                "//label[contains(@class.bind, 'SMS')]"));
    }

    public RecordsCreatePage clickSMSRecordType() {
        getSMSRecordType().click();
        return this;
    }

    public SelenideElement getCallRecordType() {
        return form()
                .$(By.xpath(".//div[@class='col-xs-6']" +
                "//div[contains(@class, 'form-group')]" +
                "//label[contains(@class.bind, 'Voice')]"));
    }

    public RecordsCreatePage clickCallRecordType() {
        getCallRecordType().click();
        return this;
    }

    public SelenideElement getCallDuration() {
        return form()
                .$(By.xpath(".//div[@class='col-xs-6']" +
                "//div[contains(@class, 'form-group')]" +
                "//input[@id='duration']"));
    }

    public SelenideElement getDataAndTime() {
        return  form()
                .$("date#datetime > input");
    }
    //".//div[@class='col-xs-6'][2]" +
    //        "//div[contains(@class, 'form-group')][1]" +
    //        "//input"


    public RecordsCreatePage clickCalendarApplyButton() {
        $$("div.daterangepicker button.applyBtn")
                .filter(visible)
                .get(0)
                .click();
        return this;
    }

    public RecordsCreatePage typeDataAndTime(Date date) {
        getDataAndTime().val(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(date));
        clickCalendarApplyButton();
        return this;
    }

    public RecordsCreatePage typeCallDuration(int duration) {
        getCallDuration().val(Integer.toString(duration));
        return this;
    }

    @Override
    public Context context() {
        return null;
    }
}
