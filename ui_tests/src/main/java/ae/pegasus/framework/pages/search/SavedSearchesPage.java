package ae.pegasus.framework.pages.search;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.constants.search.saved_searches.SavedSearchesActionButton;
import ae.pegasus.framework.constants.special.modal_dialog.ModalDialogButton;
import ae.pegasus.framework.pages.Pages;
import ae.pegasus.framework.pages.basic_pages.api.BasePageWithSearch;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static ae.pegasus.framework.constants.search.saved_searches.SavedSearchesActionButton.*;

public class SavedSearchesPage extends BasePageWithSearch {

    private final String SAVED_SEARCH_CARD_XPATH = "//saved-search-card";

    @Override
    public boolean isPageDisplayed() {
        return getPageTitle().equalsIgnoreCase("Saved Searches")
                && getSearchBlock().isDisplayed()
                && getSortByBlock().isDisplayed();
    }

    private ElementsCollection getCards() {
        return $$x(SAVED_SEARCH_CARD_XPATH);
    }

    private SelenideElement get1stCard() {
        return $x(SAVED_SEARCH_CARD_XPATH);
    }

    public int getCardsNumber() {
        return getCards().size();
    }

    private SelenideElement getOpenDropDownMenu(SelenideElement card) {
        return card.$x(".//pg-dropdown");
    }

    private void performActionOnCard(SavedSearchesActionButton action, SelenideElement card) {
        getOpenDropDownMenu(card).click();
        getActionFromDropDownMenu(action.getActionName()).click();
    }

    private void deleteCard(SelenideElement card) {
        closeAllPopUps();
        performActionOnCard(DELETE, card);
        Pages.modalDialogPage().clickButton(ModalDialogButton.DELETE);
        getSuccessPopUp().shouldBe(visible);
    }

    public void edit1stCard() {
        performActionOnCard(EDIT, get1stCard());
    }

    public void run1stCard() {
        performActionOnCard(RUN, get1stCard());
    }

    public void deleteAllCards() {
        SelenideElement card = get1stCard();
        while (card.isDisplayed()) {
            deleteCard(card);
            waitForPageLoading();
            card = get1stCard();
        }
    }


}
