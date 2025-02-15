package ae.pegasus.framework.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum IdentifierType {

    TWITTER_ID("Twitter ID"),
    TWITTER_HANDLE("Twitter handle"),
    YOUTUBE_CHANNEL_ID("Youtube channel id"),
    NEWS_PUBLISHER_ID("News publisher id"),
    NEWS_AUTHOR_ID("News author id"),
    DARK_WEB_AUTHOR_ID("Dark web author id"),
    DARK_WEB_FORUM_ID("Dark web forum id"),
    DARK_WEB_FORUM_NAME("DARK WEB FORUM NAME"),
    DARK_WEB_ROOM_ID("Dark web room id"),
    DARK_WEB_REPORTS_AUTHOR_ID("Dark web reports author id"),
    DARK_WEB_REPORTS_PUBLISHER_ID("Dark web reports publisher id"),
    INSTAGRAM_ID("Instagram ID"),
    EMAIL_ADDRESS("Email address"),
    EID_NUMBER("EID Number (Emirates ID)"),
    UDB_NUMBER("UDB Number (UDB ID)"),
    PASSPORT_NUMBER("Passport number"),
    IMSI("International Mobile Subscriber Identity"),
    TMSI("Temporary Mobile Subscriber Identity"),
    IMEI("International Mobile Equipment Identity"),
    PHONE_NUMBER("Phone number"),
    VISA_NUMBER("Country Visa number"),
    PAYMENT_CARD_NUMBER("Payment card number"),
    BANK_ACCOUNT_NUMBER("Bank account number"),
    FREQUENT_FLYER_NUMBER("Frequent flyer number/ membership card"),
    VOIP_ID("VoIP ID"),
    GPLUS_ID("GPLUS ID"),
    INSTAGRAM_HANDLE("INSTAGRAM HANDLE"),
    TUMBLR_ID("TUMBLR ID"),
    TUMBLR_HANDLE("TUMBLR HANDLE"),
    UID_NUMBER("UID NUMBER"),
    VEHICLE_PLATE_TEXT("VEHICLE_PLATE_TEXT");

    private final String descriptions;

    IdentifierType(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getDescriptions() {
        return descriptions;
    }

    private static final List<IdentifierType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static Random RANDOM = new Random();

    public static IdentifierType random() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}
