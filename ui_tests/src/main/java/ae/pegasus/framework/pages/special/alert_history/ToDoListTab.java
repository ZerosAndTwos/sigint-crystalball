package ae.pegasus.framework.pages.special.alert_history;

import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.elements.special.alert_history.ToDoListEntry;

import java.time.LocalDateTime;

import static com.codeborne.selenide.Selenide.$$x;
import static ae.pegasus.framework.constants.special.alert_history.AlertHistoryTab.TO_DO_LIST;

public class ToDoListTab extends AlertHistoryBasePage {

    public ToDoListEntry getListEntryForDate(LocalDateTime dateOfEntry) {
        openAlertHistoryTab(TO_DO_LIST);
        waitForPageLoading();
        for (SelenideElement entry : $$x("//pg-listbox")) {
            ToDoListEntry resultEntry = new ToDoListEntry(entry);
            if (resultEntry.getDate().toLocalDate().isEqual(dateOfEntry.toLocalDate())) {
                return resultEntry;
            }
        }
        throw new IllegalArgumentException("To Do List entry with date '" + dateOfEntry.toString() + "' was not found");
    }
}
