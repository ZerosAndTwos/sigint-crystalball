package ae.pegasus.framework.pages.cbfinder;

import ae.pegasus.framework.constants.cbfinder.TargetAction;
import org.apache.commons.lang.NotImplementedException;

public class CBFinderTargetPage extends CBFinderBasePage {

    public void performAction(TargetAction action) {
        getRightHalfOfHeader().$x(".//pg-dropdown[@actions.bind='actions']").click();
        switch (action) {
            case ADD_TO_REPORT:
                getActionFromDropDownMenu(action.getActionName()).click();
                break;
            default:
                throw new NotImplementedException("Unknown action '" + action.getActionName() + "'");
        }
    }

}
