package ae.pegasus.framework.pages.special.search_results;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.constants.search.search.SearchActionButton;
import ae.pegasus.framework.constants.special.modal_dialog.ModalDialogButton;
import ae.pegasus.framework.constants.special.search_results.SearchResultsEntity;
import ae.pegasus.framework.constants.special.search_results.SearchResultsEvent;
import ae.pegasus.framework.pages.Pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static ae.pegasus.framework.constants.search.search.SearchActionButton.DELETE;
import static com.codeborne.selenide.Selenide.$x;

public class SearchResultsAsCardsPage extends SearchResultsPage {

    private final String IDENTIFIER_FROM_TITLE_XPATH = ".//div[@class='media-body']/strong";
    private final String GOVINT_VALUE_XPATH = ".//div[@class='pg-card__content']//pg-col/span";
    private final String PLAIN_TEXT_IDENTIFIERS_XPATH = ".//div[@class='pg-card__content']//div[contains(@class,'para')]/span";
    private final String ONE_FROM_LIST_IDENTIFIERS_XPATH = ".//div[@class='pg-card__content']//cb-identifier/span";
    private final String FINTRAF_VALUE_XPATH = ".//div[@class='pg-card__content']//cb-render-template";
    private ElementsCollection getListOfCards() {
        return $$x(CARD_XPATH);
    }
    private final String SEARCH_SELECT_ALL = " Select All ";

    private List<SelenideElement> getAllCardsWithIdentifiers(List<String> xPathsToIdentifiers, String...identifiersValues) {
        if (identifiersValues.length < 1) {
            throw new IllegalArgumentException("There is at least one identifier expected");
        }

        List<SelenideElement> result = new ArrayList<>();

        for(SelenideElement card : getListOfCards()) {
            List<String> identsValList = new ArrayList<>();
            identsValList.addAll(Arrays.asList(identifiersValues));
            for (String xPath : xPathsToIdentifiers) {
                if (!xPath.startsWith(".")) {
                    xPath = "." + xPath;
                }
                for (SelenideElement identifier : card.$$x(xPath)) {
                    String matchedIdent = null;
                    for (String identVal : identsValList) {
                        if (identifier.getText().trim().equalsIgnoreCase(identVal.trim())) {
                            matchedIdent = identVal;
                            break;
                        }
                    }
                    if (matchedIdent != null) {
                        identsValList.remove(matchedIdent);
                    }
                    if (identsValList.isEmpty()) {
                        result.add(card);
                        break;
                    }
                }
                if (identsValList.isEmpty()) {
                    //To the next card
                    break;
                }
            }
        }
        return result;
    }

    private void selectAllCards(List<SelenideElement> cardsToSelect) {
        if (cardsToSelect.isEmpty()) {
            throw new IllegalArgumentException("None cards for select");
        }
        for (SelenideElement card : cardsToSelect) {
            selectCard(card);
        }
    }

    private void selectCard(SelenideElement cardToSelect) {
        scrollWindowTo(cardToSelect);
        cardToSelect.$x(".//div[@class='pg-card__select']").click();
    }

    private void applyActionToCard(SearchActionButton actionToApply, SelenideElement cardToApplyAction) {
        scrollWindowTo(cardToApplyAction);
        cardToApplyAction.$x(".//pg-dropdown").click();
        getActionFromDropDownMenu(actionToApply.getActionName()).click();
    }

    private List<String> getXPathsToIdentifiers(SearchResultsEvent event) {
        List<String> xPathsToIdents = new ArrayList<>();
        switch (event) {
            case SIGINT:
                xPathsToIdents.add(ONE_FROM_LIST_IDENTIFIERS_XPATH);
                xPathsToIdents.add(PLAIN_TEXT_IDENTIFIERS_XPATH);
                break;
            case EID:
                xPathsToIdents.add(PLAIN_TEXT_IDENTIFIERS_XPATH);
                xPathsToIdents.add(ONE_FROM_LIST_IDENTIFIERS_XPATH);
                xPathsToIdents.add(".//div[@class='pg-card__content']//pg-row/pg-col/div/span");
                break;
            case GOVINT:
                xPathsToIdents.add(GOVINT_VALUE_XPATH);
                xPathsToIdents.add(PLAIN_TEXT_IDENTIFIERS_XPATH);
                xPathsToIdents.add(ONE_FROM_LIST_IDENTIFIERS_XPATH);
                break;
            case OSINT:
                xPathsToIdents.add(IDENTIFIER_FROM_TITLE_XPATH);
                xPathsToIdents.add(ONE_FROM_LIST_IDENTIFIERS_XPATH);
                xPathsToIdents.add(".//div[@class='para']/span");
                break;
            case FININT:
                xPathsToIdents.add(FINTRAF_VALUE_XPATH);
            case TRAFFIC:
                xPathsToIdents.add(FINTRAF_VALUE_XPATH);


                break;
            default:
                throw new IllegalArgumentException("Not implemented for '" + event.getDisplayName() + "' event");
        }
        return xPathsToIdents;
    }

    private List<String> getXPathsToIdentifiers(SearchResultsEntity entity) {
        List<String> xPathsToIdents = new ArrayList<>();
        switch (entity) {
            case SIGINT:
                xPathsToIdents.add(".//pg-phone-number/span");
                xPathsToIdents.add(".//pg-country/span");
                xPathsToIdents.add(".//cb-identifier-value/span/span");
                xPathsToIdents.add(".//div[@class='media-body']/span");
                break;
            case EID:
                xPathsToIdents.add(PLAIN_TEXT_IDENTIFIERS_XPATH);
                xPathsToIdents.add(ONE_FROM_LIST_IDENTIFIERS_XPATH);
                break;
            case GOVINT:
                xPathsToIdents.add(GOVINT_VALUE_XPATH);
                xPathsToIdents.add(ONE_FROM_LIST_IDENTIFIERS_XPATH);
                xPathsToIdents.add(PLAIN_TEXT_IDENTIFIERS_XPATH);
                xPathsToIdents.add(".//div[@class='media-body']/div[contains(@class, 'bold')]");
                break;
            case OSINT:
                xPathsToIdents.add(".//div[@class='pg-card__header']//cb-identifier/span");
                xPathsToIdents.add(IDENTIFIER_FROM_TITLE_XPATH);
                xPathsToIdents.add(".//div[@class='pg-card__content']//a");
                xPathsToIdents.add(".//div[@class='pg-card__footer']/div");
                break;
            case PROFILER:
                xPathsToIdents.add(IDENTIFIER_FROM_TITLE_XPATH);
                xPathsToIdents.add(".//profile-category-tag");
                break;
            default:
                throw new IllegalArgumentException("Not implemented for '" + entity.getDisplayName() + "' entity");
        }
        return xPathsToIdents;
    }

    public void selectAllCardsWithIdentifiers(SearchResultsEvent event, String...identifiersValues) {
        openSearchResultsForCategory(event);
        selectAllCards(getAllCardsWithIdentifiers(getXPathsToIdentifiers(event), identifiersValues));
    }

    public void selectAllCardsWithIdentifiers(SearchResultsEntity entity, String...identifiersValues) {
        openSearchResultsForCategory(entity);
        selectAllCards(getAllCardsWithIdentifiers(getXPathsToIdentifiers(entity), identifiersValues));
    }

    public void selectAllCardsOnSearchPage() {
        $x("//pg-btn//span[text()='" + SEARCH_SELECT_ALL + "']").click();
    }


    public void deleteAllCardsWithIdentifiers(SearchResultsEntity entity, String...identifiersValues) {
        openSearchResultsForCategory(entity);
        for (SelenideElement card : getAllCardsWithIdentifiers(getXPathsToIdentifiers(entity), identifiersValues)) {
            closeAllPopUps();
            applyActionToCard(DELETE, card);
            Pages.modalDialogPage().clickButton(ModalDialogButton.DELETE);
            getSuccessPopUp().shouldBe(visible);
        }
    }

    public void select1stDisplayedCard(SearchResultsEvent event) {
        openSearchResultsForCategory(event);
        selectCard(getListOfCards().first());
    }
    public void select1stDisplayedCard() {

        selectCard(getListOfCards().first());
    }

    public void select1stDisplayedCard(SearchResultsEntity entity) {
        openSearchResultsForCategory(entity);
        selectCard(getListOfCards().first());
    }

    public void applyActionTo1stDisplayedCard(SearchActionButton actionButton) {
        applyActionToCard(actionButton, getListOfCards().first());
    }

    public void applyActionTo1stDisplayedCard(SearchResultsEvent event, SearchActionButton actionButton) {
        openSearchResultsForCategory(event);
        applyActionTo1stDisplayedCard(actionButton);
    }

    public void applyActionTo1stDisplayedCard(SearchResultsEntity entity, SearchActionButton actionButton) {
        openSearchResultsForCategory(entity);
        applyActionTo1stDisplayedCard(actionButton);
    }
}
