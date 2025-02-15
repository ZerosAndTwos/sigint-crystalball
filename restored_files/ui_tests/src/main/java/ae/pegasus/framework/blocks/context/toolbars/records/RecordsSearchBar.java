package ae.pegasus.framework.blocks.context.toolbars.records;

import ae.pegasus.framework.blocks.context.toolbars.SearchToolbar;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class RecordsSearchBar extends SearchToolbar {


    private void clickExpandOnCondition(String condition) {
        SelenideElement expButton = base().$(By.xpath("//a[@click.trigger='toggleCollapseSearchForm()']"));
        if (expButton.getAttribute("title").equalsIgnoreCase(condition)) {
            expButton.click();
        }
    }

    public void expandSearchPanel() {
        clickExpandOnCondition("show");
    }

    public void hideSearchPanel() {
        clickExpandOnCondition("hide");
    }
}
