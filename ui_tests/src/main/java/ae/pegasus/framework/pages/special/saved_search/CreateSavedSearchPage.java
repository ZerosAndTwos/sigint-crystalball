package ae.pegasus.framework.pages.special.saved_search;

public class CreateSavedSearchPage extends BaseSavedSearchPage {

    @Override
    public boolean isPageDisplayed() {
        return getPageTitle().equalsIgnoreCase("x\nCreate Saved Search");
    }
}
