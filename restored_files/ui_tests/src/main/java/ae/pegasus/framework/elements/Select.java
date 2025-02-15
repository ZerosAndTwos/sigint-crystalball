package ae.pegasus.framework.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Select {

    public SelenideElement getElement() {
        return element;
    }

    public void setElement(SelenideElement element) {
        this.element = element;
    }

    protected SelenideElement element;

    public Select(SelenideElement element) {
        this.element = element;
    }

    public ElementsCollection getOptions() {
        return element.$$("div.tagger-options-list > tagger-option").shouldHave(sizeGreaterThan(0));
    }




    public void selectOptionByText(String option) {
        element.click();
            ElementsCollection options = getOptions();
            for (SelenideElement selenideElement: options) {
                if(selenideElement.getText().equalsIgnoreCase(option)) {
                    navAndClickOption(selenideElement);
                    return;
            }
           
        }
        throw new AssertionError(String.format("Option with text %s was not found!", option));
            }


    public void selectOptionbyValue(String option) {
        element.click();
        try {
            SelenideElement foundOption = getOptions().findBy(Condition.attribute("value", option));
            navAndClickOption(foundOption);
        }catch (IndexOutOfBoundsException e) {
            throw new AssertionError(String.format("There is no available option to click with name %s!", option));
        }
    }

    protected void navAndClickOption(SelenideElement option) {
        new Actions(getWebDriver()).moveToElement(option.getWrappedElement()).click().perform();
       
    }
}
