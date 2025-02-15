package ae.pegasus.framework.conditions;

import com.codeborne.selenide.Condition;

public class UIConditions {

    public static UICondition disappear = new UICondition(Condition.disappear);
    public static UICondition present = new UICondition(Condition.present);
    public static UICondition attribute(String attrName, String attrvalue) {
        return new UICondition(Condition.attribute(attrName, attrvalue));}
}
