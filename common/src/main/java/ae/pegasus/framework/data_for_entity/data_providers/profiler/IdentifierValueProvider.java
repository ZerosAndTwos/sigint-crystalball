package ae.pegasus.framework.data_for_entity.data_providers.profiler;

import ae.pegasus.framework.data_for_entity.data_providers.DependencyData;
import ae.pegasus.framework.data_for_entity.data_providers.DependencyDataProvider;
import ae.pegasus.framework.model.IdentifierType;
import org.apache.log4j.Logger;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static ae.pegasus.framework.utils.RandomGenerator.*;

public class IdentifierValueProvider extends DependencyDataProvider {

    private Logger log = Logger.getLogger(IdentifierValueProvider.class);

    @Override
    public String generate(int length) {

        DependencyData dependencyData = getDependencyData();
        IdentifierType type = IdentifierType.valueOf((String) dependencyData.getData("type"));

        switch (type) {
            case IMEI:
                return randomNumeric(10);
            case TMSI:
                return randomNumeric(10);
            case IMSI:
                return generateIMSI();
            case EID_NUMBER:
                return randomNumeric(10);
            case TWITTER_ID:
                return generateID();
            case UDB_NUMBER:
                return randomNumeric(10);
            case VISA_NUMBER:
                return randomNumeric(10);
            case INSTAGRAM_ID:
                return generateID();
            case PHONE_NUMBER:
                return generatePhone();
            case EMAIL_ADDRESS:
                return generateEmail();
            case NEWS_AUTHOR_ID:
                return generateID();
            case TWITTER_HANDLE:
                return generateTwitterHandle();
            case PASSPORT_NUMBER:
                return generateID();
            case DARK_WEB_ROOM_ID:
                return generateID();
            case DARK_WEB_FORUM_ID:
                return generateID();
            case NEWS_PUBLISHER_ID:
                return generateID();
            case DARK_WEB_AUTHOR_ID:
                return generateID();
            case YOUTUBE_CHANNEL_ID:
                return generateID();
            case BANK_ACCOUNT_NUMBER:
                return generateIMSI();
            case PAYMENT_CARD_NUMBER:
                return randomNumeric(16);
            case FREQUENT_FLYER_NUMBER:
                return randomNumeric(8);
            case DARK_WEB_REPORTS_AUTHOR_ID:
                return generateID();
            case DARK_WEB_REPORTS_PUBLISHER_ID:
                return generateID();
            default:
                log.warn("Unknown Identifier type:" + type + " generate 'ID' value");
                return generateID();
        }
    }
}
