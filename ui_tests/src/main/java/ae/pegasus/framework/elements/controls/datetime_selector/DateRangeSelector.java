package ae.pegasus.framework.elements.controls.datetime_selector;

import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.constants.controls.datetime_selector.DateRangeCalendar;
import ae.pegasus.framework.constants.controls.datetime_selector.DateRangePeriod;
import org.apache.commons.lang.NotImplementedException;
import ae.pegasus.framework.utils.PageUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static ae.pegasus.framework.constants.controls.datetime_selector.DateRangeCalendar.FROM;
import static ae.pegasus.framework.constants.controls.datetime_selector.DateRangeCalendar.TO;


public class DateRangeSelector extends CommonDateSelector {

    public DateRangeSelector(SelenideElement periodSelectorBaseElement) {
        super(periodSelectorBaseElement);
    }

    private SelenideElement getCalendarInput(DateRangeCalendar calendar) {
        return getDatePickerBase().$x(".//div[contains(@class,'" + calendar.getPosition() + "')]");
    }

    private void setDateViaInput(SelenideElement input, LocalDateTime dateToSet) {
        PageUtils.clearAndType(input, dateToSet.format(DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm", Locale.ENGLISH)));
    }

    private void setDateViaCalendarSelection(SelenideElement input, LocalDateTime dateToSet) {
        input.$x(".//select[contains(@class ,'month')]").selectOption("December");
        input.$x(".//select[contains(@class ,'year')]").selectOption("2010");
        input.$x(".//td[@class='available'][contains(text(),'15')]").click();
        //input.$x(".//td[contains(@class,'active')]").click();

    }

    private void setDateViaCalendarSelectionto(SelenideElement input, LocalDateTime dateToSet) {
        input.$x(".//select[contains(@class ,'month')]").selectOption("December");
        input.$x(".//select[contains(@class ,'year')]").selectOption("2019");
        input.$x(".//td[@class='available'][contains(text(),'12')]").click();
      //  input.$x(".//td[contains(@class,'active')]").click();

    }
    private void selectDateRangePeriod(DateRangePeriod dateRangePeriod) {
        getDatePickerBase().$x(".//ul/li[@data-range-key='" + dateRangePeriod.getPeriodName() + "']").click();
    }

    private LocalDateTime prepareDate(DateRangeCalendar calendar, final LocalDateTime date) {
        LocalDateTime preparedDate = date;
        switch (calendar) {
            case FROM:
                preparedDate = preparedDate.withHour(0).withMinute(0);
                break;
            case TO:
                preparedDate = preparedDate.withHour(23).withMinute(59);
                break;
            default:
                throw new NotImplementedException("Unknown calendar '" + calendar.toString() + "'");
        }
        return preparedDate;
    }

    public void setDateRangePeriod(DateRangePeriod dateRangePeriod) {
        expand();
        selectDateRangePeriod(dateRangePeriod);
        applyDate();
        collapse();
    }

    public void setOneDate(DateRangeCalendar calendar, final LocalDateTime date) {
        expand();
        //selectDateRangePeriod(CUSTOM);
        setDateViaInput(getCalendarInput(calendar), prepareDate(calendar, date));
        applyDate();
        collapse();
    }

    public void setRange(LocalDateTime dateFrom, final LocalDateTime dateTo) {
        expand();
        //selectDateRangePeriod(CUSTOM);
        setDateViaCalendarSelection(getCalendarInput(FROM), prepareDate(FROM, dateFrom));
        setDateViaCalendarSelectionto(getCalendarInput(TO), prepareDate(TO, dateTo));
      //  applyDate();
       // collapse();
    }
}
