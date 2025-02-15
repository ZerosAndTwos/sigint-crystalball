package ae.pegasus.framework.conditions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.WebElement;

public class PositionConditions {
    public static final Condition inViewPort = new Condition("in-view-port") {
        @Override
        public boolean apply(WebElement element) {
            return (Boolean)Selenide.executeJavaScript(
                    "var elem = arguments[0],                 " +
                            "  box = elem.getBoundingClientRect(),    " +
                            "  cx = box.left + box.width / 2,         " +
                            "  cy = box.top + box.height / 2,         " +
                            "  e = document.elementFromPoint(cx, cy); " +
                            "for (; e; e = e.parentElement) {         " +
                            "  if (e === elem)                        " +
                            "    return true;                         " +
                            "}                                        " +
                            "return false;                            "
                    , element);
        }
    };
}
