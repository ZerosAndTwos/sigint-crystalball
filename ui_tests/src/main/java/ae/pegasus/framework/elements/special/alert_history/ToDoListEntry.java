package ae.pegasus.framework.elements.special.alert_history;

import ae.pegasus.framework.utils.TimeUtils;
import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.elements.BasicElement;

import java.time.LocalDateTime;

public class ToDoListEntry extends BasicElement {

    public ToDoListEntry(SelenideElement wrappedElement) {
        super(wrappedElement);
    }

    public String getTitle() {
        return $x("//div[@class='group-title__wrap']/span").getText().trim();
    }

    public LocalDateTime getDate() {
        return TimeUtils.convertToLocalDateTime(getTitle().replace("- Today", "").trim(), "dd MMMM yyyy");
    }

    private SelenideElement getRow(int rowIndex) {
        return $x(".//notifications-list/div[" + rowIndex + "]");
    }

    public String getRowText(int rowIndex) {
        return getRow(rowIndex).$x(".//alerting-message").getText();
    }

    public LocalDateTime getRowDate(int rowIndex) {
        return TimeUtils.convertToLocalDateTime(getRow(rowIndex).$x(".//div[@class='list-item__content']/div").getText(), "dd/MM/yyyy HH:mm");
    }
}
