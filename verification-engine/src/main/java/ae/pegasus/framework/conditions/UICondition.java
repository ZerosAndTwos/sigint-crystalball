package ae.pegasus.framework.conditions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class UICondition implements ExpectedCondition {

    private Condition condition;
    private SelenideElement element;

    public UICondition(Condition condition) {
        this.condition = condition;
    }

    public UICondition element(SelenideElement element) {
        this.element = element;
        return this;
    }

    public String toString() {
        return condition.toString();
    }

    public Boolean check() {
        if (element == null) {
            throw new AssertionError("Cannot check condition due to empty element!");
        }
        try {
            this.element.shouldBe(condition);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
