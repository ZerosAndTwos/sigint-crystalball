package ae.pegasus.framework.blocks.common.header.breadcrumb;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class Breadcrumb {

    public SelenideElement getBreadcrumb() {
        return $(By.xpath("//ol[@class='pg-breadcrumb']")).shouldBe(present);
    }

    public SelenideElement getCurrentPath() {
        return getBreadcrumb()
                .$(By.xpath("./li[@class='active']/a")).shouldBe(present);
    }

}
