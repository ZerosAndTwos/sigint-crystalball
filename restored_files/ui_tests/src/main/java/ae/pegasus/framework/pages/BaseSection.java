package ae.pegasus.framework.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public abstract class BaseSection {

    private String base;


    public SelenideElement base() {
        return $(base);
    }

    public BaseSection(String base) {
        this.base = base;
    }


    public SelenideElement getLoading() {
        return base().$(By.xpath("//span[@class='inline-block' and contains(.,'LoadingΓÇª')]"));
    }

}
