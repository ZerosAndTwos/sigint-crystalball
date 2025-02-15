package ae.pegasus.framework.conditions;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebElement;

public class AttributesConditions {
    public static Condition attributeContainsValue(final String attributeName, final String expectedAttributeValue) {
        return new Condition("attributeContainsValue") {
            @Override
            public boolean apply(WebElement element) {
                return getAttributeValue(element, attributeName).contains(expectedAttributeValue);
            }
            @Override
            public String toString() {
                return name + " " + attributeName + '=' + expectedAttributeValue;
            }
        };
    }

    private static String getAttributeValue(WebElement element, String attributeName) {
        String attr = element.getAttribute(attributeName);
        return attr == null ? "" : attr;
    }}
