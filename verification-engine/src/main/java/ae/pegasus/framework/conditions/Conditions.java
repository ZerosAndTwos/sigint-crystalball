package ae.pegasus.framework.conditions;

import org.apache.log4j.Logger;

public class Conditions {
    
    private static Logger logger = Logger.getLogger(Conditions.class);

    public static  EqualCondition equals(Object element1, Object element2) {
        logger.debug(String.format("Comparing two elements: %s and %s", element1, element2));
        EqualCondition condition = new EqualCondition();
        condition.elements(element1, element2);
        return condition;
    }

    public static TrueCondition isTrue = new TrueCondition();
}
