package data_for_entity.data_providers;

import model.InformationRequestPriority;

import java.util.Calendar;

public class RFIDueDateProvider extends DependencyDataProvider {
    
    @Override
    public Object generate(int length) {
        DependencyData dependencyData = getDependencyData();
        Integer priorityNumber = Integer.valueOf(dependencyData.getData("priority").toString());
        InformationRequestPriority priority = InformationRequestPriority.getByNumber(priorityNumber);
        if (priority == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, priority.getDaysSwitch());
        return calendar.getTime();
    }
}
