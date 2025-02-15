package pages.blocks.tables.toolbar;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public abstract class TableToolbar {

    public SelenideElement getToolbar() {
        return $(By.xpath(".//div[contains(@class, 'pg-table')]" +
                "/div[@class='pg-toolbar']"));
    }

}
