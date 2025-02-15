package ae.pegasus.framework.conditions;

import ae.pegasus.framework.model.G4Entity;
import ae.pegasus.framework.model.Record;
import ae.pegasus.framework.model.UIRecord;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static ae.pegasus.framework.json.JsonConverter.toJsonString;


public class EqualCondition implements ExpectedCondition {

    private List<ExpectedCondition> conditions = new ArrayList<>();
    private Logger log = Logger.getLogger(EqualCondition.class);


    @Override
    public String toString() {
        return conditions.toString();
    }


    void elements(Object obj1, Object obj2) {
        ExpectedCondition equalCondition;
        if (obj1 == null || obj2 == null) {
            equalCondition = new ObjectEqualCondition(obj1, obj2);
            conditions.add(equalCondition);
            return;
        }
        if (!obj1.getClass().equals(obj2.getClass())) {
            equalCondition = new NotEqual();
        }
        if (Collection.class.isAssignableFrom(obj1.getClass())) {
            equalCondition = new SetEqualCondition((Collection)obj1, (Collection)obj2);
            //elements((Collection) obj1, (Collection) obj2);

        } else if (G4Entity.class.isAssignableFrom(obj1.getClass())) {
            equalCondition = new G4EntityEqualCondition<>((G4Entity) obj1, (G4Entity) obj2);

        } else {
            equalCondition = new ObjectEqualCondition(obj1, obj2);
        }
        conditions.add(equalCondition);

    }

    private void elements(Collection collection1, Collection collection2) {
        if (collection1.size() != collection2.size()) {
            conditions.add(new NotEqual());
            return;
        }
        Object[] collection1ToArray = collection1.toArray();
        Object[] collection2ToArray = collection2.toArray();
        Arrays.sort(collection1ToArray);
        Arrays.sort(collection2ToArray);
        for (int i = 0; i < collection1.size() - 1; i++) {
            elements(collection1ToArray[i], collection2ToArray[i]);
        }

    }


    public Boolean check() {
        Boolean isConditionApplied;
        for (ExpectedCondition condition : conditions) {
            isConditionApplied = condition.check();
            if (!isConditionApplied) {
                return isConditionApplied;
            }
        }
        return true;
    }

    private class NotEqual implements ExpectedCondition {

        @Override
        public String toString() {
            return "Stub condition for false";
        }

        public Boolean check() {
            return false;
        }
    }

    private class SetEqualCondition implements ExpectedCondition {
        private Collection set1;
        private Collection set2;

        private SetEqualCondition(Collection set1, Collection set2) {
            this.set1 = set1;
            this.set2 = set2;
        }

        public String toString() {
            return "Collection condition with collection1: " + set1 + " and collection2: " + set2;
        }

        public Boolean check() {
            if ((set1 == null || set1.size() == 0) && (set2 == null || set2.size() == 0)) {
                return true;
            }
            Object[] collection1ToArray = set1.toArray();
            Object[] collection2ToArray = set2.toArray();
            Arrays.sort(collection1ToArray);
            Arrays.sort(collection2ToArray);
            return Arrays.equals(collection1ToArray, collection2ToArray);
        }
    }

    private class ObjectEqualCondition implements ExpectedCondition {
        private Object obj1;
        private Object obj2;

        private ObjectEqualCondition(Object obj1, Object obj2) {
            this.obj1 = obj1;
            this.obj2 = obj2;
        }

        @Override
        public String toString() {
            return "Object condition with object1: " + toJsonString(obj1) + " and object2: " + toJsonString(obj2);
        }

        public Boolean check() {
            if ((obj1 == null) && (obj2 == null)) {
                return true;
            }
            String json1 = toJsonString(obj1);
            String json2 = toJsonString(obj2);

            return json1.equals(json2);
        }
    }

    private class G4EntityEqualCondition<T extends G4Entity> implements ExpectedCondition {
        private T obj1;
        private T obj2;

        private G4EntityEqualCondition(T obj1, T obj2) {
            this.obj1 = obj1;
            this.obj2 = obj2;

        }

        public String toString() {
            return String.format("Compare two G4 entities: %s and %s", toJsonString(obj1), toJsonString(obj2));
        }

        public Boolean check() {
            log.debug("Comparing two G4 entities with type:" + obj2.getClass().getName());
            Boolean equals;
            for (Field field : obj2.getClass().getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                Object originalValue;
                Object requestValue;
                field.setAccessible(true);
                log.debug("Checking field with name:" + field.getName());
                try {
                    originalValue = field.get(obj1);
                    log.debug("Value of object 1 is:" + originalValue);
                    requestValue = field.get(obj2);
                    log.debug("Value of object 2 is:" + requestValue);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    throw new AssertionError(e.getMessage());
                }

                // both null or empty string
                if ((originalValue == null || originalValue.equals("")) && (requestValue == null || requestValue.equals(""))) {
                    continue;
                }
                EqualCondition condition = new EqualCondition();
                condition.elements(originalValue, requestValue);
                equals = condition.check();

                if (!equals) {
                    log.warn(String.format("%s.%nNot equaled values: %s and %s", condition, originalValue, requestValue));
                    return false;
                }
            }
            return true;
        }


    }

    private class RecordToUIRecordEqualCondition implements ExpectedCondition {

        private Record record;
        private UIRecord uiRecord;


        public RecordToUIRecordEqualCondition(Record record, UIRecord uiRecord) {
            this.record = record;
            this.uiRecord = uiRecord;
        }

        @Override
        public String toString() {
            return String.format("Comparing Record %s with record parsed from UI %s", record, uiRecord);
        }

        @Override
        public Boolean check() {
            log.debug("Checking records...");
            return record.getTmsi().equals(uiRecord.getTmsi()) &&
                    record.getLanguage().equals(uiRecord.getLanguage()) &&
                    record.getImsi().equals(uiRecord.getImsi()) &&
                    record.getDateAndTime().equals(uiRecord.getDateAndTime()) &&
                    record.getProcessedStatus().equals(uiRecord.getProcessedStatus()) &&
                    record.getOriginalId().equals(uiRecord.getOriginalId()) &&
                    record.getDuration() == uiRecord.getDuration() &&
                    record.getText().equals(uiRecord.getText());
        }
    }
}



