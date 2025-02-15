package ae.pegasus.framework.elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;

public class ReportSelect extends Select {

    public ReportSelect(SelenideElement element) {
        super(element);
    }

    @Override
    public ElementsCollection getOptions() {
        element.click();
        return element.$$(By.xpath(".//option[contains(@value.bind, 'r.id') or " +
                "contains(@value.bind, 's.id')]")).shouldHave(sizeGreaterThan(0));
    }
    
    @Override
    protected void navAndClickOption(SelenideElement option) {
        option.scrollTo().click();
        
    }
    
}
