package ae.pegasus.framework.conditions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import com.codeborne.selenide.impl.WebElementWrapper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class ContinuouslyConditions {

    private static final Logger LOG = Logger.getLogger(ContinuouslyConditions.class);

    public static Condition hiddenFor(final int timeConditionContinuouslyMeetsMS) {
        return new Condition("hiddenFor", true) {
            @Override
            public boolean apply(WebElement element) {
                return checkConditionMeet(hidden, element, timeConditionContinuouslyMeetsMS);
            }
        };
    }

    public static Condition enabledFor(final int timeConditionContinuouslyMeetsMS) {
        return new Condition("enabledFor") {
            @Override
            public boolean apply(WebElement element) {
                return checkConditionMeet(enabled, element, timeConditionContinuouslyMeetsMS);
            }
        };
    }

    private static boolean checkConditionMeet(Condition conditionToMeet, WebElement element, int timeConditionContinuouslyMeetsMS) {
        final int POLLING_PERIOD = 10;
        final int NUMBER_OF_PERIODS = timeConditionContinuouslyMeetsMS / POLLING_PERIOD;
        final int NUMBER_OF_ATTEMPTS = 100;
        int periodNumber = 0;
        int attemptNumber = 1;
        LOG.info("Condition " + conditionToMeet.toString() + " started");
        SelenideElement elementToControl = WebElementWrapper.wrap(element);
        while (periodNumber < NUMBER_OF_PERIODS) {
            try {
                elementToControl.waitUntil(conditionToMeet, 1);
                periodNumber++;
            } catch (UIAssertionError e) {
                periodNumber = 0;
                attemptNumber++;
            }
            if (attemptNumber > NUMBER_OF_ATTEMPTS) {
                LOG.info("Condition " + conditionToMeet.toString() + " failed");
                return false;
            }
            Selenide.sleep(POLLING_PERIOD);
        }
        LOG.info("Condition " + conditionToMeet.toString() + " passed");
        return true;
    }
}