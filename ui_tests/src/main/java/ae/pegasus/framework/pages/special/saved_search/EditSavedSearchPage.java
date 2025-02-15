package ae.pegasus.framework.pages.special.saved_search;

public class EditSavedSearchPage extends BaseSavedSearchPage {

    @Override
    public boolean isPageDisplayed() {
        return getPageTitle().equalsIgnoreCase("Edit Saved Search");
    }
}