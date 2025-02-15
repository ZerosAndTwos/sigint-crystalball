package ae.pegasus.framework.pages.record_assessment;

import ae.pegasus.framework.pages.basic_pages.api.BasePageWithSearch;

public class OrgUnitRecords extends BasePageWithSearch {

    @Override
    public boolean isPageDisplayed() {
        return getPageTitle().equalsIgnoreCase("OrgUnit Records")
                && super.isPageDisplayed();
    }
}
