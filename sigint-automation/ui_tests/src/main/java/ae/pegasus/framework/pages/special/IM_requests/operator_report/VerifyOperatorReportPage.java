package ae.pegasus.framework.pages.special.IM_requests.operator_report;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ae.pegasus.framework.constants.profiler.ProfilerWidget;
import ae.pegasus.framework.constants.special.IM.IMField;
import ae.pegasus.framework.pages.special.IM_requests.BaseVerifyIMPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;

public class VerifyOperatorReportPage extends BaseVerifyIMPage {

    private ElementsCollection getAllCardsAttached() {
        return getReportOrRequestBase().$$x(".//report-record");
    }

    private ElementsCollection getAllCardsWithTextMessageAttached(String textMessage) {
        return getAllCardsAttached().filterBy(text(textMessage));
    }

    private ElementsCollection getAllLabelValuePairsForCard(SelenideElement card) {
        return card.$$x(".//td[@class='report-record__attr']");
    }

    private Map<String, String> convertArrayToPairsMap(String[] labelValuePairs) {
        Map<String, String> result = new HashMap<>();
        for (int i = 0; i < labelValuePairs.length; i += 2) {
            result.put(labelValuePairs[i].toLowerCase(), labelValuePairs[i+1].toLowerCase());
        }
        return result;
    }

    private boolean isCardWithLabelValuePairsPresentInCollection(ElementsCollection cardsAttachedToProcess, String[] labelValuePairs) {
        if ((labelValuePairs.length % 2) > 0) {
            throw new IllegalArgumentException("It is expected that input's length should be multiple 2 but it is " + labelValuePairs.length);
        }

        for (SelenideElement card : cardsAttachedToProcess) {
            Map<String, String> pairsMap = convertArrayToPairsMap(labelValuePairs);
            for (SelenideElement pair : getAllLabelValuePairsForCard(card)) {
                String key = pair.$x(".//div[@class='report-record__label']").getText().toLowerCase().trim();
                String value = pair.$x(".//div[contains(@class, 'report-record__value')]").getText().toLowerCase().trim();
                String mapValue = pairsMap.get(key);
                if (mapValue != null && mapValue.equalsIgnoreCase(value)) {
                    pairsMap.remove(key);
                    if (pairsMap.isEmpty()) {
                        scrollWindowTo(card);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isCardWithLabelValuePairsAttached(String[] labelValuePairs) {
        return isCardWithLabelValuePairsPresentInCollection(getAllCardsAttached(), labelValuePairs);
    }

    public boolean isCardWithTextMessageAndLabelValuePairsAttached(String textMessage, String[] labelValuePairs) {
        return isCardWithLabelValuePairsPresentInCollection(getAllCardsWithTextMessageAttached(textMessage), labelValuePairs);
    }

    private ElementsCollection getAllSnapshots() {
        return getReportOrRequestBase().$$x(".//div[@class='report__block']/div[@class='report-record__header']");
    }

    public int getNumberOfAttachedSnapshots(ProfilerWidget widget) {
        int result = 0;
        for (SelenideElement snapshot : getAllSnapshots()) {
            if (snapshot.$x(".//div[@class='media-body']/div").getText().trim().equalsIgnoreCase(widget.getReportSectionName())
                    && snapshot.$x("./following-sibling::*").getTagName().equals("img")) {
                scrollWindowTo(snapshot);
                result++;
            }
        }

        return result;
    }

    public String getSingleValue(IMField field) {
        return getSingleValue(getFieldByLabel(field.getFieldName()), field.getControlType());
    }

    public List<String> getListOfValues(IMField field) {
        return getListOfValues(getFieldByLabel(field.getFieldName()), field.getControlType());
    }
}
