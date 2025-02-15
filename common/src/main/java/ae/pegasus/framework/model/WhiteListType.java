package ae.pegasus.framework.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public enum WhiteListType {
    PHONE_NUMBER,
    EMAIL_ADDRESS,
    TWITTER_ID,
    // TODO: implement other identifier types in Phase 2
    TWITTER_HANDLE,
    YOUTUBE_CHANNEL_ID,
    INSTAGRAM_ID,
    DARK_WEB_AUTHOR_ID,
    DARK_WEB_FORUM_ID,

    TUMBLR_ID,
    GPLUS_ID,
    TUMBLR_HANDLE,
    INSTAGRAM_HANDLE,
    VEHICLE_PLATE_NUMBER,
    VEHICLE_PLATE_TEXT,
    PLATE_TEMPLATE_NAME,
    VOIP_ID,
    DARK_WEB_FORUM_NAME,
    BANK_ACCOUNT_NUMBER;

    private static final List<WhiteListType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static Random RANDOM = new Random();

    public static Object random() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}
