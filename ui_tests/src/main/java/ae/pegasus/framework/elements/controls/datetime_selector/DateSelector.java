package ae.pegasus.framework.elements.controls.datetime_selector;

import com.codeborne.selenide.SelenideElement;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateSelector extends CommonDateSelector {

    private final boolean applyRequired;

    public DateSelector(SelenideElement dateTimeBaseElement) {
        this(dateTimeBaseElement, true);
    }

    public DateSelector(SelenideElement dateTimeBaseElement, boolean applyRequired) {
        super(dateTimeBaseElement);
        this.applyRequired = applyRequired;
    }

    private SelenideElement getCalendarBase() {
        return getDatePickerBase().$x(".//div[contains(@class,'left')]//div[@class='calendar-table']");
    }

    private SelenideElement getMonthSelector() {
        return getCalendarBase().$x(".//select[@class='monthselect']");
    }

    private void setMonth(String monthFullName) {
        getMonthSelector().click();
        getMonthSelector().$x(".//option[text()='" + monthFullName + "']").click();
    }

    private SelenideElement getYearSelector() {
        return getCalendarBase().$x(".//select[@class='yearselect']");
    }

    private void setYear(int year) {
        getYearSelector().click();
        getYearSelector().$x(".//option[text()='" + year + "']").click();
    }

    private void setDay(int day) {
        getCalendarBase().$x(".//td[contains(@class,'available') and not(contains(@class, 'off')) and text()='" + day +"']").click();
    }

    public void setDate(LocalDateTime date) {
        expand();
        setYear(date.getYear());
        setMonth(date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        setDay(date.getDayOfMonth());
        if (applyRequired) {
           // applyDate();
            //collapse();
        }
    }
}
